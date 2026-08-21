package fr.adlmrl.mtrfra.mod.registry;

import fr.adlmrl.mtrfra.mod.util.Constants;
import org.mtr.mapping.holder.Block;
import org.mtr.mapping.holder.EntityType;
import org.mtr.mapping.holder.Item;
import org.mtr.mapping.holder.World;
import org.mtr.mapping.mapper.EntityExtension;
import org.mtr.mapping.registry.BlockRegistryObject;
import org.mtr.mapping.registry.EntityTypeRegistryObject;
import org.mtr.mapping.registry.ItemRegistryObject;
import org.mtr.mapping.holder.ItemSettings;
import org.mtr.mapping.registry.Registry;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public final class MTRFRARegistry {

    public static final Registry REGISTRY = new Registry();

    private MTRFRARegistry() {}

    public static void register() {
        setupPackets();
        ModItemGroups.register();
        ModBlocks.register();
        ModBlockEntities.register();
        ModEntities.register();
        ModItems.register();
        ModEvents.register();
        ModNetworking.register();

        REGISTRY.init();
    }

    public static void setupPackets() {
        REGISTRY.setupPackets(Constants.id("packet"));
    }

    public static BlockRegistryObject registerBlockWithItem(String id, Supplier<Block> supplier, CreativeTabSection section) {
        final BlockRegistryObject registered = REGISTRY.registerBlockWithBlockItem(Constants.id(id), supplier, ModItemGroups.MAIN);
        CreativeTabSections.recordBlock(registered, section);
        return registered;
    }

    public static BlockRegistryObject registerBlock(String id, Supplier<Block> supplier) {
        return REGISTRY.registerBlock(Constants.id(id), supplier);
    }

    public static ItemRegistryObject registerItem(String id, Function<ItemSettings, Item> callback, CreativeTabSection section) {
        final ItemRegistryObject registered = REGISTRY.registerItem(Constants.id(id), callback, ModItemGroups.MAIN);
        CreativeTabSections.recordItem(registered, section);
        return registered;
    }

    @SuppressWarnings("unchecked")
    public static <T extends EntityExtension> EntityTypeRegistryObject<T> registerEntity(String id, BiFunction<EntityType<?>, World, T> factory, float width, float height) {
        return (EntityTypeRegistryObject<T>) REGISTRY.registerEntityType(Constants.id(id), (type, world) -> factory.apply(new EntityType<>(type.data), world), width, height);
    }

}
