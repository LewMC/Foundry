package net.lewmc.foundry;

import net.lewmc.foundry.utils.LogUtil;
import net.lewmc.foundry.utils.UpdateUtil;
import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

/**
 * The main Essence class.
 */
public class Foundry extends JavaPlugin {
    /**
     * The logging system.
     */
    private final LogUtil log = new LogUtil(this);

    public GlobalSettings settings = new GlobalSettings();

    public String pluginId = "Foundry";

    /**
     * This function runs when Essence is enabled.
     */
    @Override
    public void onEnable() {
        if (!Objects.equals(System.getProperty("FOUNDRY_INIT", ""), "TRUE")) {
            this.log.info("");
            this.log.info("FOUNDRY LOGO");
            this.log.info("");
            this.log.info("Running Foundry version " + this.getDescription().getVersion() + ".");
            this.log.info("Please report any issues with Foundry to our GitHub repository: https://github.com/lewmc/foundry/issues");
            this.log.info("");
            this.log.info("Please consider leaving us a review at https://www.spigotmc.org/resources/essence.114553");
            this.log.info("");
            this.log.info("Beginning startup...");
            this.log.info("");

            this.settings.verbose = this.getConfig().getBoolean("global.verbose");

            if (this.settings.verbose) {
                this.log.warn("Verbose mode is ENABLED.");
                this.log.warn("This will likely cause a lot of console spam.");
                this.log.warn("You should only enable this if you're having problems.");
                this.log.info("");
            }

            if (!Bukkit.getOnlineMode()) {
                this.log.severe(">> Your server is running in offline mode.");
                this.log.warn(">> Homes set in offline mode may not save properly if you switch back to online mode.");
                this.log.warn(">> Homes set in online mode may not work properly in offline mode.");
                this.log.info("");
            }

            this.loadCommands();
            this.loadEventHandlers();
            this.loadTabCompleters();

            UpdateUtil update = new UpdateUtil(this);
            update.versionCheck();
            update.updateFoundry();

            this.log.info("");
            this.log.info("Startup completed.");
            this.log.severe("");
            this.log.severe("WARNING: RELOAD DETECTED!");
            this.log.severe("");
            this.log.severe("This may cause issues with Essence, other plugins, and your server overall.");
            this.log.severe("These issues include breaking permissions and other crashing exceptions.");
            this.log.severe("If you are reloading datapacks use /minecraft:reload instead.");
            this.log.severe("");
            this.log.severe("WE HIGHLY RECOMMEND RESTARTING YOUR SERVER.");
            this.log.severe("");
            this.log.severe("We will not provide support for any issues when plugin reloaders are used.");
            this.log.severe("");
            this.log.severe("More info: https://madelinemiller.dev/blog/problem-with-reload");
            this.log.severe("");

            int pluginId = 25909;
            Metrics metrics = new Metrics(this, pluginId);

            System.setProperty("FOUNDRY_INIT", "TRUE");
        }
    }

    /**
     * Loads and registers the plugin's command handlers.
     */
    private void loadCommands() {
    }

    /**
     * Loads and registers all tab completers.
     */
    private void loadTabCompleters() {
    }

    /**
     * Loads and registers all the plugin's event handlers.
     */
    private void loadEventHandlers() {
    }
}
