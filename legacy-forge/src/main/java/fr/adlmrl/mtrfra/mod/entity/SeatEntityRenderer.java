package fr.adlmrl.mtrfra.mod.entity;

import fr.adlmrl.mtrfra.mod.util.Constants;
import org.mtr.mapping.holder.Identifier;
import org.mtr.mapping.mapper.EntityRenderer;
import org.mtr.mapping.mapper.GraphicsHolder;

public class SeatEntityRenderer extends EntityRenderer<SeatEntity> {

    public SeatEntityRenderer(Argument argument) {
        super(argument);
    }

    @Override
    public void render(SeatEntity entity, float yaw, float tickDelta, GraphicsHolder graphicsHolder, int light) {}

    @Override
    public Identifier getTexture2(SeatEntity entity) {
        return new Identifier(Constants.MOD_ID, "textures/entity/empty.png");
    }

}
