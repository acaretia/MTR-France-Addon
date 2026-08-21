package fr.adlmrl.mtrfra.mod.registry;

import fr.adlmrl.mtrfra.mod.barrier.PacketOpenTicketBarrierConfigScreen;
import fr.adlmrl.mtrfra.mod.barrier.PacketSaveTicketBarrierConfig;

public final class ModNetworking {

    private ModNetworking() {}

    public static void register() {
        MTRFRARegistry.REGISTRY.registerPacket(PacketOpenTicketBarrierConfigScreen.class, PacketOpenTicketBarrierConfigScreen::new);
        MTRFRARegistry.REGISTRY.registerPacket(PacketSaveTicketBarrierConfig.class, PacketSaveTicketBarrierConfig::new);
    }

    public static void registerClient() {}

}
