package fr.adlmrl.mtrfra.mod.registry;

import fr.adlmrl.mtrfra.mod.util.Constants;
import org.mtr.mapping.holder.BlockEntity;
import org.mtr.mapping.holder.Identifier;
import org.mtr.mapping.holder.RenderLayer;
import org.mtr.mapping.registry.BlockRegistryObject;
import org.mtr.mapping.registry.ItemRegistryObject;
import org.mtr.mapping.registry.RegistryClient;
import org.mtr.mod.InitClient;
import org.mtr.mod.item.ItemBlockClickingBase;
import fr.adlmrl.mtrfra.mod.block.copycat.CopycatBlockBase;
import fr.adlmrl.mtrfra.mod.block.sign.MotteLightBlock;
import fr.adlmrl.mtrfra.mod.entity.CushionEntityRenderer;
import fr.adlmrl.mtrfra.mod.item.CopycatLayerBlockItem;
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.state.BlockState;

public final class MTRFRARegistryClient {

    public static final RegistryClient REGISTRY_CLIENT = new RegistryClient(MTRFRARegistry.REGISTRY);

    private MTRFRARegistryClient() {}

    public static void register() {
        setupPacketsClient();
        ModBlockEntityRenderers.registerClient();
        ModEntityRenderers.registerClient();
        ModEvents.registerClient();
        ModNetworking.registerClient();

        for (ItemRegistryObject item : ModItems.RAIL_CONNECTORS.values()) {
            REGISTRY_CLIENT.registerItemModelPredicate(item, new Identifier(Constants.MTR_MOD_ID, "selected"), checkItemPredicateTag());
        }

        REGISTRY_CLIENT.registerBlockColors((blockState, world, pos, tintIndex) ->
                        tintIndex == 0 ? CushionEntityRenderer.currentTintColor : 0xFFFFFF, ModBlocks.IDFM_SEAT, ModBlocks.IDFM_SEAT_WITH_POLE);

        REGISTRY_CLIENT.registerBlockColors((blockState, world, pos, tintIndex) -> {
                    if (tintIndex != 0) {
                        return 0xFFFFFF;
                    }

                    final BlockEntity entity = world.getBlockEntity(pos);
                    return entity != null && entity.data instanceof MotteLightBlock.BlockEntity motteLightEntity ? motteLightEntity.getColor() : 0xFFFFFF;
                },
                ModBlocks.MOTTE_LIGHT);

        REGISTRY_CLIENT.registerBlockColors((blockState, world, pos, tintIndex) ->
                        tintIndex == 0 ? InitClient.getStationColor(pos) : 0xFFFFFF, ModBlocks.MOTTE_LIGHT_STATION_COLOR);

        for (BlockRegistryObject block : new BlockRegistryObject[]{
                ModBlocks.LOGO_MTRFRANCEADDON, ModBlocks.LOGO_SNCF_ACTUEL, ModBlocks.LOGO_SNCF_1992_2005,
                ModBlocks.LOGO_SNCF_1985_1992_V1, ModBlocks.LOGO_SNCF_1985_1992_V2, ModBlocks.LOGO_SNCF_1967_1985_V1,
                ModBlocks.LOGO_SNCF_1967_1985_V2, ModBlocks.LOGO_SNCF_1947_1967, ModBlocks.LOGO_SNCF_1938_1947,
                ModBlocks.LOGO_RATP_1951_1960, ModBlocks.LOGO_RATP_1960_1976, ModBlocks.LOGO_RATP_1976_1992, ModBlocks.LOGO_RATP_ACTUEL,
                ModBlocks.LOGO_SNCF_SIGN,
                ModBlocks.SNCF_BUFFER,
                ModBlocks.RATP_ALARM_SIGN, ModBlocks.RATP_DOORCLOSE_SIGN, ModBlocks.RATP_FIREEXIT_SIGN,
                ModBlocks.RATP_CURVEDPLATFORM_SIGN, ModBlocks.RATP_NOSMOKING_SIGN,
                ModBlocks.POLE, ModBlocks.SIGN_SPEED_20, ModBlocks.SIGN_SPEED_40, ModBlocks.SIGN_SPEED_60,
                ModBlocks.SIGN_SPEED_80, ModBlocks.SIGN_SPEED_120, ModBlocks.SIGN_SPEED_160,
                ModBlocks.SIGN_SPEED_200, ModBlocks.SIGN_SPEED_300, ModBlocks.SIGN_DEPOT,
                ModBlocks.SIGN_ONE_WAY_CORRECT, ModBlocks.SIGN_ONE_WAY_WRONG, ModBlocks.SIGN_END_OF_TRACK, ModBlocks.SIGN_LEVEL_CROSSING,
                ModBlocks.SIGN_SQUARE, ModBlocks.SIGN_STATION, ModBlocks.SIGN_CARS_4, ModBlocks.SIGN_CARS_6,
                ModBlocks.SIGN_CARS_8, ModBlocks.SIGN_CARS_10, ModBlocks.SIGN_CARS_12, ModBlocks.SIGN_CARS_14,
                ModBlocks.SIGN_CARS_16, ModBlocks.SIGN_PLATFORM_LEFT, ModBlocks.SIGN_PLATFORM_RIGHT, ModBlocks.SIGN_NO_TUNNEL_VENT,
                ModBlocks.SIGN_S_CURVE_LEFT, ModBlocks.SIGN_S_CURVE_RIGHT, ModBlocks.SIGN_TUNNEL, ModBlocks.SIGN_WHISTLE,
                ModBlocks.SIGN_SWITCH_100M, ModBlocks.SIGN_SWITCH_200M, ModBlocks.SIGN_SWITCH_300M,
                ModBlocks.SIGN_ONE_WAY_CORRECT_GROUND, ModBlocks.SIGN_ONE_WAY_WRONG_GROUND, ModBlocks.SIGN_SPEED_20_GROUND,
                ModBlocks.SIGN_SPEED_40_GROUND, ModBlocks.SIGN_SPEED_60_GROUND, ModBlocks.SIGN_SPEED_80_GROUND,
                ModBlocks.SIGN_SPEED_120_GROUND, ModBlocks.SIGN_SPEED_160_GROUND, ModBlocks.SIGN_SPEED_200_GROUND,
                ModBlocks.SIGN_SPEED_300_GROUND, ModBlocks.SIGN_DEPOT_GROUND, ModBlocks.SIGN_END_OF_TRACK_GROUND,
                ModBlocks.SIGN_S_CURVE_LEFT_GROUND, ModBlocks.SIGN_S_CURVE_RIGHT_GROUND, ModBlocks.SIGN_LEVEL_CROSSING_GROUND,
                ModBlocks.SIGN_SQUARE_GROUND, ModBlocks.SIGN_STATION_GROUND, ModBlocks.SIGN_NO_TUNNEL_VENT_GROUND,
                ModBlocks.SIGN_TUNNEL_GROUND, ModBlocks.SIGN_WHISTLE_GROUND, ModBlocks.SIGN_SWITCH_100M_GROUND,
                ModBlocks.SIGN_SWITCH_200M_GROUND, ModBlocks.SIGN_SWITCH_300M_GROUND,
                ModBlocks.POLE_CONNECTION_LEFT, ModBlocks.POLE_CONNECTION_RIGHT, ModBlocks.POLE_CONNECTION_HORIZONTAL, ModBlocks.POLE_CONNECTION_MIDDLE,
                ModBlocks.SIGN_SPEED_20_GALLOWS, ModBlocks.SIGN_SPEED_40_GALLOWS, ModBlocks.SIGN_SPEED_60_GALLOWS, ModBlocks.SIGN_SPEED_80_GALLOWS,
                ModBlocks.SIGN_SPEED_120_GALLOWS, ModBlocks.SIGN_SPEED_160_GALLOWS, ModBlocks.SIGN_SPEED_200_GALLOWS,
                ModBlocks.SIGN_SPEED_300_GALLOWS, ModBlocks.SIGN_SQUARE_GALLOWS,
                ModBlocks.GREEN_VENDING_MACHINE, ModBlocks.RED_VENDING_MACHINE
        }) {
            REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), block);
        }

        registerCopycatItemPreview(ModBlocks.COPYCAT_LAYER);
        registerCopycatItemPreview(ModBlocks.COPYCAT_PLATFORM);
        registerCopycatItemPreview(ModBlocks.COPYCAT_LAYER_PLATFORM);

        REGISTRY_CLIENT.init();
    }

    private static void registerCopycatItemPreview(BlockRegistryObject copycatBlock) {
        final Item item = (Item) copycatBlock.get().asItem().data;
        BuiltinItemRendererRegistry.INSTANCE.register(item, (stack, mode, poseStack, buffers, light, overlay) -> {
            final Minecraft minecraft = Minecraft.getInstance();
            final CompoundTag tag = stack.getTag();
            final String mimicId = tag != null && tag.contains(CopycatLayerBlockItem.KEY_MIMIC) ? tag.getString(CopycatLayerBlockItem.KEY_MIMIC) : null;
            final BlockState mimicState = mimicId == null ? null : CopycatBlockBase.BlockEntity.idToMimic(mimicId);
            final BlockState previewState = mimicState != null ? mimicState : (BlockState) ModBlocks.COPYCAT_LAYER_PLACEHOLDER.get().getDefaultState().data;
            final BakedModel bakedModel = minecraft.getBlockRenderer().getBlockModel(previewState);

            minecraft.getItemRenderer().render(stack, mode, false, poseStack, buffers, light, overlay, bakedModel);
        });
    }

    public static void setupPacketsClient() {
        REGISTRY_CLIENT.setupPackets(Constants.id("packet"));
    }

    private static RegistryClient.ModelPredicateProvider checkItemPredicateTag() {
        return (itemStack, clientWorld, livingEntity) -> itemStack.getOrCreateTag().contains(ItemBlockClickingBase.TAG_POS) ? 1 : 0;
    }

}
