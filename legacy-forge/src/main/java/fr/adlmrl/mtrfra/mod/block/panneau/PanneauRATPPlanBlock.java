package fr.adlmrl.mtrfra.mod.block.panneau;

import fr.adlmrl.mtrfra.mod.block.base.DirectionalBlock;
import org.mtr.mapping.holder.BlockPos;
import org.mtr.mapping.holder.BlockSettings;
import org.mtr.mapping.holder.BlockState;
import org.mtr.mapping.holder.BlockView;
import org.mtr.mapping.holder.Direction;
import org.mtr.mapping.holder.ShapeContext;
import org.mtr.mapping.holder.VoxelShape;
import org.mtr.mapping.holder.VoxelShapes;
import org.mtr.mod.block.IBlock;

public class PanneauRATPPlanBlock extends DirectionalBlock {

    private static final double[] BOUNDING_BOX = {-16, 0, 7, 32, 32, 9};

    public PanneauRATPPlanBlock(BlockSettings blockSettings) {
        super(blockSettings);
    }

    private VoxelShape computeShape(BlockState state) {
        final Direction facing = IBlock.getStatePropertySafe(state, FACING);
        return IBlock.getVoxelShapeByDirection(BOUNDING_BOX[0], BOUNDING_BOX[1], BOUNDING_BOX[2], BOUNDING_BOX[3], BOUNDING_BOX[4], BOUNDING_BOX[5], facing);
    }

    @Override
    public VoxelShape getOutlineShape2(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return computeShape(state);
    }

    @Override
    public VoxelShape getCollisionShape2(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return computeShape(state);
    }

    @Override
    public VoxelShape getCullingShape2(BlockState state, BlockView world, BlockPos pos) {
        return VoxelShapes.empty();
    }

    @Override
    public float getAmbientOcclusionLightLevel2(BlockState state, BlockView world, BlockPos pos) {
        return 1;
    }

}
