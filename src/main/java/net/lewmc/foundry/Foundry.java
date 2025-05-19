package net.lewmc.foundry;

import net.lewmc.foundry.utils.LogUtil;
import net.lewmc.foundry.utils.SecurityUtil;
import org.bstats.bukkit.Metrics;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * The main Essence class.
 */
public class Foundry extends JavaPlugin {

    /**
     * The logging system.
     */
    private final LogUtil log = new LogUtil(this.config);

    public String pluginId = "Foundry";

    public FoundryConfig config;

    /**
     * This function runs when Essence is enabled.
     */
    @Override
    public void onEnable() {
        this.log.info("Running Foundry version " + this.getDescription().getVersion() + ".");
        this.log.info("Please report any issues with Foundry to our GitHub repository: https://github.com/lewmc/foundry/issues");
        this.log.info("");
        this.log.info("Beginning startup...");
        this.log.info("");

        this.config = new FoundryConfig("Foundry");

        if (this.config.verbose) {
            this.log.warn("Verbose mode is ENABLED.");
            this.log.warn("This will likely cause a lot of console spam.");
            this.log.warn("You should only enable this if you're having problems.");
            this.log.info("");
        }

        new SecurityUtil(this.config).startupWatchdog();
        new Metrics(this, 25909);
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
