package fr.adlmrl.mtrfra.mod.panneau;

import org.mtr.mapping.holder.BlockPos;
import org.mtr.mapping.holder.MinecraftClient;
import org.mtr.mapping.holder.Screen;

final class PanneauConfigScreenOpener {

    private PanneauConfigScreenOpener() {}

    static void open(BlockPos pos, String title, String subtitle, int category, int maxCategory) {
        MinecraftClient.getInstance().openScreen(new Screen(new PanneauConfigScreen(pos, title, subtitle, category, maxCategory)));
    }

}
