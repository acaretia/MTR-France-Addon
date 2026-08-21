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
        return 1.5F;
    }

    @Override
    public float textZ() {
        return 8.533F;
    }

}
