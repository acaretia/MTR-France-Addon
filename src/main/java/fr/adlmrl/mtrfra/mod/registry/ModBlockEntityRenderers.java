package fr.adlmrl.mtrfra.mod.registry;

import fr.adlmrl.mtrfra.mod.block.copycat.CopycatBlockBase;
import fr.adlmrl.mtrfra.mod.block.sign.RATPSignRenderer;
import fr.adlmrl.mtrfra.mod.block.platform.InvisiblePlatform;
import fr.adlmrl.mtrfra.mod.block.platform.InvisibleSlabPlatform;

public final class ModBlockEntityRenderers {

    private ModBlockEntityRenderers() {}

    public static void registerClient() {
        MTRFRARegistryClient.REGISTRY_CLIENT.registerBlockEntityRenderer(ModBlockEntities.INVISIBLE_SLAB_PLATFORM, InvisibleSlabPlatform.Renderer::new);
        MTRFRARegistryClient.REGISTRY_CLIENT.registerBlockEntityRenderer(ModBlockEntities.INVISIBLE_PLATFORM, InvisiblePlatform.Renderer::new);
        MTRFRARegistryClient.REGISTRY_CLIENT.registerBlockEntityRenderer(ModBlockEntities.COPYCAT, CopycatBlockBase.Renderer::new);
        MTRFRARegistryClient.REGISTRY_CLIENT.registerBlockEntityRenderer(ModBlockEntities.RATP_SIGN, RATPSignRenderer::new);
    }

}
