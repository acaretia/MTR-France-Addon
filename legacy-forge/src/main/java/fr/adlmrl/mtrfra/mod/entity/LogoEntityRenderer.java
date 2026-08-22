package fr.adlmrl.mtrfra.mod.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import fr.adlmrl.mtrfra.mod.mixin.GraphicsHolderAccessor;
import fr.adlmrl.mtrfra.mod.registry.ModEntities;
import fr.adlmrl.mtrfra.mod.util.Constants;
import fr.adlmrl.mtrfra.mod.util.VersionCompat;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import org.mtr.mapping.holder.Identifier;
import org.mtr.mapping.mapper.EntityRenderer;
import org.mtr.mapping.mapper.GraphicsHolder;
import org.mtr.mapping.registry.BlockRegistryObject;

import java.util.function.Supplier;

public class LogoEntityRenderer extends EntityRenderer<LogoEntity> {

    private static final float DEFAULT_Z_OFFSET = -1.01F;
    // sncf_sign_block.json's mesh is centered on the block (Z 7.24-8.76,
    // unlike every other logo's flat quad flush at Z=16) so it needs its own
    // offset to land its outward face at the same world position the shared
    // -1.01 offset puts the others' flush face at.
    private static final float SNCF_SIGN_Z_OFFSET = -0.5572F;

    private final Supplier<BlockRegistryObject> blockSupplier;

    public LogoEntityRenderer(Argument argument, Supplier<BlockRegistryObject> blockSupplier) {
        super(argument);
        this.blockSupplier = blockSupplier;
    }

    @Override
    public void render(LogoEntity entity, float yaw, float tickDelta, GraphicsHolder graphicsHolder, int light) {
        final GraphicsHolderAccessor accessor = (GraphicsHolderAccessor) (Object) graphicsHolder;
        final PoseStack poseStack = accessor.getMatrixStack();
        final MultiBufferSource bufferSource = accessor.getVertexConsumerProvider();
        final BlockState state = (BlockState) blockSupplier.get().get().getDefaultState().data;
        final float pitch = entity.getPitch2(tickDelta);

        final BlockRenderDispatcher dispatcher = Minecraft.getInstance().getBlockRenderer();

        poseStack.pushPose();
        VersionCompat.mulPoseY(poseStack, -yaw);
        VersionCompat.mulPoseX(poseStack, pitch);
        final boolean isSncfSign = entity.getType() == ModEntities.LOGO_SNCF_SIGN.get().data;
        poseStack.translate(-0.5, -0.5, isSncfSign ? SNCF_SIGN_Z_OFFSET : DEFAULT_Z_OFFSET);

        dispatcher.renderSingleBlock(state, poseStack, bufferSource, light, OverlayTexture.NO_OVERLAY);

        poseStack.popPose();
    }

    @Override
    protected int getBlockLightLevel(LogoEntity entity, BlockPos pos) {
        return super.getBlockLightLevel(entity, pos.relative(outwardFace(entity)));
    }

    @Override
    protected int getSkyLightLevel(LogoEntity entity, BlockPos pos) {
        return super.getSkyLightLevel(entity, pos.relative(outwardFace(entity)));
    }

    private static net.minecraft.core.Direction outwardFace(LogoEntity entity) {
        final float pitch = entity.getXRot();
        if (pitch > 45.0F) {
            return net.minecraft.core.Direction.DOWN;
        }
        if (pitch < -45.0F) {
            return net.minecraft.core.Direction.UP;
        }
        final float normalizedYaw = ((entity.getYRot() % 360.0F) + 360.0F) % 360.0F;
        if (normalizedYaw < 45.0F || normalizedYaw >= 315.0F) {
            return net.minecraft.core.Direction.NORTH;
        }
        if (normalizedYaw < 135.0F) {
            return net.minecraft.core.Direction.EAST;
        }
        if (normalizedYaw < 225.0F) {
            return net.minecraft.core.Direction.SOUTH;
        }
        return net.minecraft.core.Direction.WEST;
    }

    @Override
    public Identifier getTexture2(LogoEntity entity) {
        return new Identifier(Constants.MOD_ID, "textures/entity/empty.png");
    }

}
