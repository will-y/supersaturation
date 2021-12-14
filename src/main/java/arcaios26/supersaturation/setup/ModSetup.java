package arcaios26.supersaturation.setup;

import arcaios26.supersaturation.SuperSaturation;
import arcaios26.supersaturation.data.CapabilitySuperSat;
import arcaios26.supersaturation.data.SuperSatEventHandler;
import arcaios26.supersaturation.network.SuperSatNetwork;
import net.minecraft.entity.Entity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(modid = SuperSaturation.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModSetup {
    public static void init(final FMLCommonSetupEvent event) {
        CapabilitySuperSat.register();
        SuperSatNetwork.registerMessages();

        MinecraftForge.EVENT_BUS.addGenericListener(Entity.class, SuperSatEventHandler::onAttachCapabilitiesEvent);
        MinecraftForge.EVENT_BUS.addListener(SuperSatEventHandler::onStart);
        MinecraftForge.EVENT_BUS.addListener(SuperSatEventHandler::onStop);
        MinecraftForge.EVENT_BUS.addListener(SuperSatEventHandler::onFinish);
        MinecraftForge.EVENT_BUS.addListener(SuperSatEventHandler::onPlayerTickEvent);
    }
}
