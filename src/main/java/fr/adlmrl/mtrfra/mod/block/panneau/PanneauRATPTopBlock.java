package fr.adlmrl.mtrfra.mod.block.panneau;

import org.mtr.mapping.holder.BlockSettings;

public class PanneauRATPTopBlock extends PanneauRATPBase {

    public PanneauRATPTopBlock(BlockSettings blockSettings) {
        super(blockSettings);
    }

    @Override
    public int maxCategory() {
        return 7;
    }

    @Override
    public float textY() {
        return 2.261F;
    }

    @Override
    public float textZ() {
        return 7.548F;
    }

}
