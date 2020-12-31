package arcaios26.supersaturation.data;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

import javax.annotation.Nullable;

public class CapabilitySuperSat {
    @CapabilityInject(ISuperSat.class)
    public static Capability<ISuperSat> SUPER_SAT = null;

    public static void register() {
        CapabilityManager.INSTANCE.register(ISuperSat.class, new Storage(), SuperSat::new);
    }

    public static class Storage implements Capability.IStorage<ISuperSat> {

        @Nullable
        @Override
        public INBT writeNBT(Capability<ISuperSat> capability, ISuperSat instance, Direction side) {
            CompoundNBT tag = new CompoundNBT();
            tag.putFloat("saturation", instance.getSat());
            tag.putInt("hunger", instance.getHunger());
            return tag;
        }

        @Override
        public void readNBT(Capability<ISuperSat> capability, ISuperSat instance, Direction side, INBT nbt) {
            float sat = ((CompoundNBT) nbt).getFloat("saturation");
            int hunger = ((CompoundNBT) nbt).getInt("hunger");
            instance.setSat(sat);
            instance.setHunger(hunger);
        }
    }
}
