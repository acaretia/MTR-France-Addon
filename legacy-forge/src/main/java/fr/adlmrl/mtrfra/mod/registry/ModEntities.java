package fr.adlmrl.mtrfra.mod.registry;

import fr.adlmrl.mtrfra.mod.entity.CushionEntity;
import fr.adlmrl.mtrfra.mod.entity.CushionEntityRenderer;
import fr.adlmrl.mtrfra.mod.entity.LogoEntity;
import fr.adlmrl.mtrfra.mod.entity.LogoEntityRenderer;
import fr.adlmrl.mtrfra.mod.entity.SeatEntity;
import fr.adlmrl.mtrfra.mod.entity.SeatEntityRenderer;
import fr.adlmrl.mtrfra.mod.entity.TicketMachineEntity;
import fr.adlmrl.mtrfra.mod.entity.TicketMachineEntityRenderer;
import fr.adlmrl.mtrfra.mod.entity.VendingMachineEntity;
import fr.adlmrl.mtrfra.mod.entity.VendingMachineEntityRenderer;
import org.mtr.mapping.registry.EntityTypeRegistryObject;

public final class ModEntities {

    public static final EntityTypeRegistryObject<SeatEntity> SEAT = MTRFRARegistry.registerEntity(
            "seat", SeatEntity::new, 0.01f, 0.01f
    );

    public static final EntityTypeRegistryObject<CushionEntity> CUSHION_IDFM_SEAT = MTRFRARegistry.registerEntity(
            "cushion_idfm_seat", CushionEntity::new, 1.0f, 1.0f
    );
    public static final EntityTypeRegistryObject<CushionEntity> CUSHION_IDFM_SEAT_WITH_POLE = MTRFRARegistry.registerEntity(
            "cushion_idfm_seat_with_pole", CushionEntity::new, 1.0f, 1.0f
    );
    public static final EntityTypeRegistryObject<CushionEntity> CUSHION_IDFM_SEAT_STATION_COLOR = MTRFRARegistry.registerEntity(
            "cushion_idfm_seat_station_color", CushionEntity::new, 1.0f, 1.0f
    );
    public static final EntityTypeRegistryObject<CushionEntity> CUSHION_IDFM_SEAT_WITH_POLE_STATION_COLOR = MTRFRARegistry.registerEntity(
            "cushion_idfm_seat_with_pole_station_color", CushionEntity::new, 1.0f, 1.0f
    );

    public static final EntityTypeRegistryObject<VendingMachineEntity> VENDING_MACHINE_GREEN = MTRFRARegistry.registerEntity(
            "vending_machine_green", VendingMachineEntity::new, 1.75f, 2.0f
    );
    public static final EntityTypeRegistryObject<VendingMachineEntity> VENDING_MACHINE_RED = MTRFRARegistry.registerEntity(
            "vending_machine_red", VendingMachineEntity::new, 1.75f, 2.0f
    );

    public static final EntityTypeRegistryObject<TicketMachineEntity> TICKET_MACHINE_IDFM = MTRFRARegistry.registerEntity(
            "ticket_machine_idfm", TicketMachineEntity::new, 1.0f, 1.875f
    );
    public static final EntityTypeRegistryObject<TicketMachineEntity> TICKET_MACHINE_IDFM_WIDE = MTRFRARegistry.registerEntity(
            "ticket_machine_idfm_wide", TicketMachineEntity::new, 1.9f, 1.875f
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
    public static final EntityTypeRegistryObject<LogoEntity> LOGO_SNCF_SIGN = MTRFRARegistry.registerEntity(
            "sncf_sign", LogoEntity::new, 1.0f, 1.0f
    );

    private ModEntities() {}

    public static void register() {}

    public static void registerClient() {
        MTRFRARegistryClient.REGISTRY_CLIENT.registerEntityRenderer(SEAT, SeatEntityRenderer::new);
        MTRFRARegistryClient.REGISTRY_CLIENT.registerEntityRenderer(CUSHION_IDFM_SEAT, argument -> new CushionEntityRenderer(argument, () -> ModBlocks.IDFM_SEAT));
        MTRFRARegistryClient.REGISTRY_CLIENT.registerEntityRenderer(CUSHION_IDFM_SEAT_WITH_POLE, argument -> new CushionEntityRenderer(argument, () -> ModBlocks.IDFM_SEAT_WITH_POLE));
        MTRFRARegistryClient.REGISTRY_CLIENT.registerEntityRenderer(CUSHION_IDFM_SEAT_STATION_COLOR, argument -> new CushionEntityRenderer(argument, () -> ModBlocks.IDFM_SEAT));
        MTRFRARegistryClient.REGISTRY_CLIENT.registerEntityRenderer(CUSHION_IDFM_SEAT_WITH_POLE_STATION_COLOR, argument -> new CushionEntityRenderer(argument, () -> ModBlocks.IDFM_SEAT_WITH_POLE));
        MTRFRARegistryClient.REGISTRY_CLIENT.registerEntityRenderer(VENDING_MACHINE_GREEN, argument -> new VendingMachineEntityRenderer(argument, () -> ModBlocks.GREEN_VENDING_MACHINE));
        MTRFRARegistryClient.REGISTRY_CLIENT.registerEntityRenderer(VENDING_MACHINE_RED, argument -> new VendingMachineEntityRenderer(argument, () -> ModBlocks.RED_VENDING_MACHINE));
        MTRFRARegistryClient.REGISTRY_CLIENT.registerEntityRenderer(TICKET_MACHINE_IDFM, argument -> new TicketMachineEntityRenderer(argument, () -> ModBlocks.IDFM_TICKET_MACHINE));
        MTRFRARegistryClient.REGISTRY_CLIENT.registerEntityRenderer(TICKET_MACHINE_IDFM_WIDE, argument -> new TicketMachineEntityRenderer(argument, () -> ModBlocks.IDFM_TICKET_MACHINE_2));
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
        MTRFRARegistryClient.REGISTRY_CLIENT.registerEntityRenderer(LOGO_SNCF_SIGN, argument -> new LogoEntityRenderer(argument, () -> ModBlocks.LOGO_SNCF_SIGN));
    }

}
