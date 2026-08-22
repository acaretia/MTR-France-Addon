package fr.adlmrl.mtrfra.mod.block.sign;

import com.mojang.blaze3d.vertex.PoseStack;
import fr.adlmrl.mtrfra.mod.mixin.GraphicsHolderAccessor;
import fr.adlmrl.mtrfra.mod.util.VersionCompat;
import org.mtr.mapping.holder.Direction;
import org.mtr.mapping.holder.TextFormatting;
import org.mtr.mapping.mapper.BlockEntityRenderer;
import org.mtr.mapping.mapper.DirectionHelper;
import org.mtr.mapping.mapper.GraphicsHolder;
import org.mtr.mapping.mapper.TextHelper;
import org.mtr.mod.block.IBlock;

public class RATPSignRenderer extends BlockEntityRenderer<RATPSignBase.BlockEntityBase> {

    private static final float SCALE = 1 / 48F;

    public RATPSignRenderer(Argument argument) {
        super(argument);
    }

    @Override
    public void render(RATPSignBase.BlockEntityBase entity, float tickDelta, GraphicsHolder graphicsHolder, int light, int overlay) {
        final String title = entity.getEffectiveTitle();
        final String subtitle = entity.subtitle;
        if (title.isEmpty() && subtitle.isEmpty()) {
            return;
        }

        final org.mtr.mapping.holder.BlockState state = entity.getCachedState2();
        final Object rawBlock = state.getBlock().data;
        if (!(rawBlock instanceof RATPSignBase signBlock)) {
            return;
        }

        final GraphicsHolderAccessor accessor = (GraphicsHolderAccessor) (Object) graphicsHolder;
        final PoseStack poseStack = accessor.getMatrixStack();
        final Direction facing = IBlock.getStatePropertySafe(state, DirectionHelper.FACING);

        drawFace(poseStack, graphicsHolder, signBlock, title, subtitle, -facing.asRotation());
        if (signBlock.doubleSided()) {
            drawFace(poseStack, graphicsHolder, signBlock, title, subtitle, -facing.asRotation() + 180);
        }
    }

    private void drawFace(PoseStack poseStack, GraphicsHolder graphicsHolder, RATPSignBase signBlock, String title, String subtitle, float rotationDegrees) {
        poseStack.pushPose();
        poseStack.translate(0.5, 0, 0.5);
        VersionCompat.mulPoseY(poseStack, rotationDegrees);
        poseStack.translate(-0.5, signBlock.textY() / 16.0, signBlock.textZ() / 16.0 - 0.5);
        VersionCompat.mulPoseY(poseStack, 180);
        poseStack.scale(SCALE, -SCALE, SCALE);

        final boolean hasSubtitle = !subtitle.isEmpty();
        if (!title.isEmpty()) {
            graphicsHolder.drawText(title, -GraphicsHolder.getTextWidth(title) / 2, hasSubtitle ? -5 : 0, 0xFFFFFF, false, GraphicsHolder.getDefaultLight());
        }
        if (hasSubtitle) {
            final org.mtr.mapping.holder.MutableText formattedSubtitle = TextHelper.literal(subtitle).formatted(TextFormatting.ITALIC);
            graphicsHolder.drawText(formattedSubtitle, -GraphicsHolder.getTextWidth(formattedSubtitle) / 2, 5, 0xFFFFFF, false, GraphicsHolder.getDefaultLight());
        }

        poseStack.popPose();
    }

}
