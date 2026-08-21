package fr.adlmrl.mtrfra.mod.block.ticketmachine;

import org.mtr.mapping.holder.BlockPos;
import org.mtr.mapping.holder.BlockState;
import org.mtr.mapping.holder.BlockView;
import org.mtr.mapping.holder.Direction;
import org.mtr.mapping.holder.ShapeContext;
import org.mtr.mapping.holder.VoxelShape;
import org.mtr.mod.Blocks;
import org.mtr.mod.block.BlockTicketMachine;
import org.mtr.mod.block.IBlock;
import org.mtr.mod.block.IBlock.DoubleBlockHalf;

public class IDFMTicketMachineBlock extends BlockTicketMachine {

    public IDFMTicketMachineBlock() {
        super(Blocks.createDefaultBlockSettings(true, state -> 5).nonOpaque());
    }

    @Override
    public VoxelShape getOutlineShape2(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        final Direction facing = IBlock.getStatePropertySafe(state, FACING);
        final int totalHeight = 30;
        final double height = IBlock.getStatePropertySafe(state, HALF) == DoubleBlockHalf.UPPER ? totalHeight - 16 : 16;
        return IBlock.getVoxelShapeByDirection(0, 0, 0, 16, height, 16, facing);
    }

}
