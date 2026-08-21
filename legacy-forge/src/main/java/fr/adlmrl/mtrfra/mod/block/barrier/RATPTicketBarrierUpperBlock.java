package fr.adlmrl.mtrfra.mod.block.barrier;

import fr.adlmrl.mtrfra.mod.barrier.PacketOpenTicketBarrierConfigScreen;
import fr.adlmrl.mtrfra.mod.barrier.TicketBarrierMode;
import fr.adlmrl.mtrfra.mod.registry.MTRFRARegistry;
import org.mtr.mapping.holder.ActionResult;
import org.mtr.mapping.holder.BlockHitResult;
import org.mtr.mapping.holder.BlockPos;
import org.mtr.mapping.holder.BlockRenderType;
import org.mtr.mapping.holder.BlockState;
import org.mtr.mapping.holder.BlockView;
import org.mtr.mapping.holder.Blocks;
import org.mtr.mapping.holder.Direction;
import org.mtr.mapping.holder.Hand;
import org.mtr.mapping.holder.PlayerEntity;
import org.mtr.mapping.holder.ServerPlayerEntity;
import org.mtr.mapping.holder.ShapeContext;
import org.mtr.mapping.holder.VoxelShape;
import org.mtr.mapping.holder.VoxelShapes;
import org.mtr.mapping.holder.World;
import org.mtr.mapping.mapper.BlockExtension;
import org.mtr.mapping.mapper.DirectionHelper;
import org.mtr.mod.Items;
import org.mtr.mod.block.BlockTicketBarrier;
import org.mtr.mod.block.IBlock;
import org.mtr.mod.data.TicketSystem.EnumTicketBarrierOpen;

public class RATPTicketBarrierUpperBlock extends BlockExtension {

    public RATPTicketBarrierUpperBlock() {
        super(org.mtr.mod.Blocks.createDefaultBlockSettings(true, state -> 5).nonOpaque().dropsNothing());
    }

    @Override
    public BlockRenderType getRenderType2(BlockState state) {
        return BlockRenderType.getInvisibleMapped();
    }

    @Override
    public VoxelShape getOutlineShape2(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return computeShape(world, pos);
    }

    @Override
    public VoxelShape getCollisionShape2(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return computeShape(world, pos);
    }

    @Override
    public VoxelShape getCullingShape2(BlockState state, BlockView world, BlockPos pos) {
        return VoxelShapes.empty();
    }

    @Override
    public float getAmbientOcclusionLightLevel2(BlockState state, BlockView world, BlockPos pos) {
        return 1;
    }

    private static VoxelShape computeShape(BlockView world, BlockPos pos) {
        final BlockState anchorState = world.getBlockState(pos.down());
        final Object anchorBlock = anchorState.getBlock().data;
        if (anchorBlock instanceof RATPTicketBarrierBlock barrier) {
            final Direction facing = IBlock.getStatePropertySafe(anchorState, DirectionHelper.FACING);
            final EnumTicketBarrierOpen open = IBlock.getStatePropertySafe(anchorState, BlockTicketBarrier.OPEN);
            VoxelShape shape = RATPTicketBarrierBlock.box(RATPTicketBarrierBlock.POST_LEFT_UPPER, facing);
            if (barrier.hasSideCover()) {
                shape = VoxelShapes.union(shape, RATPTicketBarrierBlock.box(RATPTicketBarrierBlock.POST_RIGHT_UPPER, facing));
            }
            if (open != EnumTicketBarrierOpen.OPEN) {
                shape = VoxelShapes.union(shape, RATPTicketBarrierBlock.box(RATPTicketBarrierBlock.DOOR_CLOSED_UPPER, facing));
            }
            return shape;
        }
        if (anchorBlock instanceof RATPTicketBarrierSideCoverBlock) {
            final Direction facing = IBlock.getStatePropertySafe(anchorState, DirectionHelper.FACING);
            return RATPTicketBarrierBlock.box(RATPTicketBarrierSideCoverBlock.POST_UPPER, facing);
        }
        return VoxelShapes.empty();
    }

    @Override
    public void onBreak2(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        if (!world.isClient()) {
            final BlockPos anchorPos = pos.down();
            final Object anchorBlock = world.getBlockState(anchorPos).getBlock().data;
            if (anchorBlock instanceof RATPTicketBarrierBlock || anchorBlock instanceof RATPTicketBarrierSideCoverBlock) {
                world.setBlockState(anchorPos, Blocks.getAirMapped().getDefaultState(), 35);
            }
        }
        super.onBreak2(world, pos, state, player);
    }

    @Override
    public ActionResult onUse2(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!player.getStackInHand(hand).getItem().equals(Items.BRUSH.get().asItem())) {
            return ActionResult.PASS;
        }
        final BlockPos anchorPos = pos.down();
        if (!(world.getBlockState(anchorPos).getBlock().data instanceof RATPTicketBarrierBlock)) {
            return ActionResult.PASS;
        }
        if (!world.isClient()) {
            final RATPTicketBarrierBlock.BarrierBlockEntity barrierBlockEntity = RATPTicketBarrierBlock.getBarrierBlockEntity(world, anchorPos);
            final TicketBarrierMode mode = barrierBlockEntity == null ? TicketBarrierMode.MTR_BALANCE : barrierBlockEntity.mode;
            final String acceptedTicketIdsCsv = barrierBlockEntity == null ? "" : barrierBlockEntity.acceptedTicketIdsCsv;
            MTRFRARegistry.REGISTRY.sendPacketToClient(ServerPlayerEntity.cast(player), new PacketOpenTicketBarrierConfigScreen(anchorPos, mode.ordinal(), acceptedTicketIdsCsv));
        }
        return ActionResult.SUCCESS;
    }

}
