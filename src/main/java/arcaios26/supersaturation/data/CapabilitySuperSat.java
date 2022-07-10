package arcaios26.supersaturation.data;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;


public class CapabilitySuperSat {
    @CapabilityInject(ISuperSat.class)
    public static Capability<ISuperSat> SUPER_SAT = null;

    public static void register() {
        CapabilityManager.INSTANCE.register(ISuperSat.class);
    }
}
