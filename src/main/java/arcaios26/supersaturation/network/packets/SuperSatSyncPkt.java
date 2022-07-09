package arcaios26.supersaturation.network.packets;

import arcaios26.supersaturation.data.CapabilitySuperSat;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class SuperSatSyncPkt {

    private final CompoundNBT nbt;

    public SuperSatSyncPkt(PacketBuffer buf) {
        nbt = buf.readNbt();
    }

    public SuperSatSyncPkt(CompoundNBT nbt) {
        this.nbt = nbt;
    }

    public void toBytes(PacketBuffer buf) {
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
        PlayerEntity player = Minecraft.getInstance().player;

        if (player.level.isClientSide) {
            ctx.get().enqueueWork(() -> {
                player.getCapability(CapabilitySuperSat.SUPER_SAT).ifPresent(cap -> CapabilitySuperSat.SUPER_SAT.readNBT(cap, null, nbt));
            });
        }
    }

    public void handleServer(Supplier<NetworkEvent.Context> ctx) {}
}
