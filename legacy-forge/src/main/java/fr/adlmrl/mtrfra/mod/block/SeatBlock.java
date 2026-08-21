package fr.adlmrl.mtrfra.mod.block;

import fr.adlmrl.mtrfra.mod.block.base.DirectionalBlock;
import fr.adlmrl.mtrfra.mod.block.base.Sittable;
import fr.adlmrl.mtrfra.mod.entity.SeatEntity;
import fr.adlmrl.mtrfra.mod.registry.ModEntities;
import org.mtr.mapping.holder.ActionResult;
import org.mtr.mapping.holder.BlockHitResult;
import org.mtr.mapping.holder.BlockPos;
import org.mtr.mapping.holder.BlockSettings;
import org.mtr.mapping.holder.BlockState;
import org.mtr.mapping.holder.BlockView;
import org.mtr.mapping.holder.Direction;
import org.mtr.mapping.holder.Entity;
import org.mtr.mapping.holder.Hand;
import org.mtr.mapping.holder.PlayerEntity;
import org.mtr.mapping.holder.ServerWorld;
import org.mtr.mapping.holder.ShapeContext;
import org.mtr.mapping.holder.VoxelShape;
import org.mtr.mapping.holder.World;
import org.mtr.mod.block.IBlock;

public class SeatBlock extends DirectionalBlock implements Sittable {

    private final double x1, y1, z1, x2, y2, z2;
    private final double seatHeight;

    public SeatBlock(BlockSettings settings, double x1, double y1, double z1, double x2, double y2, double z2, double seatHeight) {
        super(settings);
        this.x1 = x1;
        this.y1 = y1;
        this.z1 = z1;
        this.x2 = x2;
        this.y2 = y2;
        this.z2 = z2;
        this.seatHeight = seatHeight;
    }

    @Override
    public ActionResult onUse2(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (player.isSneaking()) {
            return ActionResult.FAIL;
        }
        if (!world.isClient()) {
            if (player.getVehicle() != null) {
                return ActionResult.FAIL;
            }

            final Direction facing = IBlock.getStatePropertySafe(state, FACING);
            double seatX = pos.getX() + 0.5;
            final double seatY = pos.getY() + seatHeight;
            double seatZ = pos.getZ() + 0.5;
            seatX -= facing.getOffsetX() * 0.15;
            seatZ -= facing.getOffsetZ() * 0.15;

            final SeatEntity seat = new SeatEntity(ModEntities.SEAT.get(), world);
            seat.setPosition2(seatX, seatY, seatZ);
            seat.setBodyYaw2(facing.asRotation());
            seat.savePlayerPosition(player);

            ServerWorld.cast(world).spawnEntity(new Entity(seat));
            player.startRiding(new Entity(seat));
            seat.setMounted();
        }
        return ActionResult.SUCCESS;
    }

    @Override
    public VoxelShape getOutlineShape2(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        final Direction facing = IBlock.getStatePropertySafe(state, FACING);
        return IBlock.getVoxelShapeByDirection(x1, y1, z1, x2, y2, z2, facing);
    }

    @Override
    public boolean isTranslucent2(BlockState state, BlockView world, BlockPos pos) {
        return true;
    }

    @Override
    public float getAmbientOcclusionLightLevel2(BlockState state, BlockView world, BlockPos pos) {
        return 1;
    }

}
