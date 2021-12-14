package arcaios26.supersaturation.network;

import arcaios26.supersaturation.SuperSaturation;
import arcaios26.supersaturation.data.SuperSat;
import arcaios26.supersaturation.network.packets.SuperSatSyncPkt;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

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

    public static void sendToClient(Object packet, ServerPlayerEntity player) {
        INSTANCE.sendTo(packet, player.connection.netManager, NetworkDirection.PLAY_TO_CLIENT);
    }

    public static void sendToServer(Object packet) {
        INSTANCE.sendToServer(packet);
    }
}
