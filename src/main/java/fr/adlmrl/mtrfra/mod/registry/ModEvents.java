package fr.adlmrl.mtrfra.mod.registry;

import fr.adlmrl.mtrfra.mod.util.ClientRedrawQueue;

public final class ModEvents {

    private ModEvents() {}

    public static void register() {}

    public static void registerClient() {
        MTRFRARegistryClient.REGISTRY_CLIENT.eventRegistryClient.registerEndClientTick(ClientRedrawQueue::tick);
    }

}
