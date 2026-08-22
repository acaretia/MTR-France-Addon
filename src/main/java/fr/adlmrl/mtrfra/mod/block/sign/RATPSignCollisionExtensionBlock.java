package fr.adlmrl.mtrfra.mod.block.sign;

import fr.adlmrl.mtrfra.mod.sign.PacketOpenSignConfigScreen;
import fr.adlmrl.mtrfra.mod.registry.MTRFRARegistry;
import org.mtr.mapping.holder.ActionResult;
import org.mtr.mapping.holder.Blocks;
import org.mtr.mapping.holder.Box;
import org.mtr.mapping.holder.BlockEntity;
import org.mtr.mapping.holder.BlockHitResult;
import org.mtr.mapping.holder.BlockPos;
import org.mtr.mapping.holder.BlockRenderType;
import org.mtr.mapping.holder.BlockState;
import org.mtr.mapping.holder.BlockView;
import org.mtr.mapping.holder.Direction;
import org.mtr.mapping.holder.Hand;
import org.mtr.mapping.holder.ItemStack;
import org.mtr.mapping.holder.PlayerEntity;
import org.mtr.mapping.holder.ServerPlayerEntity;
import org.mtr.mapping.holder.ShapeContext;
import org.mtr.mapping.holder.VoxelShape;
import org.mtr.mapping.holder.VoxelShapes;
import org.mtr.mapping.holder.World;
import org.mtr.mapping.mapper.BlockExtension;
import org.mtr.mapping.mapper.DirectionHelper;
import org.mtr.mod.Items;
import org.mtr.mod.block.IBlock;

public class RATPSignCollisionExtensionBlock extends BlockExtension {

    public RATPSignCollisionExtensionBlock() {
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
        VoxelShape shape = VoxelShapes.empty();
        for (final Direction direction : Direction.values()) {
            final BlockPos anchorPos = pos.offset(direction);
            final BlockState anchorState = world.getBlockState(anchorPos);
            final Object anchorBlock = anchorState.getBlock().data;
            if (!(anchorBlock instanceof HasBoundingBox boundedAnchor)) {
                continue;
            }
            final VoxelShape anchorShape = anchorVoxelShape(anchorState, boundedAnchor);
            final Box bounds = anchorShape.getBoundingBox();
            final int dx = pos.getX() - anchorPos.getX();
            final int dy = pos.getY() - anchorPos.getY();
            final int dz = pos.getZ() - anchorPos.getZ();
            final boolean overlapsHere =
                    (dx > 0 && bounds.getMaxXMapped() > 1) ||
                            (dx < 0 && bounds.getMinXMapped() < 0) ||
                            (dz > 0 && bounds.getMaxZMapped() > 1) ||
                            (dz < 0 && bounds.getMinZMapped() < 0) ||
                            (dy > 0 && bounds.getMaxYMapped() > 1) ||
                            (dy < 0 && bounds.getMinYMapped() < 0);
            if (overlapsHere) {
                shape = VoxelShapes.union(shape, anchorShape.offset(-dx, -dy, -dz));
            }
        }
        return shape;
    }

    private static VoxelShape anchorVoxelShape(BlockState anchorState, HasBoundingBox boundedAnchor) {
        final Direction facing = IBlock.getStatePropertySafe(anchorState, DirectionHelper.FACING);
        final double[] box = boundedAnchor.boundingBox();
        return IBlock.getVoxelShapeByDirection(box[0], box[1], box[2], box[3], box[4], box[5], facing);
    }

    public static void placeAround(World world, BlockPos anchorPos, Direction facing, double[] boundingBox) {
        final VoxelShape shape = IBlock.getVoxelShapeByDirection(boundingBox[0], boundingBox[1], boundingBox[2], boundingBox[3], boundingBox[4], boundingBox[5], facing);
        final Box bounds = shape.getBoundingBox();
        if (bounds.getMinXMapped() < 0) {
            placeAt(world, anchorPos.offset(Direction.WEST));
        }
        if (bounds.getMaxXMapped() > 1) {
            placeAt(world, anchorPos.offset(Direction.EAST));
        }
        if (bounds.getMinZMapped() < 0) {
            placeAt(world, anchorPos.offset(Direction.NORTH));
        }
        if (bounds.getMaxZMapped() > 1) {
            placeAt(world, anchorPos.offset(Direction.SOUTH));
        }
        if (bounds.getMaxYMapped() > 1) {
            placeAt(world, anchorPos.offset(Direction.UP));
        }
        if (bounds.getMinYMapped() < 0) {
            placeAt(world, anchorPos.offset(Direction.DOWN));
        }
    }

    private static void placeAt(World world, BlockPos target) {
        world.setBlockState(target, fr.adlmrl.mtrfra.mod.registry.ModBlocks.RATP_SIGN_COLLISION_EXTENSION.get().getDefaultState(), 3);
    }

    public static void removeAround(World world, BlockPos anchorPos, Direction facing, double[] boundingBox) {
        final VoxelShape shape = IBlock.getVoxelShapeByDirection(boundingBox[0], boundingBox[1], boundingBox[2], boundingBox[3], boundingBox[4], boundingBox[5], facing);
        final Box bounds = shape.getBoundingBox();
        if (bounds.getMinXMapped() < 0) {
            removeAt(world, anchorPos.offset(Direction.WEST));
        }
        if (bounds.getMaxXMapped() > 1) {
            removeAt(world, anchorPos.offset(Direction.EAST));
        }
        if (bounds.getMinZMapped() < 0) {
            removeAt(world, anchorPos.offset(Direction.NORTH));
        }
        if (bounds.getMaxZMapped() > 1) {
            removeAt(world, anchorPos.offset(Direction.SOUTH));
        }
        if (bounds.getMaxYMapped() > 1) {
            removeAt(world, anchorPos.offset(Direction.UP));
        }
        if (bounds.getMinYMapped() < 0) {
            removeAt(world, anchorPos.offset(Direction.DOWN));
        }
    }

    private static void removeAt(World world, BlockPos target) {
        if (world.getBlockState(target).getBlock().data instanceof RATPSignCollisionExtensionBlock) {
            world.setBlockState(target, Blocks.getAirMapped().getDefaultState(), 35);
        }
    }

    @Override
    public ActionResult onUse2(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!player.getStackInHand(hand).getItem().equals(Items.BRUSH.get().asItem())) {
            return ActionResult.PASS;
        }
        final BlockPos anchorPos = findSignAnchorPos(BlockView.cast(world), pos);
        if (anchorPos == null) {
            return ActionResult.PASS;
        }
        if (!world.isClient()) {
            final BlockState anchorState = world.getBlockState(anchorPos);
            final RATPSignBase sign = (RATPSignBase) anchorState.getBlock().data;
            final BlockEntity blockEntity = world.getBlockEntity(anchorPos);
            final RATPSignBase.BlockEntityBase signBlockEntity = blockEntity != null && blockEntity.data instanceof RATPSignBase.BlockEntityBase ? (RATPSignBase.BlockEntityBase) blockEntity.data : null;
            final String customTitle = signBlockEntity == null ? "" : signBlockEntity.customTitle;
            final String subtitle = signBlockEntity == null ? "" : signBlockEntity.subtitle;
            final int category = IBlock.getStatePropertySafe(anchorState, RATPSignBase.CATEGORY);
            MTRFRARegistry.REGISTRY.sendPacketToClient(ServerPlayerEntity.cast(player), new PacketOpenSignConfigScreen(anchorPos, customTitle, subtitle, category, sign.maxCategory()));
        }
        return ActionResult.SUCCESS;
    }

    private static BlockPos findSignAnchorPos(BlockView world, BlockPos pos) {
        for (final Direction direction : Direction.values()) {
            final BlockPos anchorPos = pos.offset(direction);
            final BlockState anchorState = world.getBlockState(anchorPos);
            final Object anchorBlock = anchorState.getBlock().data;
            if (!(anchorBlock instanceof RATPSignBase)) {
                continue;
            }
            final VoxelShape anchorShape = anchorVoxelShape(anchorState, (HasBoundingBox) anchorBlock);
            final Box bounds = anchorShape.getBoundingBox();
            final int dx = pos.getX() - anchorPos.getX();
            final int dy = pos.getY() - anchorPos.getY();
            final int dz = pos.getZ() - anchorPos.getZ();
            final boolean overlapsHere =
                    (dx > 0 && bounds.getMaxXMapped() > 1) ||
                            (dx < 0 && bounds.getMinXMapped() < 0) ||
                            (dz > 0 && bounds.getMaxZMapped() > 1) ||
                            (dz < 0 && bounds.getMinZMapped() < 0) ||
                            (dy > 0 && bounds.getMaxYMapped() > 1) ||
                            (dy < 0 && bounds.getMinYMapped() < 0);
            if (overlapsHere) {
                return anchorPos;
            }
        }
        return null;
    }

    @Override
    public ItemStack getPickStack2(BlockView world, BlockPos pos, BlockState state) {
        final BlockPos anchorPos = findSignAnchorPos(world, pos);
        return anchorPos == null ? ItemStack.getEmptyMapped() : world.getBlockState(anchorPos).getBlock().asItem().getDefaultStack();
    }

    @Override
    public void onBreak2(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        if (!world.isClient()) {
            final BlockPos anchorPos = findSignAnchorPos(BlockView.cast(world), pos);
            if (anchorPos != null) {
                world.setBlockState(anchorPos, Blocks.getAirMapped().getDefaultState(), 35);
            }
        }
        super.onBreak2(world, pos, state, player);
    }

}
