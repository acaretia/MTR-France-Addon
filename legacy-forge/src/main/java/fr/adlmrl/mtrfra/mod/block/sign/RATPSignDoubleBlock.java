package fr.adlmrl.mtrfra.mod.block.sign;

import org.mtr.mapping.holder.BlockSettings;

public class RATPSignDoubleBlock extends RATPSignBase {

    public RATPSignDoubleBlock(BlockSettings blockSettings) {
        super(blockSettings, 7, 5.0F, 6.999F, new double[]{-16, 0, 7, 32, 9, 9}, true);
    }

}
