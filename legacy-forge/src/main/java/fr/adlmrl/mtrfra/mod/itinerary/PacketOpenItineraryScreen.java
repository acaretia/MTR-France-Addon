package fr.adlmrl.mtrfra.mod.itinerary;

import org.mtr.mapping.registry.PacketHandler;
import org.mtr.mapping.tool.PacketBufferReceiver;
import org.mtr.mapping.tool.PacketBufferSender;

public final class PacketOpenItineraryScreen extends PacketHandler {

    public PacketOpenItineraryScreen(PacketBufferReceiver packetBufferReceiver) {}

    public PacketOpenItineraryScreen() {}

    @Override
    public void write(PacketBufferSender packetBufferSender) {}

    @Override
    public void runClient() {
        ItineraryScreenClient.open();
    }

}
