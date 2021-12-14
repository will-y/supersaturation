package arcaios26.supersaturation.setup;

import arcaios26.supersaturation.SuperSaturation;
import arcaios26.supersaturation.data.ClientEventHandler;
import arcaios26.supersaturation.network.SuperSatNetwork;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = SuperSaturation.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientSetup {
    public static void init(final FMLClientSetupEvent event) {
        MinecraftForge.EVENT_BUS.addListener(ClientEventHandler::onRenderGameOverlayText);
    }
}
