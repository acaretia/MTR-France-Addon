package fr.adlmrl.mtrfra.mod.itinerary;

import org.mtr.mapping.registry.PacketHandler;
import org.mtr.mapping.tool.PacketBufferReceiver;
import org.mtr.mapping.tool.PacketBufferSender;

import java.util.ArrayList;
import java.util.List;

public final class PacketItineraryResult extends PacketHandler {

    private final boolean found;
    private final List<ItineraryFinder.Leg> legs;

    public PacketItineraryResult(ItineraryFinder.Result result) {
        found = result.found;
        legs = result.legs;
    }

    public PacketItineraryResult(PacketBufferReceiver packetBufferReceiver) {
        found = packetBufferReceiver.readBoolean();
        final int count = packetBufferReceiver.readInt();
        legs = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            legs.add(new ItineraryFinder.Leg(
                    packetBufferReceiver.readString(),
                    packetBufferReceiver.readString(),
                    packetBufferReceiver.readString(),
                    packetBufferReceiver.readLong(),
                    packetBufferReceiver.readLong()
            ));
        }
    }

    @Override
    public void write(PacketBufferSender packetBufferSender) {
        packetBufferSender.writeBoolean(found);
        packetBufferSender.writeInt(legs.size());
        for (final ItineraryFinder.Leg leg : legs) {
            packetBufferSender.writeString(leg.routeName);
            packetBufferSender.writeString(leg.fromStation);
            packetBufferSender.writeString(leg.toStation);
            packetBufferSender.writeLong(leg.startTime);
            packetBufferSender.writeLong(leg.endTime);
        }
    }

    @Override
    public void runClient() {
        ItineraryScreenClient.applyResult(found, legs);
    }

}
