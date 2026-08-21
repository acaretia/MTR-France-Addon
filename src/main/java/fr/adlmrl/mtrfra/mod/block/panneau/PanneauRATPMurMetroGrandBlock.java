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
        return 16.25F;
    }

    @Override
    public double[] boundingBox() {
        return new double[]{-16, 1, 15.5, 32, 15, 16};
    }

}
