package fr.adlmrl.mtrfra.mod.registry;

import net.minecraft.world.item.Item;
import org.mtr.mapping.registry.BlockRegistryObject;
import org.mtr.mapping.registry.ItemRegistryObject;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class CreativeTabSections {

    private static final List<Runnable> PENDING = new ArrayList<>();
    private static final Map<Item, CreativeTabSection> ITEM_SECTIONS = new LinkedHashMap<>();
    private static boolean resolved = false;

    private CreativeTabSections() {}

    public static void recordBlock(BlockRegistryObject blockRegistryObject, CreativeTabSection section) {
        PENDING.add(() -> ITEM_SECTIONS.put((Item) blockRegistryObject.get().asItem().data, section));
    }

    public static void recordItem(ItemRegistryObject itemRegistryObject, CreativeTabSection section) {
        PENDING.add(() -> ITEM_SECTIONS.put((Item) itemRegistryObject.get().data, section));
    }

    static Map<Item, CreativeTabSection> itemSections() {
        if (!resolved) {
            PENDING.forEach(Runnable::run);
            resolved = true;
        }
        return ITEM_SECTIONS;
    }

}
