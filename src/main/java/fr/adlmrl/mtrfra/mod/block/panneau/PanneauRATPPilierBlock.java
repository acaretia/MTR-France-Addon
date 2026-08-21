package fr.adlmrl.mtrfra.mod.block.panneau;

import org.mtr.mapping.holder.BlockSettings;

public class PanneauRATPPilierBlock extends PanneauRATPBase {

    public PanneauRATPPilierBlock(BlockSettings blockSettings) {
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
        return 11.95F;
    }

    @Override
    public double[] boundingBox() {
        return new double[]{-15, 0, 12, 31, 11, 14};
    }

}
