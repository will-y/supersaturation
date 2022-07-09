package arcaios26.supersaturation.data;

import arcaios26.supersaturation.network.SuperSatNetwork;
import arcaios26.supersaturation.network.packets.SuperSatSyncPkt;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;

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
    public void sync(ServerPlayerEntity player) {

        if (!player.level.isClientSide()) {
            player.getCapability(CapabilitySuperSat.SUPER_SAT, null).ifPresent(cap -> {
                CompoundNBT nbt = (CompoundNBT) CapabilitySuperSat.SUPER_SAT.writeNBT(cap, null);
                SuperSatNetwork.sendToClient(new SuperSatSyncPkt(nbt), player);
            });
        }
    }
}
