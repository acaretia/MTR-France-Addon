package fr.adlmrl.mtrfra.mod.entity;

import fr.adlmrl.mtrfra.mod.block.base.Sittable;
import org.mtr.mapping.holder.BlockPos;
import org.mtr.mapping.holder.BlockState;
import org.mtr.mapping.holder.EntityType;
import org.mtr.mapping.holder.PlayerEntity;
import org.mtr.mapping.holder.Vector3d;
import org.mtr.mapping.holder.World;
import org.mtr.mapping.mapper.EntityExtension;

public class SeatEntity extends EntityExtension {

    private double savedX, savedY, savedZ;
    private int lifetimeTicks = 0;
    private boolean mounted = false;

    public SeatEntity(EntityType<?> type, World world) {
        super(type, world);
        setInvisible(true);
        setNoGravity(true);
        setNoClipMapped(true);
    }

    public void savePlayerPosition(PlayerEntity player) {
        final Vector3d pos = player.getPos();
        savedX = pos.getXMapped();
        savedY = pos.getYMapped();
        savedZ = pos.getZMapped();
    }

    public double getSavedX() {
        return savedX;
    }

    public double getSavedY() {
        return savedY;
    }

    public double getSavedZ() {
        return savedZ;
    }

    public void setMounted() {
        mounted = true;
    }

    @Override
    public void tick2() {
        lifetimeTicks++;
        if (lifetimeTicks <= 3) {
            return;
        }
        if (!mounted && lifetimeTicks > 20) {
            kill2();
            return;
        }
        if (mounted && lifetimeTicks > 5) {
            final BlockPos blockPos = getBlockPos2();
            final World world = getEntityWorld2();
            final BlockState stateHere = world.getBlockState(blockPos);
            final BlockPos above = new BlockPos(blockPos.getX(), blockPos.getY() + 1, blockPos.getZ());
            final BlockState stateAbove = world.getBlockState(above);
            if (!(stateHere.getBlock().data instanceof Sittable) && !(stateAbove.getBlock().data instanceof Sittable)) {
                kill2();
            }
        }
    }

    @Override
    protected void initDataTracker2() {}

}
