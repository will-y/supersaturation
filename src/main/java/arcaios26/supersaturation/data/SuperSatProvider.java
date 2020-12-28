package arcaios26.supersaturation.data;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class SuperSatProvider implements ICapabilitySerializable<CompoundNBT> {
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
    public CompoundNBT serializeNBT() {
        if (CapabilitySuperSat.SUPER_SAT == null) {
            return new CompoundNBT();
        } else {
            return (CompoundNBT) CapabilitySuperSat.SUPER_SAT.writeNBT(sat, null);
        }
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        if (CapabilitySuperSat.SUPER_SAT != null) {
            CapabilitySuperSat.SUPER_SAT.readNBT(sat, null, nbt);
        }
    }
}
