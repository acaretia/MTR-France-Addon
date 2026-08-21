package fr.adlmrl.mtrfra.mod.block.panneau;

import org.mtr.mapping.holder.BlockSettings;

public class PanneauRATPDoubleBlock extends PanneauRATPBase {

    public PanneauRATPDoubleBlock(BlockSettings blockSettings) {
        super(blockSettings);
    }

    @Override
    public int maxCategory() {
        return 7;
    }

    @Override
    public float textY() {
        return 4.5F;
    }

    @Override
    public float textZ() {
        return 6.45F;
    }

    @Override
    public double[] boundingBox() {
        return new double[]{-16, 0, 7, 32, 9, 9};
    }

}
