package fr.adlmrl.mtrfra.mod.itinerary;

import fr.adlmrl.mtrfra.mod.registry.MTRFRARegistry;
import org.mtr.mapping.holder.MinecraftServer;
import org.mtr.mapping.holder.ServerPlayerEntity;
import org.mtr.mapping.holder.World;
import org.mtr.mapping.registry.PacketHandler;
import org.mtr.mapping.tool.PacketBufferReceiver;
import org.mtr.mapping.tool.PacketBufferSender;

public final class PacketSearchItinerary extends PacketHandler {

    private final String fromStationName;
    private final String toStationName;

    public PacketSearchItinerary(String fromStationName, String toStationName) {
        this.fromStationName = fromStationName;
        this.toStationName = toStationName;
    }

    public PacketSearchItinerary(PacketBufferReceiver packetBufferReceiver) {
        fromStationName = packetBufferReceiver.readString();
        toStationName = packetBufferReceiver.readString();
    }

    @Override
    public void write(PacketBufferSender packetBufferSender) {
        packetBufferSender.writeString(fromStationName);
        packetBufferSender.writeString(toStationName);
    }

    @Override
    public void runServer(MinecraftServer minecraftServer, ServerPlayerEntity serverPlayerEntity) {
        final World world = World.cast(serverPlayerEntity.getServerWorld());
        ItineraryFinder.search(world, minecraftServer, fromStationName, toStationName, result ->
                MTRFRARegistry.REGISTRY.sendPacketToClient(serverPlayerEntity, new PacketItineraryResult(result))
        );
    }

}
