package fr.adlmrl.mtrfra.mod.block.panneau;

import org.mtr.mapping.holder.BlockSettings;

public class PanneauRATPMurRerBlock extends PanneauRATPBase {

    public PanneauRATPMurRerBlock(BlockSettings blockSettings) {
        super(blockSettings);
    }

    @Override
    public int maxCategory() {
        return 7;
    }

    @Override
    public float textY() {
        return 1.391F;
    }

    @Override
    public float textZ() {
        return 7.548F;
    }

}
