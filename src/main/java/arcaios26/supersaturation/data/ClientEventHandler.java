package arcaios26.supersaturation.data;

import arcaios26.supersaturation.setup.Config;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

public class ClientEventHandler {

    public static void onRenderGameOverlayText(RenderGameOverlayEvent.Text event) {
        if (Minecraft.getInstance().options.renderDebug) {
            Player player = Minecraft.getInstance().player;

            player.getCapability(CapabilitySuperSat.SUPER_SAT, null).ifPresent(sat -> {
                event.getRight().add("");
                String s = String.format("%.2f", sat.getSat());
                event.getRight().add("Oversaturation: " + s);
                if (Config.HUNGEROVERFLOW.get())
                    if (!Config.HUNGERTOSAT.get())
                        event.getRight().add("Overhunger: " + sat.getHunger());
            });
        }
    }
}
