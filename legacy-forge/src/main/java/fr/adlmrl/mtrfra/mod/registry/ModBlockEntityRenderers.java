package fr.adlmrl.mtrfra.mod.registry;

import fr.adlmrl.mtrfra.mod.block.platform.InvisiblePlatform;
import fr.adlmrl.mtrfra.mod.block.platform.InvisibleSlabPlatform;

public final class ModBlockEntityRenderers {

    private ModBlockEntityRenderers() {}

    public static void registerClient() {
        MTRFRARegistryClient.REGISTRY_CLIENT.registerBlockEntityRenderer(
                ModBlockEntities.INVISIBLE_SLAB_PLATFORM, InvisibleSlabPlatform.Renderer::new);
        MTRFRARegistryClient.REGISTRY_CLIENT.registerBlockEntityRenderer(
                ModBlockEntities.INVISIBLE_PLATFORM, InvisiblePlatform.Renderer::new);
    }

}
