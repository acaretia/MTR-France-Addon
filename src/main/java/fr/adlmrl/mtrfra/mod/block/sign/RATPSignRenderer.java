package fr.adlmrl.mtrfra.mod.block.sign;

import com.mojang.blaze3d.vertex.PoseStack;
import fr.adlmrl.mtrfra.mod.mixin.GraphicsHolderAccessor;
import org.mtr.mapping.holder.Direction;
import org.mtr.mapping.holder.TextFormatting;
import org.mtr.mapping.mapper.BlockEntityRenderer;
import org.mtr.mapping.mapper.DirectionHelper;
import org.mtr.mapping.mapper.GraphicsHolder;
import org.mtr.mapping.mapper.TextHelper;
import org.mtr.mod.block.IBlock;

public class RATPSignRenderer extends BlockEntityRenderer<RATPSignBase.BlockEntityBase> {

    private static final float TITLE_SCALE = 1 / 50F;
    private static final float SUBTITLE_SCALE = 1 / 125F;
    private static final float LINE_HEIGHT_PX = 9F;

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
        final int category = IBlock.getStatePropertySafe(state, RATPSignBase.CATEGORY);
        final RATPSignBase.TextLayout layout = signBlock.textLayout(category);

        final float baseRotation = -facing.asRotation() + 180F;
        drawFace(poseStack, graphicsHolder, signBlock, layout, title, subtitle, baseRotation, false);
        if (signBlock.doubleSided()) {
            drawFace(poseStack, graphicsHolder, signBlock, layout, title, subtitle, baseRotation + 180F, true);
        }
    }

    private void drawFace(PoseStack poseStack, GraphicsHolder graphicsHolder, RATPSignBase signBlock, RATPSignBase.TextLayout layout, String title, String subtitle, float rotationDegrees, boolean mirrored) {
        final boolean hasTitle = !title.isEmpty();
        final boolean hasSubtitle = !subtitle.isEmpty();
        if (!hasTitle && !hasSubtitle) {
            return;
        }

        final boolean stackedAsOneBlock = hasTitle && hasSubtitle && !layout.hasSubtitlePosition;
        final float titleHeight = LINE_HEIGHT_PX * TITLE_SCALE * 16F;
        final float subtitleHeight = LINE_HEIGHT_PX * SUBTITLE_SCALE * 16F;
        final float blockHalfHeight = (titleHeight + subtitleHeight) / 2F;

        if (hasTitle) {
            final float titleX = mirrored ? 16F - layout.titleX : layout.titleX;
            final float titleY = stackedAsOneBlock ? layout.titleY + blockHalfHeight - titleHeight / 2F : layout.titleY;
            pushTextPose(poseStack, rotationDegrees, titleX, titleY, signBlock.textZ(), TITLE_SCALE);
            graphicsHolder.drawText(title, -GraphicsHolder.getTextWidth(title) / 2, -4, 0xFFFFFF, false, GraphicsHolder.getDefaultLight());
            poseStack.popPose();
        }

        if (hasSubtitle) {
            final org.mtr.mapping.holder.MutableText formattedSubtitle = TextHelper.literal(subtitle).formatted(TextFormatting.ITALIC);
            final float subtitleX = mirrored ? 16F - layout.subtitleX : layout.subtitleX;
            final float subtitleY;
            if (layout.hasSubtitlePosition) {
                subtitleY = layout.subtitleY;
            } else if (hasTitle) {
                subtitleY = layout.titleY - blockHalfHeight + subtitleHeight / 2F - 0.075F;
            } else {
                subtitleY = layout.titleY - 0.075F;
            }
            pushTextPose(poseStack, rotationDegrees, subtitleX, subtitleY, signBlock.textZ(), SUBTITLE_SCALE);
            graphicsHolder.drawText(formattedSubtitle, -GraphicsHolder.getTextWidth(formattedSubtitle) / 2, -4, 0xFFFFFF, false, GraphicsHolder.getDefaultLight());
            poseStack.popPose();
        }
    }

    private void pushTextPose(PoseStack poseStack, float rotationDegrees, float x, float y, float z, float scale) {
        poseStack.pushPose();
        poseStack.translate(0.5, 0, 0.5);
        //? if >=1.19.4 {
        poseStack.mulPose(new org.joml.Quaternionf().rotationY((float) Math.toRadians(rotationDegrees)));
        //? } else {
        /*poseStack.mulPose(com.mojang.math.Vector3f.YP.rotationDegrees(rotationDegrees));
        *///? }
        poseStack.translate(x / 16.0 - 0.5, y / 16.0, z / 16.0 - 0.5);
        //? if >=1.19.4 {
        poseStack.mulPose(new org.joml.Quaternionf().rotationY((float) Math.toRadians(180)));
        //? } else {
        /*poseStack.mulPose(com.mojang.math.Vector3f.YP.rotationDegrees(180));
        *///? }
        poseStack.scale(scale, -scale, scale);
    }

}
