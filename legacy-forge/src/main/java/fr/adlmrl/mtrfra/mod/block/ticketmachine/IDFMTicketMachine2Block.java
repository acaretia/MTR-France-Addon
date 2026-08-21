package fr.adlmrl.mtrfra.mod.block.ticketmachine;

import fr.adlmrl.mtrfra.mod.registry.ModBlocks;
import org.mtr.mapping.holder.BlockPos;
import org.mtr.mapping.holder.BlockState;
import org.mtr.mapping.holder.BlockView;
import org.mtr.mapping.holder.Direction;
import org.mtr.mapping.holder.ItemPlacementContext;
import org.mtr.mapping.holder.ItemStack;
import org.mtr.mapping.holder.LivingEntity;
import org.mtr.mapping.holder.PlayerEntity;
import org.mtr.mapping.holder.Property;
import org.mtr.mapping.holder.ShapeContext;
import org.mtr.mapping.holder.VoxelShape;
import org.mtr.mapping.holder.World;
import org.mtr.mod.Blocks;
import org.mtr.mod.block.BlockTicketMachine;
import org.mtr.mod.block.IBlock;
import org.mtr.mod.block.IBlock.DoubleBlockHalf;

public class IDFMTicketMachine2Block extends BlockTicketMachine {

    public IDFMTicketMachine2Block() {
        super(Blocks.createDefaultBlockSettings(true, state -> 5).nonOpaque());
    }

    @Override
    public VoxelShape getOutlineShape2(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        final Direction facing = IBlock.getStatePropertySafe(state, FACING);
        final int totalHeight = 30;
        final double height = IBlock.getStatePropertySafe(state, HALF) == DoubleBlockHalf.UPPER ? totalHeight - 16 : 16;

        return IBlock.getVoxelShapeByDirection(0, 0, 0, 16, height, 16, facing);
    }

    @Override
    public BlockState getPlacementState2(ItemPlacementContext ctx) {
        final BlockState state = super.getPlacementState2(ctx);
        if (state == null) {
            return null;
        }
        final BlockPos companionPos = ctx.getBlockPos().offset(ctx.getPlayerFacing().rotateYCounterclockwise());
        if (!ctx.getWorld().getBlockState(companionPos).canReplace(ctx) || !ctx.getWorld().getBlockState(companionPos.up()).canReplace(ctx)) {
            return null;
        }
        return state;
    }

    @Override
    public void onPlaced2(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack itemStack) {
        super.onPlaced2(world, pos, state, placer, itemStack);
        if (!world.isClient()) {
            final int totalHeight = 30;
            final Direction facing = IBlock.getStatePropertySafe(state, FACING);
            final BlockPos companionPos = pos.offset(facing.rotateYCounterclockwise());

            final BlockState companionBase = ModBlocks.HITBOX_COMPANION.get().getDefaultState()
                    .with(new Property<>(HitboxCompanionBlock.FACING.data), facing.data);

            world.setBlockState(companionPos, companionBase.with(HitboxCompanionBlock.HEIGHT_PROPERTY, 16), 3);
            world.setBlockState(companionPos.up(), companionBase.with(HitboxCompanionBlock.HEIGHT_PROPERTY, totalHeight - 16), 3);
        }
    }

    @Override
    public void onBreak2(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        if (!world.isClient()) {
            final Direction facing = IBlock.getStatePropertySafe(state, FACING);
            final BlockPos anchorTwinPos = IBlock.getStatePropertySafe(state, HALF) == DoubleBlockHalf.UPPER ? pos.down() : pos.up();
            final org.mtr.mapping.holder.BlockState air = org.mtr.mapping.holder.Blocks.getAirMapped().getDefaultState();
            world.setBlockState(pos.offset(facing.rotateYCounterclockwise()), air, 35);
            world.setBlockState(anchorTwinPos.offset(facing.rotateYCounterclockwise()), air, 35);
        }
        super.onBreak2(world, pos, state, player);
    }

}
