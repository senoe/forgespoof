package win.senoe.forgespoof.forge;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import win.senoe.forgespoof.ForgeSpoof;

import java.io.File;
import java.util.Arrays;

public class ConfigurationHandler {

    private final Configuration config;
    private final Property modidsProperty;

    public ConfigurationHandler(File suggestedConfigFile) {
        this.config = new Configuration(suggestedConfigFile);
        this.modidsProperty = this.config.get(Configuration.CATEGORY_GENERAL, "modids", new String[]{ForgeSpoof.MODID});
        this.load();
        this.save();
    }

    @SubscribeEvent
    public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent e) {
        if (e.modID.equalsIgnoreCase(ForgeSpoof.MODID)) {
            this.load();
        }
    }

    public void load() {
        ForgeSpoof.modIds.clear();
        ForgeSpoof.modIds.addAll(Arrays.asList(modidsProperty.getStringList()));
    }

    public void save() {
        this.modidsProperty.set(ForgeSpoof.modIds.toArray(new String[0]));
        this.config.save();
    }

}
