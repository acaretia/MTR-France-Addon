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
        return 9.5F;
    }

    @Override
    public float textZ() {
        return 6.45F;
    }

    @Override
    public double[] boundingBox() {
        return new double[]{-15, 3, 7, 31, 16, 9};
    }

}
