package fr.adlmrl.mtrfra.mod.registry;

import fr.adlmrl.mtrfra.mod.barrier.PacketOpenTicketBarrierConfigScreen;
import fr.adlmrl.mtrfra.mod.barrier.PacketSaveTicketBarrierConfig;
import fr.adlmrl.mtrfra.mod.panneau.PacketOpenPanneauConfigScreen;
import fr.adlmrl.mtrfra.mod.panneau.PacketSavePanneauConfig;

public final class ModNetworking {

    private ModNetworking() {}

    public static void register() {
        MTRFRARegistry.REGISTRY.registerPacket(PacketOpenTicketBarrierConfigScreen.class, PacketOpenTicketBarrierConfigScreen::new);
        MTRFRARegistry.REGISTRY.registerPacket(PacketSaveTicketBarrierConfig.class, PacketSaveTicketBarrierConfig::new);
        MTRFRARegistry.REGISTRY.registerPacket(PacketOpenPanneauConfigScreen.class, PacketOpenPanneauConfigScreen::new);
        MTRFRARegistry.REGISTRY.registerPacket(PacketSavePanneauConfig.class, PacketSavePanneauConfig::new);
    }

    public static void registerClient() {}

}
