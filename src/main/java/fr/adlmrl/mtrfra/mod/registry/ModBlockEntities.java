package fr.adlmrl.mtrfra.mod.registry;

import fr.adlmrl.mtrfra.mod.block.copycat.CopycatBlockBase;
import fr.adlmrl.mtrfra.mod.block.platform.InvisiblePlatform;
import fr.adlmrl.mtrfra.mod.block.platform.InvisibleSlabPlatform;
import fr.adlmrl.mtrfra.mod.block.sign.MotteLightBlock;
import fr.adlmrl.mtrfra.mod.block.sign.MotteLightStationColorBlock;
import fr.adlmrl.mtrfra.mod.block.barrier.RATPTicketBarrierBlock;
import fr.adlmrl.mtrfra.mod.util.Constants;
import org.mtr.mapping.registry.BlockEntityTypeRegistryObject;

public final class ModBlockEntities {

    public static final BlockEntityTypeRegistryObject<InvisibleSlabPlatform.BlockEntity> INVISIBLE_SLAB_PLATFORM = MTRFRARegistry.REGISTRY.registerBlockEntityType(
            Constants.id("invisible_slab_platform"),
            InvisibleSlabPlatform.BlockEntity::new,
            ModBlocks.INVISIBLE_SLAB_PLATFORM::get
    );

    public static final BlockEntityTypeRegistryObject<InvisiblePlatform.BlockEntity> INVISIBLE_PLATFORM = MTRFRARegistry.REGISTRY.registerBlockEntityType(
            Constants.id("invisible_platform"),
            InvisiblePlatform.BlockEntity::new,
            ModBlocks.INVISIBLE_PLATFORM::get
    );

    public static final BlockEntityTypeRegistryObject<RATPTicketBarrierBlock.BarrierBlockEntity> RATP_TICKET_BARRIER = MTRFRARegistry.REGISTRY.registerBlockEntityType(
            Constants.id("ratp_ticket_barrier"),
            RATPTicketBarrierBlock.BarrierBlockEntity::new,
            ModBlocks.RATP_TICKET_BARRIER_ENTRANCE::get,
            ModBlocks.RATP_TICKET_BARRIER_EXIT::get,
            ModBlocks.RATP_TICKET_BARRIER_ENTRANCE_WITH_SIDE::get,
            ModBlocks.RATP_TICKET_BARRIER_EXIT_WITH_SIDE::get
    );

    public static final BlockEntityTypeRegistryObject<CopycatBlockBase.BlockEntity> COPYCAT = MTRFRARegistry.REGISTRY.registerBlockEntityType(
            Constants.id("copycat"),
            CopycatBlockBase.BlockEntity::new,
            ModBlocks.COPYCAT_LAYER::get,
            ModBlocks.COPYCAT_PLATFORM::get,
            ModBlocks.COPYCAT_LAYER_PLATFORM::get
    );

    public static final BlockEntityTypeRegistryObject<MotteLightBlock.BlockEntity> MOTTE_LIGHT = MTRFRARegistry.REGISTRY.registerBlockEntityType(
            Constants.id("motte_light"),
            MotteLightBlock.BlockEntity::new,
            ModBlocks.MOTTE_LIGHT::get
    );

    public static final BlockEntityTypeRegistryObject<MotteLightStationColorBlock.BlockEntity> MOTTE_LIGHT_STATION_COLOR = MTRFRARegistry.REGISTRY.registerBlockEntityType(
            Constants.id("motte_light_station_color"),
            MotteLightStationColorBlock.BlockEntity::new,
            ModBlocks.MOTTE_LIGHT_STATION_COLOR::get
    );

    private ModBlockEntities() {}

    public static void register() {}

}
