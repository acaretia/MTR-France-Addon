package fr.adlmrl.mtrfra.mod.panneau;

import org.mtr.mapping.holder.BlockPos;
import org.mtr.mapping.registry.PacketHandler;
import org.mtr.mapping.tool.PacketBufferReceiver;
import org.mtr.mapping.tool.PacketBufferSender;

public final class PacketOpenPanneauConfigScreen extends PacketHandler {

    private final int x;
    private final int y;
    private final int z;
    private final String title;
    private final String subtitle;
    private final int category;
    private final int maxCategory;

    public PacketOpenPanneauConfigScreen(BlockPos pos, String title, String subtitle, int category, int maxCategory) {
        this.x = pos.getX();
        this.y = pos.getY();
        this.z = pos.getZ();
        this.title = title;
        this.subtitle = subtitle;
        this.category = category;
        this.maxCategory = maxCategory;
    }

    public PacketOpenPanneauConfigScreen(PacketBufferReceiver packetBufferReceiver) {
        x = packetBufferReceiver.readInt();
        y = packetBufferReceiver.readInt();
        z = packetBufferReceiver.readInt();
        title = packetBufferReceiver.readString();
        subtitle = packetBufferReceiver.readString();
        category = packetBufferReceiver.readInt();
        maxCategory = packetBufferReceiver.readInt();
    }

    @Override
    public void write(PacketBufferSender packetBufferSender) {
        packetBufferSender.writeInt(x);
        packetBufferSender.writeInt(y);
        packetBufferSender.writeInt(z);
        packetBufferSender.writeString(title);
        packetBufferSender.writeString(subtitle);
        packetBufferSender.writeInt(category);
        packetBufferSender.writeInt(maxCategory);
    }

    @Override
    public void runClient() {
        PanneauConfigScreenOpener.open(new BlockPos(x, y, z), title, subtitle, category, maxCategory);
    }

}
