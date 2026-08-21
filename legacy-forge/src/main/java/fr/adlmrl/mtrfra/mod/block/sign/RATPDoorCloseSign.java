package fr.adlmrl.mtrfra.mod.block.sign;

import fr.adlmrl.mtrfra.mod.block.base.DirectionalBlock;
import org.mtr.mapping.holder.BlockPos;
import org.mtr.mapping.holder.BlockSettings;
import org.mtr.mapping.holder.BlockState;
import org.mtr.mapping.holder.BlockView;
import org.mtr.mapping.holder.ShapeContext;
import org.mtr.mapping.holder.VoxelShape;
import org.mtr.mod.block.IBlock;

public class RATPDoorCloseSign extends DirectionalBlock {

    public RATPDoorCloseSign(BlockSettings settings) {
        super(settings);
    }

    @Override
    public VoxelShape getOutlineShape2(BlockState state, BlockView view, BlockPos pos, ShapeContext context) {

        return IBlock.getVoxelShapeByDirection(3.25, 0, 14, 12.75, 25, 16, IBlock.getStatePropertySafe(state, FACING));
    }

    @Override
    public VoxelShape getCollisionShape2(BlockState state, BlockView view, BlockPos pos, ShapeContext context) {
        return IBlock.getVoxelShapeByDirection(2, 0, 14, 14, 16, 16, IBlock.getStatePropertySafe(state, FACING));
    }

}
