package fr.adlmrl.mtrfra.mod.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import fr.adlmrl.mtrfra.mod.mixin.GraphicsHolderAccessor;
import fr.adlmrl.mtrfra.mod.util.Constants;
import fr.adlmrl.mtrfra.mod.util.VersionCompat;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.texture.OverlayTexture;
import org.mtr.mapping.holder.Direction;
import org.mtr.mapping.holder.Identifier;
import org.mtr.mapping.holder.Property;
import org.mtr.mapping.mapper.DirectionHelper;
import org.mtr.mapping.mapper.EntityRenderer;
import org.mtr.mapping.mapper.GraphicsHolder;
import org.mtr.mapping.registry.BlockRegistryObject;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.world.level.block.state.BlockState;
import org.mtr.mod.block.IBlock;

import java.util.function.Supplier;

public class TicketMachineEntityRenderer extends EntityRenderer<TicketMachineEntity> {

    private final Supplier<BlockRegistryObject> blockSupplier;

    public TicketMachineEntityRenderer(Argument argument, Supplier<BlockRegistryObject> blockSupplier) {
        super(argument);
        this.blockSupplier = blockSupplier;
    }

    @Override
    public void render(TicketMachineEntity entity, float yaw, float tickDelta, GraphicsHolder graphicsHolder, int light) {
        final GraphicsHolderAccessor accessor = (GraphicsHolderAccessor) (Object) graphicsHolder;
        final PoseStack poseStack = accessor.getMatrixStack();
        final MultiBufferSource bufferSource = accessor.getVertexConsumerProvider();
        final BlockState state = (BlockState) blockSupplier.get().get().getDefaultState()
                .with(new Property<>(DirectionHelper.FACING.data), Direction.SOUTH.data)
                .with(new Property<>(IBlock.HALF.data), IBlock.DoubleBlockHalf.LOWER)
                .data;

        final BlockRenderDispatcher dispatcher = Minecraft.getInstance().getBlockRenderer();

        poseStack.pushPose();
        VersionCompat.mulPoseY(poseStack, -yaw);
        poseStack.translate(-0.5, 0, -0.5);

        dispatcher.renderSingleBlock(state, poseStack, bufferSource, light, OverlayTexture.NO_OVERLAY);

        poseStack.popPose();
    }

    @Override
    public Identifier getTexture2(TicketMachineEntity entity) {
        return new Identifier(Constants.MOD_ID, "textures/entity/empty.png");
    }

}
