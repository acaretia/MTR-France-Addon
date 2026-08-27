package fr.mtrfra.mod.registry;

import fr.mtrfra.mod.tab.CreativeTabSection;
import fr.mtrfra.mod.util.Constants;
import org.mtr.mapping.holder.ItemConvertible;
import org.mtr.mapping.holder.ItemStack;
import org.mtr.mapping.mapper.TextHelper;
import org.mtr.mapping.registry.CreativeModeTabHolder;

import java.util.List;

public final class ModItemGroups {

    public static final CreativeModeTabHolder MAIN = MTRFRARegistry.REGISTRY.createCreativeModeTabHolder(
            Constants.id("main"),
            () -> new ItemStack(new ItemConvertible(ModItems.LOGO_MTRFRANCEADDON.get().data))
    );

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

    private ModItemGroups() {}

    public static void register() {}

}
