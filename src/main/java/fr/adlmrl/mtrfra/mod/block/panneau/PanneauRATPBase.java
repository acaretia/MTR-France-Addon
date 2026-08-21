package fr.adlmrl.mtrfra.mod.block.panneau;

import fr.adlmrl.mtrfra.mod.block.base.DirectionalBlock;
import fr.adlmrl.mtrfra.mod.panneau.PacketOpenPanneauConfigScreen;
import fr.adlmrl.mtrfra.mod.registry.MTRFRARegistry;
import org.mtr.mapping.holder.ActionResult;
import org.mtr.mapping.holder.BlockEntity;
import org.mtr.mapping.holder.BlockHitResult;
import org.mtr.mapping.holder.BlockPos;
import org.mtr.mapping.holder.BlockSettings;
import org.mtr.mapping.holder.BlockState;
import org.mtr.mapping.holder.CompoundTag;
import org.mtr.mapping.holder.Hand;
import org.mtr.mapping.holder.IntegerProperty;
import org.mtr.mapping.holder.PlayerEntity;
import org.mtr.mapping.holder.Property;
import org.mtr.mapping.holder.ServerPlayerEntity;
import org.mtr.mapping.holder.World;
import org.mtr.mapping.mapper.BlockEntityExtension;
import org.mtr.mapping.mapper.BlockWithEntity;
import org.mtr.mapping.tool.HolderBase;
import org.mtr.mod.InitClient;
import org.mtr.mod.Items;

import java.util.List;

public abstract class PanneauRATPBase extends DirectionalBlock implements BlockWithEntity {

    public static final IntegerProperty CATEGORY = IntegerProperty.of("category", 0, 7);

    public PanneauRATPBase(BlockSettings blockSettings) {
        super(blockSettings);
        setDefaultState2(getDefaultState2().with(new Property<>(CATEGORY.data), 0));
    }

    public abstract int maxCategory();

    public abstract float textY();

    public abstract float textZ();

    @Override
    public BlockEntityExtension createBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new BlockEntityBase(blockPos, blockState);
    }

    @Override
    public void addBlockProperties(List<HolderBase<?>> properties) {
        super.addBlockProperties(properties);
        properties.add(CATEGORY);
    }

    @Override
    public ActionResult onUse2(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!player.getStackInHand(hand).getItem().equals(Items.BRUSH.get().asItem())) {
            return ActionResult.PASS;
        }
        if (!world.isClient()) {
            final BlockEntity blockEntity = world.getBlockEntity(pos);
            final BlockEntityBase panneauBlockEntity = blockEntity != null && blockEntity.data instanceof BlockEntityBase ? (BlockEntityBase) blockEntity.data : null;
            final String customTitle = panneauBlockEntity == null ? "" : panneauBlockEntity.customTitle;
            final String subtitle = panneauBlockEntity == null ? "" : panneauBlockEntity.subtitle;
            final int category = org.mtr.mod.block.IBlock.getStatePropertySafe(state, CATEGORY);
            MTRFRARegistry.REGISTRY.sendPacketToClient(ServerPlayerEntity.cast(player), new PacketOpenPanneauConfigScreen(pos, customTitle, subtitle, category, maxCategory()));
        }
        return ActionResult.SUCCESS;
    }

    public static class BlockEntityBase extends BlockEntityExtension {

        private static final String KEY_CUSTOM_TITLE = "custom_title";
        private static final String KEY_SUBTITLE = "subtitle";

        public String customTitle = "";
        public String subtitle = "";

        public BlockEntityBase(BlockPos pos, BlockState state) {
            super(fr.adlmrl.mtrfra.mod.registry.ModBlockEntities.PANNEAU_RATP.get(), pos, state);
        }

        public String getEffectiveTitle() {
            if (!customTitle.isEmpty()) {
                return customTitle;
            }
            final org.mtr.core.data.Station station = InitClient.findStation(getPos2());
            return station != null ? station.getName() : "";
        }

        @Override
        public void readCompoundTag(CompoundTag compoundTag) {
            super.readCompoundTag(compoundTag);
            customTitle = compoundTag.getString(KEY_CUSTOM_TITLE);
            subtitle = compoundTag.getString(KEY_SUBTITLE);
        }

        @Override
        public void writeCompoundTag(CompoundTag compoundTag) {
            super.writeCompoundTag(compoundTag);
            compoundTag.putString(KEY_CUSTOM_TITLE, customTitle);
            compoundTag.putString(KEY_SUBTITLE, subtitle);
        }

    }

}
