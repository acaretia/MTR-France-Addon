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
        return 8F;
    }

    @Override
    public float textZ() {
        return 15.2F;
    }

}
