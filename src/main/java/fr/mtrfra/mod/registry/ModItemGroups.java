package fr.mtrfra.mod.registry;

import fr.mtrfra.mod.tab.CreativeTabSection;
import fr.mtrfra.mod.tab.CreativeTabSections;
import fr.mtrfra.mod.util.Constants;
import net.minecraft.world.item.Item;
import org.mtr.mapping.holder.ItemConvertible;
import org.mtr.mapping.holder.ItemStack;
import org.mtr.mapping.mapper.TextHelper;
import org.mtr.mapping.registry.CreativeModeTabHolder;

//? if <1.20.1 {
/* import org.mtr.mapping.tool.HolderBase;
import java.util.function.Supplier;
*///? }


import java.util.Map;
import java.util.LinkedHashMap;
import java.util.List;

public final class ModItemGroups {

    //? if >=1.20.1 {
    public static final CreativeModeTabHolder MAIN = MTRFRARegistry.REGISTRY.createCreativeModeTabHolder(
            Constants.id("main"),
            () -> new ItemStack(new ItemConvertible(ModItems.LOGO_MTRFRANCEADDON.get().data))
    );
    //? }

    public static final CreativeTabSection LOGOS = new CreativeTabSection(
            "logos", TextHelper.translatable("itemGroup.mtrfranceaddon.logos"), "tab_banner/logos", 0xB85FC7
    );
    public static final CreativeTabSection RAIL_CONNECTORS = new CreativeTabSection(
            "rail_connectors", TextHelper.translatable("itemGroup.mtrfranceaddon.rail_connectors"), "tab_banner/rail_connectors", 0x4A90D9
    );
    public static final CreativeTabSection STATION_EQUIPMENT = new CreativeTabSection(
            "station_equipment", TextHelper.translatable("itemGroup.mtrfranceaddon.station_equipment"), "tab_banner/station_equipment", 0xE0B84D
    );
    public static final CreativeTabSection BUILDING_MATERIALS = new CreativeTabSection(
            "building_materials", TextHelper.translatable("itemGroup.mtrfranceaddon.building_materials"), "tab_banner/building_materials", 0x93C976
    );
    public static final CreativeTabSection SIGNS = new CreativeTabSection(
            "sign_group", TextHelper.translatable("itemGroup.mtrfranceaddon.sign_group"), "tab_banner/sign_group", 0xCCCCCC
    );


    public static final List<CreativeTabSection> SECTION_ORDER = List.of(LOGOS, RAIL_CONNECTORS, STATION_EQUIPMENT, BUILDING_MATERIALS, SIGNS);

    static {
        CreativeTabSections.pinFirst(List.of(
                () -> (Item) ModItems.LOGO_MTRFRANCEADDON.get().data,
                () -> (Item) ModItems.LOGO_SNCF_ACTUEL.get().data,
                () -> (Item) ModItems.LOGO_SNCF_1992_2005.get().data,
                () -> (Item) ModItems.LOGO_SNCF_1985_1992_V1.get().data,
                () -> (Item) ModItems.LOGO_SNCF_1985_1992_V2.get().data,
                () -> (Item) ModItems.LOGO_SNCF_1967_1985_V1.get().data,
                () -> (Item) ModItems.LOGO_SNCF_1967_1985_V2.get().data,
                () -> (Item) ModItems.LOGO_SNCF_1947_1967.get().data,
                () -> (Item) ModItems.LOGO_SNCF_1938_1947.get().data,
                () -> (Item) ModItems.LOGO_RATP_1951_1960.get().data,
                () -> (Item) ModItems.LOGO_RATP_1960_1976.get().data,
                () -> (Item) ModItems.LOGO_RATP_1976_1992.get().data,
                () -> (Item) ModItems.LOGO_RATP_ACTUEL.get().data,
                () -> (Item) ModItems.LOGO_SNCF_SIGN.get().data,
                () -> (Item) ModBlocks.IDFM_METRO.get().asItem().data,
                () -> (Item) ModBlocks.IDFM_METRO_1.get().asItem().data,
                () -> (Item) ModBlocks.IDFM_METRO_2.get().asItem().data,
                () -> (Item) ModBlocks.IDFM_METRO_3.get().asItem().data,
                () -> (Item) ModBlocks.IDFM_METRO_3BIS.get().asItem().data,
                () -> (Item) ModBlocks.IDFM_METRO_4.get().asItem().data,
                () -> (Item) ModBlocks.IDFM_METRO_5.get().asItem().data,
                () -> (Item) ModBlocks.IDFM_METRO_6.get().asItem().data,
                () -> (Item) ModBlocks.IDFM_METRO_7.get().asItem().data,
                () -> (Item) ModBlocks.IDFM_METRO_7BIS.get().asItem().data,
                () -> (Item) ModBlocks.IDFM_METRO_8.get().asItem().data,
                () -> (Item) ModBlocks.IDFM_METRO_9.get().asItem().data,
                () -> (Item) ModBlocks.IDFM_METRO_10.get().asItem().data,
                () -> (Item) ModBlocks.IDFM_METRO_11.get().asItem().data,
                () -> (Item) ModBlocks.IDFM_METRO_12.get().asItem().data,
                () -> (Item) ModBlocks.IDFM_METRO_13.get().asItem().data,
                () -> (Item) ModBlocks.IDFM_METRO_14.get().asItem().data,
                () -> (Item) ModBlocks.IDFM_METRO_15.get().asItem().data,
                () -> (Item) ModBlocks.IDFM_METRO_16.get().asItem().data,
                () -> (Item) ModBlocks.IDFM_METRO_17.get().asItem().data,
                () -> (Item) ModBlocks.IDFM_METRO_18.get().asItem().data,
                () -> (Item) ModBlocks.IDFM_RER.get().asItem().data,
                () -> (Item) ModBlocks.IDFM_RER_A.get().asItem().data,
                () -> (Item) ModBlocks.IDFM_RER_B.get().asItem().data,
                () -> (Item) ModBlocks.IDFM_RER_C.get().asItem().data,
                () -> (Item) ModBlocks.IDFM_RER_D.get().asItem().data,
                () -> (Item) ModBlocks.IDFM_RER_E.get().asItem().data,
                () -> (Item) ModBlocks.IDFM_TRAIN.get().asItem().data,
                () -> (Item) ModBlocks.IDFM_TRAIN_H.get().asItem().data,
                () -> (Item) ModBlocks.IDFM_TRAIN_J.get().asItem().data,
                () -> (Item) ModBlocks.IDFM_TRAIN_K.get().asItem().data,
                () -> (Item) ModBlocks.IDFM_TRAIN_L.get().asItem().data,
                () -> (Item) ModBlocks.IDFM_TRAIN_N.get().asItem().data,
                () -> (Item) ModBlocks.IDFM_TRAIN_P.get().asItem().data,
                () -> (Item) ModBlocks.IDFM_TRAIN_R.get().asItem().data,
                () -> (Item) ModBlocks.IDFM_TRAIN_U.get().asItem().data,
                () -> (Item) ModBlocks.IDFM_TRAIN_V.get().asItem().data,
                () -> (Item) ModBlocks.IDFM_TRAM.get().asItem().data,
                () -> (Item) ModBlocks.IDFM_TRAM_T1.get().asItem().data,
                () -> (Item) ModBlocks.IDFM_TRAM_T2.get().asItem().data,
                () -> (Item) ModBlocks.IDFM_TRAM_T3.get().asItem().data,
                () -> (Item) ModBlocks.IDFM_TRAM_T3A.get().asItem().data,
                () -> (Item) ModBlocks.IDFM_TRAM_T3B.get().asItem().data,
                () -> (Item) ModBlocks.IDFM_TRAM_T4.get().asItem().data,
                () -> (Item) ModBlocks.IDFM_TRAM_T5.get().asItem().data,
                () -> (Item) ModBlocks.IDFM_TRAM_T6.get().asItem().data,
                () -> (Item) ModBlocks.IDFM_TRAM_T7.get().asItem().data,
                () -> (Item) ModBlocks.IDFM_TRAM_T8.get().asItem().data,
                () -> (Item) ModBlocks.IDFM_TRAM_T9.get().asItem().data,
                () -> (Item) ModBlocks.IDFM_TRAM_T10.get().asItem().data,
                () -> (Item) ModBlocks.IDFM_TRAM_T11.get().asItem().data,
                () -> (Item) ModBlocks.IDFM_TRAM_T12.get().asItem().data,
                () -> (Item) ModBlocks.IDFM_TRAM_T13.get().asItem().data,
                () -> (Item) ModBlocks.IDFM_TRAM_T14.get().asItem().data,
                () -> (Item) ModBlocks.IDFM_CABLE.get().asItem().data,
                () -> (Item) ModBlocks.IDFM_CABLE_1.get().asItem().data,
                () -> (Item) ModBlocks.IDFM_BUS.get().asItem().data
        ));
    }

    //? if >=1.20.1 {
    public static CreativeModeTabHolder tabFor(CreativeTabSection section) {
        return MAIN;
    }
    //? } else {
    /*private static final Map<CreativeTabSection, CreativeModeTabHolder> SECTION_TABS = new LinkedHashMap<>();

    private static CreativeModeTabHolder registerSectionTab(CreativeTabSection section, Supplier<? extends HolderBase<?>> itemSupplier) {
        final CreativeModeTabHolder holder = MTRFRARegistry.REGISTRY.createCreativeModeTabHolder(
                Constants.id(section.id()),
                () -> new ItemStack(ItemConvertible.cast(itemSupplier.get()))
        );
        SECTION_TABS.put(section, holder);
        return holder;
    }

    public static final CreativeModeTabHolder LOGOS_TAB = registerSectionTab(LOGOS, () -> ModItems.LOGO_MTRFRANCEADDON.get());
    public static final CreativeModeTabHolder RAIL_CONNECTORS_TAB = registerSectionTab(RAIL_CONNECTORS, () -> ModItems.RAIL_CONNECTORS.get("rail_30").get());
    public static final CreativeModeTabHolder STATION_EQUIPMENT_TAB = registerSectionTab(STATION_EQUIPMENT, () -> ModBlocks.SNCF_PLATFORM.get());
    public static final CreativeModeTabHolder BUILDING_MATERIALS_TAB = registerSectionTab(BUILDING_MATERIALS, () -> ModBlocks.BEIGE_LIMESTONE_PAVING.get());
    public static final CreativeModeTabHolder SIGNS_TAB = registerSectionTab(SIGNS, () -> ModBlocks.SIGN_SPEED_20.get());

    public static CreativeModeTabHolder tabFor(CreativeTabSection section) {
        return SECTION_TABS.get(section);
    }
    *///? }

    private ModItemGroups() {}

    public static void register() {}

}
