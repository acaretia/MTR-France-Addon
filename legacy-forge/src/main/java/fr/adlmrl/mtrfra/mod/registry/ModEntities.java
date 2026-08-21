package fr.adlmrl.mtrfra.mod.registry;

import fr.adlmrl.mtrfra.mod.entity.LogoEntity;
import fr.adlmrl.mtrfra.mod.entity.LogoEntityRenderer;
import fr.adlmrl.mtrfra.mod.entity.SeatEntity;
import fr.adlmrl.mtrfra.mod.entity.SeatEntityRenderer;
import org.mtr.mapping.registry.EntityTypeRegistryObject;

public final class ModEntities {

    public static final EntityTypeRegistryObject<SeatEntity> SEAT = MTRFRARegistry.registerEntity(
            "seat", SeatEntity::new, 0.01f, 0.01f
    );

    public static final EntityTypeRegistryObject<LogoEntity> LOGO_MTRFRANCEADDON = MTRFRARegistry.registerEntity(
            "mtrfranceaddon_logo", LogoEntity::new, 1.0f, 1.0f
    );
    public static final EntityTypeRegistryObject<LogoEntity> LOGO_SNCF_ACTUEL = MTRFRARegistry.registerEntity(
            "sncf_logo_actuel", LogoEntity::new, 1.0f, 1.0f
    );
    public static final EntityTypeRegistryObject<LogoEntity> LOGO_SNCF_1992_2005 = MTRFRARegistry.registerEntity(
            "sncf_logo_1992-2005", LogoEntity::new, 1.0f, 1.0f
    );
    public static final EntityTypeRegistryObject<LogoEntity> LOGO_SNCF_1985_1992_V1 = MTRFRARegistry.registerEntity(
            "sncf_logo_1985-1992_v1", LogoEntity::new, 1.0f, 1.0f
    );
    public static final EntityTypeRegistryObject<LogoEntity> LOGO_SNCF_1985_1992_V2 = MTRFRARegistry.registerEntity(
            "sncf_logo_1985-1992_v2", LogoEntity::new, 1.0f, 1.0f
    );
    public static final EntityTypeRegistryObject<LogoEntity> LOGO_SNCF_1967_1985_V1 = MTRFRARegistry.registerEntity(
            "sncf_logo_1967-1985_v1", LogoEntity::new, 1.0f, 1.0f
    );
    public static final EntityTypeRegistryObject<LogoEntity> LOGO_SNCF_1967_1985_V2 = MTRFRARegistry.registerEntity(
            "sncf_logo_1967-1985_v2", LogoEntity::new, 1.0f, 1.0f
    );
    public static final EntityTypeRegistryObject<LogoEntity> LOGO_SNCF_1947_1967 = MTRFRARegistry.registerEntity(
            "sncf_logo_1947-1967", LogoEntity::new, 1.0f, 1.0f
    );
    public static final EntityTypeRegistryObject<LogoEntity> LOGO_SNCF_1938_1947 = MTRFRARegistry.registerEntity(
            "sncf_logo_1938-1947", LogoEntity::new, 1.0f, 1.0f
    );
    public static final EntityTypeRegistryObject<LogoEntity> LOGO_RATP_1951_1960 = MTRFRARegistry.registerEntity(
            "ratp_logo_1951-1960", LogoEntity::new, 1.0f, 1.0f
    );
    public static final EntityTypeRegistryObject<LogoEntity> LOGO_RATP_1960_1976 = MTRFRARegistry.registerEntity(
            "ratp_logo_1960-1976", LogoEntity::new, 1.0f, 1.0f
    );
    public static final EntityTypeRegistryObject<LogoEntity> LOGO_RATP_1976_1992 = MTRFRARegistry.registerEntity(
            "ratp_logo_1976-1992", LogoEntity::new, 1.0f, 1.0f
    );
    public static final EntityTypeRegistryObject<LogoEntity> LOGO_RATP_ACTUEL = MTRFRARegistry.registerEntity(
            "ratp_logo_actuel", LogoEntity::new, 1.0f, 1.0f
    );

    private ModEntities() {}

    public static void register() {}

    public static void registerClient() {
        MTRFRARegistryClient.REGISTRY_CLIENT.registerEntityRenderer(SEAT, SeatEntityRenderer::new);
        MTRFRARegistryClient.REGISTRY_CLIENT.registerEntityRenderer(LOGO_MTRFRANCEADDON, argument -> new LogoEntityRenderer(argument, () -> ModBlocks.LOGO_MTRFRANCEADDON));
        MTRFRARegistryClient.REGISTRY_CLIENT.registerEntityRenderer(LOGO_SNCF_ACTUEL, argument -> new LogoEntityRenderer(argument, () -> ModBlocks.LOGO_SNCF_ACTUEL));
        MTRFRARegistryClient.REGISTRY_CLIENT.registerEntityRenderer(LOGO_SNCF_1992_2005, argument -> new LogoEntityRenderer(argument, () -> ModBlocks.LOGO_SNCF_1992_2005));
        MTRFRARegistryClient.REGISTRY_CLIENT.registerEntityRenderer(LOGO_SNCF_1985_1992_V1, argument -> new LogoEntityRenderer(argument, () -> ModBlocks.LOGO_SNCF_1985_1992_V1));
        MTRFRARegistryClient.REGISTRY_CLIENT.registerEntityRenderer(LOGO_SNCF_1985_1992_V2, argument -> new LogoEntityRenderer(argument, () -> ModBlocks.LOGO_SNCF_1985_1992_V2));
        MTRFRARegistryClient.REGISTRY_CLIENT.registerEntityRenderer(LOGO_SNCF_1967_1985_V1, argument -> new LogoEntityRenderer(argument, () -> ModBlocks.LOGO_SNCF_1967_1985_V1));
        MTRFRARegistryClient.REGISTRY_CLIENT.registerEntityRenderer(LOGO_SNCF_1967_1985_V2, argument -> new LogoEntityRenderer(argument, () -> ModBlocks.LOGO_SNCF_1967_1985_V2));
        MTRFRARegistryClient.REGISTRY_CLIENT.registerEntityRenderer(LOGO_SNCF_1947_1967, argument -> new LogoEntityRenderer(argument, () -> ModBlocks.LOGO_SNCF_1947_1967));
        MTRFRARegistryClient.REGISTRY_CLIENT.registerEntityRenderer(LOGO_SNCF_1938_1947, argument -> new LogoEntityRenderer(argument, () -> ModBlocks.LOGO_SNCF_1938_1947));
        MTRFRARegistryClient.REGISTRY_CLIENT.registerEntityRenderer(LOGO_RATP_1951_1960, argument -> new LogoEntityRenderer(argument, () -> ModBlocks.LOGO_RATP_1951_1960));
        MTRFRARegistryClient.REGISTRY_CLIENT.registerEntityRenderer(LOGO_RATP_1960_1976, argument -> new LogoEntityRenderer(argument, () -> ModBlocks.LOGO_RATP_1960_1976));
        MTRFRARegistryClient.REGISTRY_CLIENT.registerEntityRenderer(LOGO_RATP_1976_1992, argument -> new LogoEntityRenderer(argument, () -> ModBlocks.LOGO_RATP_1976_1992));
        MTRFRARegistryClient.REGISTRY_CLIENT.registerEntityRenderer(LOGO_RATP_ACTUEL, argument -> new LogoEntityRenderer(argument, () -> ModBlocks.LOGO_RATP_ACTUEL));
    }

}
