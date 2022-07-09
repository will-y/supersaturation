package arcaios26.supersaturation.network.packets;

import arcaios26.supersaturation.data.CapabilitySuperSat;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class SuperSatSyncPkt {

    private final CompoundTag nbt;

    public SuperSatSyncPkt(FriendlyByteBuf buf) {
        nbt = buf.readNbt();
    }

    public SuperSatSyncPkt(CompoundTag nbt) {
        this.nbt = nbt;
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeNbt(nbt);
    }

    public boolean handle(Supplier<NetworkEvent.Context> ctx) {
        NetworkEvent.Context context = ctx.get();
        switch (context.getDirection().getReceptionSide()) {
            case CLIENT:
                this.handleClient(ctx);
                break;
            case SERVER:
                this.handleServer(ctx);
                break;
        }
        return true;
    }

    @OnlyIn(Dist.CLIENT)
    public void handleClient(Supplier<NetworkEvent.Context> ctx) {
        Player player = Minecraft.getInstance().player;

        if (player.level.isClientSide) {
            ctx.get().enqueueWork(() -> {
                player.getCapability(CapabilitySuperSat.SUPER_SAT).ifPresent(cap -> {
                    float saturation = (nbt).getFloat("saturation");
                    int hunger = (nbt).getInt("hunger");
                    cap.setSat(saturation);
                    cap.setHunger(hunger);
                });
            });
        }
    }

    public void handleServer(Supplier<NetworkEvent.Context> ctx) {}
}
