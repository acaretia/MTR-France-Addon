package fr.adlmrl.mtrfra.mod.block.sign;

import fr.adlmrl.mtrfra.mod.block.base.DirectionalBlock;
import org.mtr.mapping.holder.BlockPos;
import org.mtr.mapping.holder.BlockSettings;
import org.mtr.mapping.holder.BlockState;
import org.mtr.mapping.holder.BlockView;
import org.mtr.mapping.holder.ShapeContext;
import org.mtr.mapping.holder.VoxelShape;
import org.mtr.mod.block.IBlock;

public class RATPNoSmokingSign extends DirectionalBlock {

    public RATPNoSmokingSign(BlockSettings settings) {
        super(settings);
    }

    @Override
    public VoxelShape getOutlineShape2(BlockState state, BlockView view, BlockPos pos, ShapeContext context) {
        return IBlock.getVoxelShapeByDirection(4, 0, 15, 12, 16, 16, IBlock.getStatePropertySafe(state, FACING));
    }

    @Override
    public VoxelShape getCollisionShape2(BlockState state, BlockView view, BlockPos pos, ShapeContext context) {
        return IBlock.getVoxelShapeByDirection(4, 0, 15, 12, 16, 16, IBlock.getStatePropertySafe(state, FACING));
    }

}
