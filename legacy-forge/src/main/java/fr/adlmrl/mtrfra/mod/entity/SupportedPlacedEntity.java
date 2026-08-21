package fr.adlmrl.mtrfra.mod.entity;

import fr.adlmrl.mtrfra.mod.Init;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import org.mtr.mapping.holder.BlockPos;
import org.mtr.mapping.holder.BlockState;
import org.mtr.mapping.holder.BlockView;
import org.mtr.mapping.holder.EntityType;
import org.mtr.mapping.holder.Item;
import org.mtr.mapping.holder.VoxelShape;
import org.mtr.mapping.holder.World;
import org.mtr.mapping.mapper.EntityExtension;

public abstract class SupportedPlacedEntity extends EntityExtension {

    private static final int UNSUPPORTED_GRACE_TICKS = 30;

    protected double anchorX, anchorY, anchorZ;
    private boolean anchorInitialized = false;
    protected BlockPos supportPos;
    protected Item dropItem;
    protected int lifetimeTicks = 0;
    private int unsupportedTicks = 0;

    protected SupportedPlacedEntity(EntityType<?> type, World world) {
        super(type, world);
        setNoGravity(true);
    }

    public void placeAt(double x, double y, double z, BlockPos supportPos, float yaw, Item dropItem) {
        placeAt(x, y, z, supportPos, yaw, 0, dropItem);
    }

    public void placeAt(double x, double y, double z, BlockPos supportPos, float yaw, float pitch, Item dropItem) {
        anchorX = x;
        anchorY = y;
        anchorZ = z;
        anchorInitialized = true;
        this.supportPos = supportPos;
        this.dropItem = dropItem;
        setPosition2(anchorX, anchorY, anchorZ);
        setBodyYaw2(yaw);
        updatePositionAndAngles2(anchorX, anchorY, anchorZ, yaw, pitch);
    }

    public BlockPos getSupportPos() {
        return supportPos;
    }

    @Override
    public void tick2() {
        lifetimeTicks++;
        if (getEntityWorld2().isClient()) {
            onClientTick();
            return;
        }

        if (!anchorInitialized) {
            anchorX = getX2();
            anchorY = getY2();
            anchorZ = getZ2();
            anchorInitialized = true;
            if (supportPos == null) {
                supportPos = new BlockPos((int) Math.floor(anchorX), (int) Math.floor(anchorY - 0.01), (int) Math.floor(anchorZ));
            }
        }

        setPosition2(anchorX, anchorY, anchorZ);

        if (lifetimeTicks < 20 || supportPos == null) {
            return;
        }

        if (hasSupport()) {
            unsupportedTicks = 0;
            return;
        }
        unsupportedTicks++;
        if (unsupportedTicks >= UNSUPPORTED_GRACE_TICKS) {
            Init.LOGGER.warn("{} at {} lost its support at {} (lifetime {} ticks) - self-destructing", getClass().getSimpleName(), getPos2(), supportPos, lifetimeTicks);
            kill2();
        }
    }

    protected void onClientTick() {}

    private boolean hasSupport() {
        try {
            final World world = getEntityWorld2();
            final BlockState state = world.getBlockState(supportPos);
            final VoxelShape shape = state.getOutlineShape(BlockView.cast(world), supportPos);
            return !shape.isEmpty();
        } catch (final Exception exception) {
            Init.LOGGER.warn("{} support check failed for {}", getClass().getSimpleName(), supportPos, exception);
            return true;
        }
    }

    @Override
    public boolean isPushable2() {
        return false;
    }

    @Override
    public boolean isAttackable2() {
        return true;
    }

    @Override
    public boolean isPickable() {
        return true;
    }

    @Override
    public boolean canBeCollidedWith() {
        return true;
    }

    protected abstract net.minecraft.world.item.Item resolveDropItem();

    private void dropAndKill() {
        final net.minecraft.world.item.Item item = resolveDropItem();
        if (item != null) {
            spawnAtLocation(item);
        }
        kill2();
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        if (!getEntityWorld2().isClient() && isAlive2()) {
            if (isBrokenByCreativePlayer(source)) {
                kill2();
            } else {
                dropAndKill();
            }
        }
        return true;
    }

    private static boolean isBrokenByCreativePlayer(DamageSource source) {
        final Entity attacker = source.getEntity();
        return attacker instanceof Player player && player.isCreative();
    }

    @Override
    protected void initDataTracker2() {}

}
