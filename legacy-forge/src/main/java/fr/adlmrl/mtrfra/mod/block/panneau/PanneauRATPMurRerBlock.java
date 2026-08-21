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
        return 7F;
    }

    @Override
    public float textZ() {
        return 13.95F;
    }

    @Override
    public double[] boundingBox() {
        return new double[]{-15, 3, 14, 31, 11, 16};
    }

}
