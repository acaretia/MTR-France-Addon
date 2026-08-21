package fr.adlmrl.mtrfra.mod.block.panneau;

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

public class PanneauRATPRenderer extends BlockEntityRenderer<PanneauRATPBase.BlockEntityBase> {

    private static final float SCALE = 1 / 48F;

    public PanneauRATPRenderer(Argument argument) {
        super(argument);
    }

    @Override
    public void render(PanneauRATPBase.BlockEntityBase entity, float tickDelta, GraphicsHolder graphicsHolder, int light, int overlay) {
        final String title = entity.getEffectiveTitle();
        final String subtitle = entity.subtitle;
        if (title.isEmpty() && subtitle.isEmpty()) {
            return;
        }

        final org.mtr.mapping.holder.BlockState state = entity.getCachedState2();
        final Object rawBlock = state.getBlock().data;
        if (!(rawBlock instanceof PanneauRATPBase panneauBlock)) {
            return;
        }

        final GraphicsHolderAccessor accessor = (GraphicsHolderAccessor) (Object) graphicsHolder;
        final PoseStack poseStack = accessor.getMatrixStack();
        final Direction facing = IBlock.getStatePropertySafe(state, DirectionHelper.FACING);

        poseStack.pushPose();
        poseStack.translate(0.5, 0, 0.5);
        VersionCompat.mulPoseY(poseStack, -facing.asRotation());
        poseStack.translate(-0.5, panneauBlock.textY() / 16.0, panneauBlock.textZ() / 16.0 - 0.5);
        poseStack.scale(SCALE, -SCALE, SCALE);

        if (!title.isEmpty()) {
            graphicsHolder.drawCenteredText(title, 0, 0, 0xFFFFFF);
        }
        if (!subtitle.isEmpty()) {
            graphicsHolder.drawCenteredText(TextHelper.literal(subtitle).formatted(TextFormatting.ITALIC), 0, 10, 0xFFFFFF);
        }

        poseStack.popPose();
    }

}
