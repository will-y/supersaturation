package arcaios26.supersaturation.setup;


import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class Config {
    public static final String CATEGORY_OVERFLOW = "overflow";
    public static final String CATEGORY_HUNGER = "hunger";

    public static ForgeConfigSpec SERVER_CONFIG;
    //public static ForgeConfigSpec CLIENT_CONFIG;

    public static ForgeConfigSpec.BooleanValue CANGAIN;
    public static ForgeConfigSpec.BooleanValue HUNGEROVERFLOW;
    public static ForgeConfigSpec.BooleanValue HUNGERTOSAT;

    static {
        ForgeConfigSpec.Builder SERVER_BUILDER = new ForgeConfigSpec.Builder();
        //ForgeConfigSpec.Builder CLIENT_BUILDER = new ForgeConfigSpec.Builder();

        setupOverflowConfig(SERVER_BUILDER);
        setupHungerOverflow(SERVER_BUILDER);

        SERVER_CONFIG = SERVER_BUILDER.build();
        //CLIENT_CONFIG = CLIENT_BUILDER.build();
    }

    private static void setupOverflowConfig(ForgeConfigSpec.Builder SERVER_BUILDER) {
        SERVER_BUILDER.comment("Saturation overflow settings").push(CATEGORY_OVERFLOW);

        CANGAIN = SERVER_BUILDER.comment("Can gain over saturation while over saturated. Default: false").define("canGainWhileOverSaturated", false);

        SERVER_BUILDER.pop();
    }

    private static void setupHungerOverflow(ForgeConfigSpec.Builder SERVER_BUILDER) {
        SERVER_BUILDER.comment("Hunger overflow settings").push(CATEGORY_HUNGER);

        HUNGEROVERFLOW = SERVER_BUILDER.comment("Can hunger overflow. Default: false").define("canHungerOverflow", false);
        HUNGERTOSAT = SERVER_BUILDER.comment("Hunger overflow converts to saturation. Default: true").define("hungerOverflowtoSaturation", true);

        SERVER_BUILDER.pop();
    }
}
