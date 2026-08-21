package fr.adlmrl.mtrfra.mod.block;

import fr.adlmrl.mtrfra.mod.block.base.DirectionalBlock;
import fr.adlmrl.mtrfra.mod.itinerary.PacketOpenItineraryScreen;
import fr.adlmrl.mtrfra.mod.registry.MTRFRARegistry;
import org.mtr.mapping.holder.ActionResult;
import org.mtr.mapping.holder.BlockHitResult;
import org.mtr.mapping.holder.BlockPos;
import org.mtr.mapping.holder.BlockSettings;
import org.mtr.mapping.holder.BlockState;
import org.mtr.mapping.holder.Hand;
import org.mtr.mapping.holder.PlayerEntity;
import org.mtr.mapping.holder.ServerPlayerEntity;
import org.mtr.mapping.holder.World;

public class BlockSystemMap extends DirectionalBlock {

    public BlockSystemMap(BlockSettings blockSettings) {
        super(blockSettings.nonOpaque());
    }

    @Override
    public ActionResult onUse2(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient()) {
            MTRFRARegistry.REGISTRY.sendPacketToClient(ServerPlayerEntity.cast(player), new PacketOpenItineraryScreen());
        }
        return ActionResult.SUCCESS;
    }

}
