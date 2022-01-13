package win.senoe.forgespoof;

import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import win.senoe.forgespoof.commands.ForgeSpoofCommand;
import win.senoe.forgespoof.forge.ConfigurationHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Mod(modid = ForgeSpoof.MODID, version = ForgeSpoof.VERSION)
public class ForgeSpoof {

    public static final String NAME = "ForgeSpoof", MODID = "forgespoof", VERSION = "1.0";
    public static List<String> modIds = new ArrayList<>(Collections.singletonList(MODID));
    public static final Logger LOGGER = LogManager.getLogger(NAME);
    public static ConfigurationHandler configHandler;

    @EventHandler
    public void init(FMLInitializationEvent e) {
        LOGGER.info("Initializing...");
        ClientCommandHandler.instance.registerCommand(new ForgeSpoofCommand());
    }

    @EventHandler
    public void preInit(FMLPreInitializationEvent e) {
        configHandler = new ConfigurationHandler(e.getSuggestedConfigurationFile());
    }

}
