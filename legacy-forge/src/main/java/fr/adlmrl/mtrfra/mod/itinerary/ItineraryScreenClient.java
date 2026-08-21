package fr.adlmrl.mtrfra.mod.itinerary;

import org.mtr.mapping.holder.MinecraftClient;
import org.mtr.mapping.holder.Screen;

import java.util.List;

final class ItineraryScreenClient {

    private ItineraryScreenClient() {}

    static void open() {
        final MinecraftClient minecraftClient = MinecraftClient.getInstance();
        final Screen currentScreen = minecraftClient.getCurrentScreenMapped();
        if (currentScreen == null || !(currentScreen.data instanceof ItineraryScreen)) {
            minecraftClient.openScreen(new Screen(new ItineraryScreen()));
        }
    }

    static void applyResult(boolean found, List<ItineraryFinder.Leg> legs) {
        final Screen currentScreen = MinecraftClient.getInstance().getCurrentScreenMapped();
        if (currentScreen != null && currentScreen.data instanceof ItineraryScreen) {
            ((ItineraryScreen) currentScreen.data).applyResult(found, legs);
        }
    }

}
