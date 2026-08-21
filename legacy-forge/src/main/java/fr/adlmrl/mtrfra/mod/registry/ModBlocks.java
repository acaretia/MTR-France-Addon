package fr.adlmrl.mtrfra.mod.registry;

import fr.adlmrl.mtrfra.mod.block.platform.BlockPlatformLayer;
import fr.adlmrl.mtrfra.mod.block.BlockSystemMap;
import fr.adlmrl.mtrfra.mod.block.BufferBlock;
import fr.adlmrl.mtrfra.mod.block.copycat.CopycatLayerBlock;
import fr.adlmrl.mtrfra.mod.block.copycat.CopycatLayerPlatformBlock;
import fr.adlmrl.mtrfra.mod.block.copycat.CopycatPlatformBlock;
import fr.adlmrl.mtrfra.mod.item.CopycatLayerBlockItem;
import fr.adlmrl.mtrfra.mod.block.sign.ConfigurableSignBlock;
import fr.adlmrl.mtrfra.mod.block.HitboxCompanionBlock;
import fr.adlmrl.mtrfra.mod.block.ticketmachine.IDFMTicketMachine2Block;
import fr.adlmrl.mtrfra.mod.block.ticketmachine.IDFMTicketMachineBlock;
import fr.adlmrl.mtrfra.mod.block.platform.InvisiblePlatform;
import fr.adlmrl.mtrfra.mod.block.platform.InvisibleSlabPlatform;
import fr.adlmrl.mtrfra.mod.block.LogoBlock;
import fr.adlmrl.mtrfra.mod.block.SeatBlock;
import fr.adlmrl.mtrfra.mod.block.platform.SittablePlatformSlab;
import fr.adlmrl.mtrfra.mod.block.sign.MotteLightBlock;
import fr.adlmrl.mtrfra.mod.block.sign.MotteLightStationColorBlock;
import fr.adlmrl.mtrfra.mod.item.MotteLightStationColorBlockItem;
import fr.adlmrl.mtrfra.mod.block.sign.RATPCurvedPlatformSign;
import fr.adlmrl.mtrfra.mod.block.sign.RATPDoorCloseSign;
import fr.adlmrl.mtrfra.mod.block.sign.RATPFireExitSign;
import fr.adlmrl.mtrfra.mod.block.sign.RATPNoSmokingSign;
import fr.adlmrl.mtrfra.mod.block.barrier.RATPTicketBarrierBlock;
import fr.adlmrl.mtrfra.mod.block.barrier.RATPTicketBarrierSideCoverBlock;
import fr.adlmrl.mtrfra.mod.block.barrier.RATPTicketBarrierUpperBlock;
import fr.adlmrl.mtrfra.mod.block.base.HangingSignBlock;
import org.mtr.mapping.holder.Block;
import org.mtr.mapping.mapper.BlockExtension;
import org.mtr.mapping.mapper.BlockHelper;
import org.mtr.mapping.registry.BlockRegistryObject;
import org.mtr.mod.Blocks;
import org.mtr.mod.block.BlockPlatform;

public final class ModBlocks {

    public static final BlockRegistryObject PLATFORM_LAYER_SNCF = MTRFRARegistry.registerBlockWithItem(
            "platform_layer_sncf",
            () -> new Block(new BlockPlatformLayer(BlockHelper.createBlockSettings(false, false).strength(0.2f).nonOpaque().dynamicBounds())),
            ModItemGroups.RAIL_CONNECTORS
    );
    public static final BlockRegistryObject PLATFORM_LAYER_SNCF2 = MTRFRARegistry.registerBlockWithItem(
            "platform_layer_sncf2",
            () -> new Block(new BlockPlatformLayer(BlockHelper.createBlockSettings(false, false).strength(0.2f).nonOpaque().dynamicBounds())),
            ModItemGroups.RAIL_CONNECTORS
    );
    public static final BlockRegistryObject PLATFORM_LAYER_RATP = MTRFRARegistry.registerBlockWithItem(
            "platform_layer_ratp",
            () -> new Block(new BlockPlatformLayer(BlockHelper.createBlockSettings(false, false).strength(0.2f).nonOpaque().dynamicBounds())),
            ModItemGroups.RAIL_CONNECTORS
    );

    public static final BlockRegistryObject SNCF_BUFFER = MTRFRARegistry.registerBlockWithItem(
            "sncf_buffer",
            () -> new Block(new BufferBlock(Blocks.createDefaultBlockSettings(false))),
            ModItemGroups.STATION_EQUIPMENT
    );

    public static final BlockRegistryObject INVISIBLE_SLAB_PLATFORM = MTRFRARegistry.registerBlockWithItem(
            "invisible_slab_platform",
            () -> new Block(new InvisibleSlabPlatform(Blocks.createDefaultBlockSettings(false).strength(-1.0F, 3600000.0F).dropsNothing())),
            ModItemGroups.STATION_EQUIPMENT
    );
    public static final BlockRegistryObject INVISIBLE_PLATFORM = MTRFRARegistry.registerBlockWithItem(
            "invisible_platform",
            () -> new Block(new InvisiblePlatform(Blocks.createDefaultBlockSettings(false).strength(-1.0F, 3600000.0F).dropsNothing())),
            ModItemGroups.STATION_EQUIPMENT
    );

    public static final BlockRegistryObject SNCF_PLATFORM = MTRFRARegistry.registerBlockWithItem(
            "sncf_platform", () -> new Block(new BlockPlatform(Blocks.createDefaultBlockSettings(false), false)), ModItemGroups.STATION_EQUIPMENT
    );
    public static final BlockRegistryObject SNCF_PLATFORM_SLAB = MTRFRARegistry.registerBlockWithItem(
            "sncf_platform_slab", () -> new Block(new SittablePlatformSlab(Blocks.createDefaultBlockSettings(false))), ModItemGroups.STATION_EQUIPMENT
    );
    public static final BlockRegistryObject SNCF2_PLATFORM = MTRFRARegistry.registerBlockWithItem(
            "sncf2_platform", () -> new Block(new BlockPlatform(Blocks.createDefaultBlockSettings(false), false)), ModItemGroups.STATION_EQUIPMENT
    );
    public static final BlockRegistryObject SNCF2_PLATFORM_SLAB = MTRFRARegistry.registerBlockWithItem(
            "sncf2_platform_slab", () -> new Block(new SittablePlatformSlab(Blocks.createDefaultBlockSettings(false))), ModItemGroups.STATION_EQUIPMENT
    );
    public static final BlockRegistryObject RATP_PLATFORM = MTRFRARegistry.registerBlockWithItem(
            "ratp_platform", () -> new Block(new BlockPlatform(Blocks.createDefaultBlockSettings(false), false)), ModItemGroups.STATION_EQUIPMENT
    );
    public static final BlockRegistryObject RATP_PLATFORM_SLAB = MTRFRARegistry.registerBlockWithItem(
            "ratp_platform_slab", () -> new Block(new SittablePlatformSlab(Blocks.createDefaultBlockSettings(false))), ModItemGroups.STATION_EQUIPMENT
    );

    public static final BlockRegistryObject HITBOX_COMPANION = MTRFRARegistry.registerBlock(
            "hitbox_companion", () -> new Block(new HitboxCompanionBlock())
    );

    public static final BlockRegistryObject IDFM_TICKET_MACHINE = MTRFRARegistry.registerBlock(
            "idfm_ticket_machine", () -> new Block(new IDFMTicketMachineBlock())
    );
    public static final BlockRegistryObject IDFM_TICKET_MACHINE_2 = MTRFRARegistry.registerBlock(
            "idfm_ticket_machine_2", () -> new Block(new IDFMTicketMachine2Block())
    );

    public static final BlockRegistryObject GREEN_VENDING_MACHINE = MTRFRARegistry.registerBlock(
            "vending_machine_green", () -> new Block(new BlockExtension(Blocks.createDefaultBlockSettings(true).nonOpaque()))
    );
    public static final BlockRegistryObject RED_VENDING_MACHINE = MTRFRARegistry.registerBlock(
            "vending_machine_red", () -> new Block(new BlockExtension(Blocks.createDefaultBlockSettings(true).nonOpaque()))
    );

    public static final BlockRegistryObject COPYCAT_LAYER = MTRFRARegistry.registerBlockWithItem(
            "copycat_layer",
            () -> new Block(new CopycatLayerBlock(BlockHelper.createBlockSettings(false, false).strength(0.2f).nonOpaque().dynamicBounds())),
            CopycatLayerBlockItem::new,
            ModItemGroups.BUILDING_MATERIALS
    );
    public static final BlockRegistryObject COPYCAT_LAYER_PLACEHOLDER = MTRFRARegistry.registerBlock(
            "copycat_layer_placeholder", () -> new Block(new BlockExtension(Blocks.createDefaultBlockSettings(true)))
    );
    public static final BlockRegistryObject COPYCAT_PLATFORM = MTRFRARegistry.registerBlockWithItem(
            "copycat_platform",
            () -> new Block(new CopycatPlatformBlock(BlockHelper.createBlockSettings(false, false).strength(0.2f).nonOpaque().dynamicBounds())),
            CopycatLayerBlockItem::new,
            ModItemGroups.BUILDING_MATERIALS
    );
    public static final BlockRegistryObject COPYCAT_LAYER_PLATFORM = MTRFRARegistry.registerBlockWithItem(
            "copycat_layer_platform",
            () -> new Block(new CopycatLayerPlatformBlock(BlockHelper.createBlockSettings(false, false).strength(0.2f).nonOpaque().dynamicBounds())),
            CopycatLayerBlockItem::new,
            ModItemGroups.BUILDING_MATERIALS
    );

    public static final BlockRegistryObject MOTTE_LIGHT = MTRFRARegistry.registerBlockWithItem(
            "motte_light", () -> new Block(new MotteLightBlock(Blocks.createDefaultBlockSettings(false).nonOpaque(), 0, 0, 4, 16, 15, 12)), ModItemGroups.STATION_EQUIPMENT
    );
    public static final BlockRegistryObject MOTTE_LIGHT_STATION_COLOR = MTRFRARegistry.registerBlockWithItem(
            "motte_light_station_color",
            () -> new Block(new MotteLightStationColorBlock(Blocks.createDefaultBlockSettings(false).nonOpaque(), 0, 0, 4, 16, 15, 12)),
            MotteLightStationColorBlockItem::new,
            ModItemGroups.STATION_EQUIPMENT
    );

    public static final BlockRegistryObject RATP_ALARM_SIGN = MTRFRARegistry.registerBlockWithItem(
            "ratp_alarm_sign", () -> new Block(new HangingSignBlock(Blocks.createDefaultBlockSettings(false))), ModItemGroups.STATION_EQUIPMENT
    );
    public static final BlockRegistryObject RATP_DOORCLOSE_SIGN = MTRFRARegistry.registerBlockWithItem(
            "ratp_doorclose_sign", () -> new Block(new RATPDoorCloseSign(Blocks.createDefaultBlockSettings(false))), ModItemGroups.STATION_EQUIPMENT
    );
    public static final BlockRegistryObject RATP_FIREEXIT_SIGN = MTRFRARegistry.registerBlockWithItem(
            "ratp_fireexit_sign", () -> new Block(new RATPFireExitSign(Blocks.createDefaultBlockSettings(false))), ModItemGroups.STATION_EQUIPMENT
    );
    public static final BlockRegistryObject RATP_CURVEDPLATFORM_SIGN = MTRFRARegistry.registerBlockWithItem(
            "ratp_curvedplatform_sign", () -> new Block(new RATPCurvedPlatformSign(Blocks.createDefaultBlockSettings(false))), ModItemGroups.STATION_EQUIPMENT
    );
    public static final BlockRegistryObject RATP_NOSMOKING_SIGN = MTRFRARegistry.registerBlockWithItem(
            "ratp_nosmoking_sign", () -> new Block(new RATPNoSmokingSign(Blocks.createDefaultBlockSettings(false))), ModItemGroups.STATION_EQUIPMENT
    );

    public static final BlockRegistryObject RATP_TICKET_BARRIER_SIDE_COVER = MTRFRARegistry.registerBlockWithItem(
            "ratp_ticket_barrier_side_cover", () -> new Block(new RATPTicketBarrierSideCoverBlock()), ModItemGroups.STATION_EQUIPMENT
    );
    public static final BlockRegistryObject RATP_TICKET_BARRIER_UPPER = MTRFRARegistry.registerBlock(
            "ratp_ticket_barrier_upper", () -> new Block(new RATPTicketBarrierUpperBlock())
    );
    public static final BlockRegistryObject RATP_TICKET_BARRIER_ENTRANCE = MTRFRARegistry.registerBlockWithItem(
            "ratp_ticket_barrier_entrance", () -> new Block(new RATPTicketBarrierBlock(true)), ModItemGroups.STATION_EQUIPMENT
    );
    public static final BlockRegistryObject RATP_TICKET_BARRIER_EXIT = MTRFRARegistry.registerBlockWithItem(
            "ratp_ticket_barrier_exit", () -> new Block(new RATPTicketBarrierBlock(false)), ModItemGroups.STATION_EQUIPMENT
    );
    public static final BlockRegistryObject RATP_TICKET_BARRIER_ENTRANCE_WITH_SIDE = MTRFRARegistry.registerBlockWithItem(
            "ratp_ticket_barrier_entrance_with_side", () -> new Block(new RATPTicketBarrierBlock(true, true)), ModItemGroups.STATION_EQUIPMENT
    );
    public static final BlockRegistryObject RATP_TICKET_BARRIER_EXIT_WITH_SIDE = MTRFRARegistry.registerBlockWithItem(
            "ratp_ticket_barrier_exit_with_side", () -> new Block(new RATPTicketBarrierBlock(false, true)), ModItemGroups.STATION_EQUIPMENT
    );

    public static final BlockRegistryObject SYSTEM_MAP = MTRFRARegistry.registerBlockWithItem(
            "system_map", () -> new Block(new BlockSystemMap(Blocks.createDefaultBlockSettings(false))), ModItemGroups.STATION_EQUIPMENT
    );

    public static final BlockRegistryObject LOGO_MTRFRANCEADDON = MTRFRARegistry.registerBlock(
            "mtrfranceaddon_logo_block", () -> new Block(new LogoBlock(Blocks.createDefaultBlockSettings(false).strength(0.2f)))
    );
    public static final BlockRegistryObject LOGO_SNCF_ACTUEL = MTRFRARegistry.registerBlock(
            "sncf_logo_actuel_block", () -> new Block(new LogoBlock(Blocks.createDefaultBlockSettings(false).strength(0.2f)))
    );
    public static final BlockRegistryObject LOGO_SNCF_1992_2005 = MTRFRARegistry.registerBlock(
            "sncf_logo_1992-2005_block", () -> new Block(new LogoBlock(Blocks.createDefaultBlockSettings(false).strength(0.2f)))
    );
    public static final BlockRegistryObject LOGO_SNCF_1985_1992_V1 = MTRFRARegistry.registerBlock(
            "sncf_logo_1985-1992_block_v1", () -> new Block(new LogoBlock(Blocks.createDefaultBlockSettings(false).strength(0.2f)))
    );
    public static final BlockRegistryObject LOGO_SNCF_1985_1992_V2 = MTRFRARegistry.registerBlock(
            "sncf_logo_1985-1992_block_v2", () -> new Block(new LogoBlock(Blocks.createDefaultBlockSettings(false).strength(0.2f)))
    );
    public static final BlockRegistryObject LOGO_SNCF_1967_1985_V1 = MTRFRARegistry.registerBlock(
            "sncf_logo_1967-1985_block_v1", () -> new Block(new LogoBlock(Blocks.createDefaultBlockSettings(false).strength(0.2f)))
    );
    public static final BlockRegistryObject LOGO_SNCF_1967_1985_V2 = MTRFRARegistry.registerBlock(
            "sncf_logo_1967-1985_block_v2", () -> new Block(new LogoBlock(Blocks.createDefaultBlockSettings(false).strength(0.2f)))
    );
    public static final BlockRegistryObject LOGO_SNCF_1947_1967 = MTRFRARegistry.registerBlock(
            "sncf_logo_1947-1967_block", () -> new Block(new LogoBlock(Blocks.createDefaultBlockSettings(false).strength(0.2f)))
    );
    public static final BlockRegistryObject LOGO_SNCF_1938_1947 = MTRFRARegistry.registerBlock(
            "sncf_logo_1938-1947_block", () -> new Block(new LogoBlock(Blocks.createDefaultBlockSettings(false).strength(0.2f)))
    );
    public static final BlockRegistryObject LOGO_RATP_1951_1960 = MTRFRARegistry.registerBlock(
            "ratp_logo_1951-1960_block", () -> new Block(new LogoBlock(Blocks.createDefaultBlockSettings(false).strength(0.2f)))
    );
    public static final BlockRegistryObject LOGO_RATP_1960_1976 = MTRFRARegistry.registerBlock(
            "ratp_logo_1960-1976_block", () -> new Block(new LogoBlock(Blocks.createDefaultBlockSettings(false).strength(0.2f)))
    );
    public static final BlockRegistryObject LOGO_RATP_1976_1992 = MTRFRARegistry.registerBlock(
            "ratp_logo_1976-1992_block", () -> new Block(new LogoBlock(Blocks.createDefaultBlockSettings(false).strength(0.2f)))
    );
    public static final BlockRegistryObject LOGO_RATP_ACTUEL = MTRFRARegistry.registerBlock(
            "ratp_logo_actuel_block", () -> new Block(new LogoBlock(Blocks.createDefaultBlockSettings(false).strength(0.2f)))
    );
    public static final BlockRegistryObject LOGO_SNCF_SIGN = MTRFRARegistry.registerBlock(
            "sncf_sign_block", () -> new Block(new LogoBlock(Blocks.createDefaultBlockSettings(false).strength(0.2f)))
    );

    public static final BlockRegistryObject POLE = MTRFRARegistry.registerBlockWithItem(
            "pole", () -> new Block(new ConfigurableSignBlock(Blocks.createDefaultBlockSettings(false), 7, 0, 7, 9, 16, 9)), ModItemGroups.SIGNS
    );
    public static final BlockRegistryObject SIGN_SPEED_20 = MTRFRARegistry.registerBlockWithItem(
            "sign_speed_20", () -> new Block(new ConfigurableSignBlock(Blocks.createDefaultBlockSettings(false), 5, 0, 5, 11, 16, 11)), ModItemGroups.SIGNS
    );
    public static final BlockRegistryObject SIGN_SPEED_40 = MTRFRARegistry.registerBlockWithItem(
            "sign_speed_40", () -> new Block(new ConfigurableSignBlock(Blocks.createDefaultBlockSettings(false), 5, 0, 5, 11, 16, 11)), ModItemGroups.SIGNS
    );
    public static final BlockRegistryObject SIGN_SPEED_60 = MTRFRARegistry.registerBlockWithItem(
            "sign_speed_60", () -> new Block(new ConfigurableSignBlock(Blocks.createDefaultBlockSettings(false), 5, 0, 5, 11, 16, 11)), ModItemGroups.SIGNS
    );
    public static final BlockRegistryObject SIGN_SPEED_80 = MTRFRARegistry.registerBlockWithItem(
            "sign_speed_80", () -> new Block(new ConfigurableSignBlock(Blocks.createDefaultBlockSettings(false), 5, 0, 5, 11, 16, 11)), ModItemGroups.SIGNS
    );
    public static final BlockRegistryObject SIGN_SPEED_120 = MTRFRARegistry.registerBlockWithItem(
            "sign_speed_120", () -> new Block(new ConfigurableSignBlock(Blocks.createDefaultBlockSettings(false), 5, 0, 5, 11, 16, 11)), ModItemGroups.SIGNS
    );
    public static final BlockRegistryObject SIGN_SPEED_160 = MTRFRARegistry.registerBlockWithItem(
            "sign_speed_160", () -> new Block(new ConfigurableSignBlock(Blocks.createDefaultBlockSettings(false), 5, 0, 5, 11, 16, 11)), ModItemGroups.SIGNS
    );
    public static final BlockRegistryObject SIGN_SPEED_200 = MTRFRARegistry.registerBlockWithItem(
            "sign_speed_200", () -> new Block(new ConfigurableSignBlock(Blocks.createDefaultBlockSettings(false), 5, 0, 5, 11, 16, 11)), ModItemGroups.SIGNS
    );
    public static final BlockRegistryObject SIGN_SPEED_300 = MTRFRARegistry.registerBlockWithItem(
            "sign_speed_300", () -> new Block(new ConfigurableSignBlock(Blocks.createDefaultBlockSettings(false), 5, 0, 5, 11, 16, 11)), ModItemGroups.SIGNS
    );
    public static final BlockRegistryObject SIGN_DEPOT = MTRFRARegistry.registerBlockWithItem(
            "sign_depot", () -> new Block(new ConfigurableSignBlock(Blocks.createDefaultBlockSettings(false), 5, 0, 5, 11, 16, 11)), ModItemGroups.SIGNS
    );
    public static final BlockRegistryObject SIGN_ONE_WAY_CORRECT = MTRFRARegistry.registerBlockWithItem(
            "sign_one_way_correct", () -> new Block(new ConfigurableSignBlock(Blocks.createDefaultBlockSettings(false), 5, 0, 5, 11, 16, 11)), ModItemGroups.SIGNS
    );
    public static final BlockRegistryObject SIGN_ONE_WAY_WRONG = MTRFRARegistry.registerBlockWithItem(
            "sign_one_way_wrong", () -> new Block(new ConfigurableSignBlock(Blocks.createDefaultBlockSettings(false), 5, 0, 5, 11, 16, 11)), ModItemGroups.SIGNS
    );
    public static final BlockRegistryObject SIGN_END_OF_TRACK = MTRFRARegistry.registerBlockWithItem(
            "sign_end_of_track", () -> new Block(new ConfigurableSignBlock(Blocks.createDefaultBlockSettings(false), 5, 0, 5, 11, 16, 11)), ModItemGroups.SIGNS
    );
    public static final BlockRegistryObject SIGN_LEVEL_CROSSING = MTRFRARegistry.registerBlockWithItem(
            "sign_level_crossing", () -> new Block(new ConfigurableSignBlock(Blocks.createDefaultBlockSettings(false), 5, 0, 5, 11, 16, 11)), ModItemGroups.SIGNS
    );
    public static final BlockRegistryObject SIGN_SQUARE = MTRFRARegistry.registerBlockWithItem(
            "sign_square", () -> new Block(new ConfigurableSignBlock(Blocks.createDefaultBlockSettings(false), 5, 0, 5, 11, 16, 11)), ModItemGroups.SIGNS
    );
    public static final BlockRegistryObject SIGN_STATION = MTRFRARegistry.registerBlockWithItem(
            "sign_station", () -> new Block(new ConfigurableSignBlock(Blocks.createDefaultBlockSettings(false), 5, 0, 5, 11, 16, 11)), ModItemGroups.SIGNS
    );
    public static final BlockRegistryObject SIGN_CARS_4 = MTRFRARegistry.registerBlockWithItem(
            "sign_cars_4", () -> new Block(new ConfigurableSignBlock(Blocks.createDefaultBlockSettings(false), 5, 0, 5, 11, 16, 11)), ModItemGroups.SIGNS
    );
    public static final BlockRegistryObject SIGN_CARS_6 = MTRFRARegistry.registerBlockWithItem(
            "sign_cars_6", () -> new Block(new ConfigurableSignBlock(Blocks.createDefaultBlockSettings(false), 5, 0, 5, 11, 16, 11)), ModItemGroups.SIGNS
    );
    public static final BlockRegistryObject SIGN_CARS_8 = MTRFRARegistry.registerBlockWithItem(
            "sign_cars_8", () -> new Block(new ConfigurableSignBlock(Blocks.createDefaultBlockSettings(false), 5, 0, 5, 11, 16, 11)), ModItemGroups.SIGNS
    );
    public static final BlockRegistryObject SIGN_CARS_10 = MTRFRARegistry.registerBlockWithItem(
            "sign_cars_10", () -> new Block(new ConfigurableSignBlock(Blocks.createDefaultBlockSettings(false), 5, 0, 5, 11, 16, 11)), ModItemGroups.SIGNS
    );
    public static final BlockRegistryObject SIGN_CARS_12 = MTRFRARegistry.registerBlockWithItem(
            "sign_cars_12", () -> new Block(new ConfigurableSignBlock(Blocks.createDefaultBlockSettings(false), 5, 0, 5, 11, 16, 11)), ModItemGroups.SIGNS
    );
    public static final BlockRegistryObject SIGN_CARS_14 = MTRFRARegistry.registerBlockWithItem(
            "sign_cars_14", () -> new Block(new ConfigurableSignBlock(Blocks.createDefaultBlockSettings(false), 5, 0, 5, 11, 16, 11)), ModItemGroups.SIGNS
    );
    public static final BlockRegistryObject SIGN_CARS_16 = MTRFRARegistry.registerBlockWithItem(
            "sign_cars_16", () -> new Block(new ConfigurableSignBlock(Blocks.createDefaultBlockSettings(false), 5, 0, 5, 11, 16, 11)), ModItemGroups.SIGNS
    );
    public static final BlockRegistryObject SIGN_PLATFORM_LEFT = MTRFRARegistry.registerBlockWithItem(
            "sign_platform_left", () -> new Block(new ConfigurableSignBlock(Blocks.createDefaultBlockSettings(false), 5, 0, 5, 11, 16, 11)), ModItemGroups.SIGNS
    );
    public static final BlockRegistryObject SIGN_PLATFORM_RIGHT = MTRFRARegistry.registerBlockWithItem(
            "sign_platform_right", () -> new Block(new ConfigurableSignBlock(Blocks.createDefaultBlockSettings(false), 5, 0, 5, 11, 16, 11)), ModItemGroups.SIGNS
    );
    public static final BlockRegistryObject SIGN_NO_TUNNEL_VENT = MTRFRARegistry.registerBlockWithItem(
            "sign_no_tunnel_vent", () -> new Block(new ConfigurableSignBlock(Blocks.createDefaultBlockSettings(false), 5, 0, 5, 11, 16, 11)), ModItemGroups.SIGNS
    );
    public static final BlockRegistryObject SIGN_S_CURVE_LEFT = MTRFRARegistry.registerBlockWithItem(
            "sign_s_curve_left", () -> new Block(new ConfigurableSignBlock(Blocks.createDefaultBlockSettings(false), 5, 0, 5, 11, 16, 11)), ModItemGroups.SIGNS
    );
    public static final BlockRegistryObject SIGN_S_CURVE_RIGHT = MTRFRARegistry.registerBlockWithItem(
            "sign_s_curve_right", () -> new Block(new ConfigurableSignBlock(Blocks.createDefaultBlockSettings(false), 5, 0, 5, 11, 16, 11)), ModItemGroups.SIGNS
    );
    public static final BlockRegistryObject SIGN_TUNNEL = MTRFRARegistry.registerBlockWithItem(
            "sign_tunnel", () -> new Block(new ConfigurableSignBlock(Blocks.createDefaultBlockSettings(false), 5, 0, 5, 11, 16, 11)), ModItemGroups.SIGNS
    );
    public static final BlockRegistryObject SIGN_WHISTLE = MTRFRARegistry.registerBlockWithItem(
            "sign_whistle", () -> new Block(new ConfigurableSignBlock(Blocks.createDefaultBlockSettings(false), 5, 0, 5, 11, 16, 11)), ModItemGroups.SIGNS
    );
    public static final BlockRegistryObject SIGN_SWITCH_100M = MTRFRARegistry.registerBlockWithItem(
            "sign_switch_100m", () -> new Block(new ConfigurableSignBlock(Blocks.createDefaultBlockSettings(false), 5, 0, 5, 11, 16, 11)), ModItemGroups.SIGNS
    );
    public static final BlockRegistryObject SIGN_SWITCH_200M = MTRFRARegistry.registerBlockWithItem(
            "sign_switch_200m", () -> new Block(new ConfigurableSignBlock(Blocks.createDefaultBlockSettings(false), 5, 0, 5, 11, 16, 11)), ModItemGroups.SIGNS
    );
    public static final BlockRegistryObject SIGN_SWITCH_300M = MTRFRARegistry.registerBlockWithItem(
            "sign_switch_300m", () -> new Block(new ConfigurableSignBlock(Blocks.createDefaultBlockSettings(false), 5, 0, 5, 11, 16, 11)), ModItemGroups.SIGNS
    );
    public static final BlockRegistryObject SIGN_ONE_WAY_CORRECT_GROUND = MTRFRARegistry.registerBlockWithItem(
            "sign_one_way_correct_ground", () -> new Block(new ConfigurableSignBlock(Blocks.createDefaultBlockSettings(false), 1, 0, 1, 14, 7, 14)), ModItemGroups.SIGNS
    );
    public static final BlockRegistryObject SIGN_ONE_WAY_WRONG_GROUND = MTRFRARegistry.registerBlockWithItem(
            "sign_one_way_wrong_ground", () -> new Block(new ConfigurableSignBlock(Blocks.createDefaultBlockSettings(false), 1, 0, 1, 14, 7, 14)), ModItemGroups.SIGNS
    );
    public static final BlockRegistryObject SIGN_SPEED_20_GROUND = MTRFRARegistry.registerBlockWithItem(
            "sign_speed_20_ground", () -> new Block(new ConfigurableSignBlock(Blocks.createDefaultBlockSettings(false), 1, 0, 1, 14, 7, 14)), ModItemGroups.SIGNS
    );
    public static final BlockRegistryObject SIGN_SPEED_40_GROUND = MTRFRARegistry.registerBlockWithItem(
            "sign_speed_40_ground", () -> new Block(new ConfigurableSignBlock(Blocks.createDefaultBlockSettings(false), 1, 0, 1, 14, 7, 14)), ModItemGroups.SIGNS
    );
    public static final BlockRegistryObject SIGN_SPEED_60_GROUND = MTRFRARegistry.registerBlockWithItem(
            "sign_speed_60_ground", () -> new Block(new ConfigurableSignBlock(Blocks.createDefaultBlockSettings(false), 1, 0, 1, 14, 7, 14)), ModItemGroups.SIGNS
    );
    public static final BlockRegistryObject SIGN_SPEED_80_GROUND = MTRFRARegistry.registerBlockWithItem(
            "sign_speed_80_ground", () -> new Block(new ConfigurableSignBlock(Blocks.createDefaultBlockSettings(false), 1, 0, 1, 14, 7, 14)), ModItemGroups.SIGNS
    );
    public static final BlockRegistryObject SIGN_SPEED_120_GROUND = MTRFRARegistry.registerBlockWithItem(
            "sign_speed_120_ground", () -> new Block(new ConfigurableSignBlock(Blocks.createDefaultBlockSettings(false), 1, 0, 1, 14, 7, 14)), ModItemGroups.SIGNS
    );
    public static final BlockRegistryObject SIGN_SPEED_160_GROUND = MTRFRARegistry.registerBlockWithItem(
            "sign_speed_160_ground", () -> new Block(new ConfigurableSignBlock(Blocks.createDefaultBlockSettings(false), 1, 0, 1, 14, 7, 14)), ModItemGroups.SIGNS
    );
    public static final BlockRegistryObject SIGN_SPEED_200_GROUND = MTRFRARegistry.registerBlockWithItem(
            "sign_speed_200_ground", () -> new Block(new ConfigurableSignBlock(Blocks.createDefaultBlockSettings(false), 1, 0, 1, 14, 7, 14)), ModItemGroups.SIGNS
    );
    public static final BlockRegistryObject SIGN_SPEED_300_GROUND = MTRFRARegistry.registerBlockWithItem(
            "sign_speed_300_ground", () -> new Block(new ConfigurableSignBlock(Blocks.createDefaultBlockSettings(false), 1, 0, 1, 14, 7, 14)), ModItemGroups.SIGNS
    );
    public static final BlockRegistryObject SIGN_DEPOT_GROUND = MTRFRARegistry.registerBlockWithItem(
            "sign_depot_ground", () -> new Block(new ConfigurableSignBlock(Blocks.createDefaultBlockSettings(false), 1, 0, 1, 14, 7, 14)), ModItemGroups.SIGNS
    );
    public static final BlockRegistryObject SIGN_END_OF_TRACK_GROUND = MTRFRARegistry.registerBlockWithItem(
            "sign_end_of_track_ground", () -> new Block(new ConfigurableSignBlock(Blocks.createDefaultBlockSettings(false), 1, 0, 1, 14, 7, 14)), ModItemGroups.SIGNS
    );
    public static final BlockRegistryObject SIGN_S_CURVE_LEFT_GROUND = MTRFRARegistry.registerBlockWithItem(
            "sign_s_curve_left_ground", () -> new Block(new ConfigurableSignBlock(Blocks.createDefaultBlockSettings(false), 1, 0, 1, 14, 7, 14)), ModItemGroups.SIGNS
    );
    public static final BlockRegistryObject SIGN_S_CURVE_RIGHT_GROUND = MTRFRARegistry.registerBlockWithItem(
            "sign_s_curve_right_ground", () -> new Block(new ConfigurableSignBlock(Blocks.createDefaultBlockSettings(false), 1, 0, 1, 14, 7, 14)), ModItemGroups.SIGNS
    );
    public static final BlockRegistryObject SIGN_LEVEL_CROSSING_GROUND = MTRFRARegistry.registerBlockWithItem(
            "sign_level_crossing_ground", () -> new Block(new ConfigurableSignBlock(Blocks.createDefaultBlockSettings(false), 1, 0, 1, 14, 7, 14)), ModItemGroups.SIGNS
    );
    public static final BlockRegistryObject SIGN_SQUARE_GROUND = MTRFRARegistry.registerBlockWithItem(
            "sign_square_ground", () -> new Block(new ConfigurableSignBlock(Blocks.createDefaultBlockSettings(false), 1, 0, 1, 14, 7, 14)), ModItemGroups.SIGNS
    );
    public static final BlockRegistryObject SIGN_STATION_GROUND = MTRFRARegistry.registerBlockWithItem(
            "sign_station_ground", () -> new Block(new ConfigurableSignBlock(Blocks.createDefaultBlockSettings(false), 1, 0, 1, 14, 7, 14)), ModItemGroups.SIGNS
    );
    public static final BlockRegistryObject SIGN_NO_TUNNEL_VENT_GROUND = MTRFRARegistry.registerBlockWithItem(
            "sign_no_tunnel_vent_ground", () -> new Block(new ConfigurableSignBlock(Blocks.createDefaultBlockSettings(false), 1, 0, 1, 14, 7, 14)), ModItemGroups.SIGNS
    );
    public static final BlockRegistryObject SIGN_TUNNEL_GROUND = MTRFRARegistry.registerBlockWithItem(
            "sign_tunnel_ground", () -> new Block(new ConfigurableSignBlock(Blocks.createDefaultBlockSettings(false), 1, 0, 1, 14, 7, 14)), ModItemGroups.SIGNS
    );
    public static final BlockRegistryObject SIGN_WHISTLE_GROUND = MTRFRARegistry.registerBlockWithItem(
            "sign_whistle_ground", () -> new Block(new ConfigurableSignBlock(Blocks.createDefaultBlockSettings(false), 1, 0, 1, 14, 7, 14)), ModItemGroups.SIGNS
    );
    public static final BlockRegistryObject SIGN_SWITCH_100M_GROUND = MTRFRARegistry.registerBlockWithItem(
            "sign_switch_100m_ground", () -> new Block(new ConfigurableSignBlock(Blocks.createDefaultBlockSettings(false), 1, 0, 1, 14, 7, 14)), ModItemGroups.SIGNS
    );
    public static final BlockRegistryObject SIGN_SWITCH_200M_GROUND = MTRFRARegistry.registerBlockWithItem(
            "sign_switch_200m_ground", () -> new Block(new ConfigurableSignBlock(Blocks.createDefaultBlockSettings(false), 1, 0, 1, 14, 7, 14)), ModItemGroups.SIGNS
    );
    public static final BlockRegistryObject SIGN_SWITCH_300M_GROUND = MTRFRARegistry.registerBlockWithItem(
            "sign_switch_300m_ground", () -> new Block(new ConfigurableSignBlock(Blocks.createDefaultBlockSettings(false), 1, 0, 1, 14, 7, 14)), ModItemGroups.SIGNS
    );
    public static final BlockRegistryObject POLE_CONNECTION_LEFT = MTRFRARegistry.registerBlockWithItem(
            "pole_connection_left", () -> new Block(new ConfigurableSignBlock(Blocks.createDefaultBlockSettings(false), 1, 1, 1, 15, 15, 15)), ModItemGroups.SIGNS
    );
    public static final BlockRegistryObject POLE_CONNECTION_RIGHT = MTRFRARegistry.registerBlockWithItem(
            "pole_connection_right", () -> new Block(new ConfigurableSignBlock(Blocks.createDefaultBlockSettings(false), 1, 1, 1, 15, 15, 15)), ModItemGroups.SIGNS
    );
    public static final BlockRegistryObject POLE_CONNECTION_HORIZONTAL = MTRFRARegistry.registerBlockWithItem(
            "pole_connection_horizontal", () -> new Block(new ConfigurableSignBlock(Blocks.createDefaultBlockSettings(false), 1, 1, 1, 15, 15, 15)), ModItemGroups.SIGNS
    );
    public static final BlockRegistryObject POLE_CONNECTION_MIDDLE = MTRFRARegistry.registerBlockWithItem(
            "pole_connection_middle", () -> new Block(new ConfigurableSignBlock(Blocks.createDefaultBlockSettings(false), 1, 1, 1, 15, 15, 15)), ModItemGroups.SIGNS
    );
    public static final BlockRegistryObject SIGN_SPEED_20_GALLOWS = MTRFRARegistry.registerBlockWithItem(
            "sign_speed_20_gallows", () -> new Block(new ConfigurableSignBlock(Blocks.createDefaultBlockSettings(false), 1, 1, 1, 15, 15, 15)), ModItemGroups.SIGNS
    );
    public static final BlockRegistryObject SIGN_SPEED_40_GALLOWS = MTRFRARegistry.registerBlockWithItem(
            "sign_speed_40_gallows", () -> new Block(new ConfigurableSignBlock(Blocks.createDefaultBlockSettings(false), 1, 1, 1, 15, 15, 15)), ModItemGroups.SIGNS
    );
    public static final BlockRegistryObject SIGN_SPEED_60_GALLOWS = MTRFRARegistry.registerBlockWithItem(
            "sign_speed_60_gallows", () -> new Block(new ConfigurableSignBlock(Blocks.createDefaultBlockSettings(false), 1, 1, 1, 15, 15, 15)), ModItemGroups.SIGNS
    );
    public static final BlockRegistryObject SIGN_SPEED_80_GALLOWS = MTRFRARegistry.registerBlockWithItem(
            "sign_speed_80_gallows", () -> new Block(new ConfigurableSignBlock(Blocks.createDefaultBlockSettings(false), 1, 1, 1, 15, 15, 15)), ModItemGroups.SIGNS
    );
    public static final BlockRegistryObject SIGN_SPEED_120_GALLOWS = MTRFRARegistry.registerBlockWithItem(
            "sign_speed_120_gallows", () -> new Block(new ConfigurableSignBlock(Blocks.createDefaultBlockSettings(false), 1, 1, 1, 15, 15, 15)), ModItemGroups.SIGNS
    );
    public static final BlockRegistryObject SIGN_SPEED_160_GALLOWS = MTRFRARegistry.registerBlockWithItem(
            "sign_speed_160_gallows", () -> new Block(new ConfigurableSignBlock(Blocks.createDefaultBlockSettings(false), 1, 1, 1, 15, 15, 15)), ModItemGroups.SIGNS
    );
    public static final BlockRegistryObject SIGN_SPEED_200_GALLOWS = MTRFRARegistry.registerBlockWithItem(
            "sign_speed_200_gallows", () -> new Block(new ConfigurableSignBlock(Blocks.createDefaultBlockSettings(false), 1, 1, 1, 15, 15, 15)), ModItemGroups.SIGNS
    );
    public static final BlockRegistryObject SIGN_SPEED_300_GALLOWS = MTRFRARegistry.registerBlockWithItem(
            "sign_speed_300_gallows", () -> new Block(new ConfigurableSignBlock(Blocks.createDefaultBlockSettings(false), 1, 1, 1, 15, 15, 15)), ModItemGroups.SIGNS
    );
    public static final BlockRegistryObject SIGN_SQUARE_GALLOWS = MTRFRARegistry.registerBlockWithItem(
            "sign_square_gallows", () -> new Block(new ConfigurableSignBlock(Blocks.createDefaultBlockSettings(false), 1, 1, 1, 15, 15, 15)), ModItemGroups.SIGNS
    );

    public static final BlockRegistryObject IDFM_SEAT_WITH_POLE = MTRFRARegistry.registerBlockWithItem(
            "idfm_seat_with_pole", () -> new Block(new SeatBlock(Blocks.createDefaultBlockSettings(false).nonOpaque(), 0, 0, 0, 16, 17, 16, 0)), ModItemGroups.STATION_EQUIPMENT
    );
    public static final BlockRegistryObject IDFM_SEAT = MTRFRARegistry.registerBlockWithItem(
            "idfm_seat", () -> new Block(new SeatBlock(Blocks.createDefaultBlockSettings(false).nonOpaque(), 0, 0, 0, 16, 17, 16, 0)), ModItemGroups.STATION_EQUIPMENT
    );
    private ModBlocks() {}

    public static void register() {

    }

}
