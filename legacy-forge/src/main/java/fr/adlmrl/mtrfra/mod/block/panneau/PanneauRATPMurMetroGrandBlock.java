package fr.adlmrl.mtrfra.mod.block.panneau;

import org.mtr.mapping.holder.BlockSettings;

public class PanneauRATPMurMetroGrandBlock extends PanneauRATPBase {

    public PanneauRATPMurMetroGrandBlock(BlockSettings blockSettings) {
        super(blockSettings);
    }

    @Override
    public int maxCategory() {
        return 3;
    }

    @Override
    public float textY() {
        return 2.333F;
    }

    @Override
    public float textZ() {
        return 7.817F;
    }

}
