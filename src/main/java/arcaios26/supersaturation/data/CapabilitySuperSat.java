package arcaios26.supersaturation.data;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;


public class CapabilitySuperSat {

    public static Capability<ISuperSat> SUPER_SAT = CapabilityManager.get(new CapabilityToken<>() {});

}
