package arcaios26.supersaturation.data;

import arcaios26.supersaturation.network.SuperSatNetwork;
import arcaios26.supersaturation.network.packets.SuperSatSyncPkt;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.nbt.CompoundTag;

public class SuperSat implements ISuperSat {
    private float saturation = 0.0f;
    private int hunger = 0;

    @Override
    public float getSat() {
        return this.saturation;
    }

    @Override
    public void setSat(float sat) {
        this.saturation = sat;
    }

    @Override
    public int getHunger() {
        return this.hunger;
    }

    @Override
    public void setHunger(int hunger) {
        this.hunger = hunger;
    }

    @Override
    public void sync(ServerPlayer player) {

        if (!player.level.isClientSide()) {
            player.getCapability(CapabilitySuperSat.SUPER_SAT, null).ifPresent(cap -> {
                CompoundTag nbt = new CompoundTag();
                nbt.putFloat("saturation", cap.getSat());
                nbt.putInt("hunger", cap.getHunger());
                SuperSatNetwork.sendToClient(new SuperSatSyncPkt(nbt), player);
            });
        }
    }
}
