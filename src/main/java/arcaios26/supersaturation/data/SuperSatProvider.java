package arcaios26.supersaturation.data;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class SuperSatProvider implements ICapabilitySerializable<CompoundTag> {
    private final SuperSat sat = new SuperSat();
    private final LazyOptional<ISuperSat> satOptional = LazyOptional.of(() -> sat);

    public void invalidate() {
        satOptional.invalidate();
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        return CapabilitySuperSat.SUPER_SAT.orEmpty(cap, satOptional);
    }


    @Override
    public CompoundTag serializeNBT() {
        if (CapabilitySuperSat.SUPER_SAT == null) {
            return new CompoundTag();
        } else {
            CompoundTag tag = new CompoundTag();
            tag.putFloat("saturation", sat.getSat());
            tag.putInt("hunger", sat.getHunger());
            return tag;
        }
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        if (CapabilitySuperSat.SUPER_SAT != null) {
            float saturation = (nbt).getFloat("saturation");
            int hunger = (nbt).getInt("hunger");
            sat.setSat(saturation);
            sat.setHunger(hunger);
        }
    }
}
