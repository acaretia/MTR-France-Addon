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
        return 8F;
    }

    @Override
    public float textZ() {
        return 16.25F;
    }

    @Override
    public double[] boundingBox() {
        return new double[]{-8, 3, 15.5, 24, 13, 16};
    }

}
