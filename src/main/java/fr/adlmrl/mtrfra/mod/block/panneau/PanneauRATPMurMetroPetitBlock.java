package fr.adlmrl.mtrfra.mod.block.panneau;

import org.mtr.mapping.holder.BlockSettings;

public class PanneauRATPMurMetroPetitBlock extends PanneauRATPBase {

    public PanneauRATPMurMetroPetitBlock(BlockSettings blockSettings) {
        super(blockSettings);
    }

    @Override
    public int maxCategory() {
        return 3;
    }

    @Override
    public float textY() {
        return 2.5F;
    }

    @Override
    public float textZ() {
        return 7.725F;
    }

}
