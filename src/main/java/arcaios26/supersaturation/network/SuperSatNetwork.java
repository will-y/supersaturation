package arcaios26.supersaturation.network;

import arcaios26.supersaturation.SuperSaturation;
import arcaios26.supersaturation.network.packets.SuperSatSyncPkt;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class SuperSatNetwork {

    private static SimpleChannel INSTANCE;
    private static int ID = 0;

    private static int nextID() {
        return ID++;
    }

    public static void registerMessages() {
        INSTANCE = NetworkRegistry.newSimpleChannel(new ResourceLocation(SuperSaturation.MODID, "supersaturation"),
                () -> "1.0",
                s -> true,
                s -> true);

        INSTANCE.messageBuilder(SuperSatSyncPkt.class, nextID())
                .encoder(SuperSatSyncPkt::toBytes)
                .decoder(SuperSatSyncPkt::new)
                .consumer(SuperSatSyncPkt::handle)
                .add();
    }

    public static void sendToClient(Object packet, ServerPlayer player) {
        INSTANCE.sendTo(packet, player.connection.connection, NetworkDirection.PLAY_TO_CLIENT);
    }

    public static void sendToServer(Object packet) {
        INSTANCE.sendToServer(packet);
    }
}
