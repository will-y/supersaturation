package arcaios26.supersaturation;


import arcaios26.supersaturation.setup.ClientSetup;
import arcaios26.supersaturation.setup.Config;
import arcaios26.supersaturation.setup.ModSetup;
import arcaios26.supersaturation.setup.Registration;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(SuperSaturation.MODID)
public class SuperSaturation {
    public static final String MODID = "supersaturation";
    public static final Logger LOGGER = LogManager.getLogger();

    public SuperSaturation() {
        //ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, Config.CLIENT_CONFIG);
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, Config.SERVER_CONFIG);

        Registration.init();

        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(ModSetup::init);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(ClientSetup::init);
    }
}
