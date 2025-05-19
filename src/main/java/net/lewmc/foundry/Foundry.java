package net.lewmc.foundry;

import net.lewmc.foundry.utils.LogUtil;
import net.lewmc.foundry.utils.SecurityUtil;
import org.bstats.bukkit.Metrics;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * The main Essence class.
 */
public class Foundry extends JavaPlugin {

    public FoundryConfig config;

    /**
     * This function runs when Essence is enabled.
     */
    @Override
    public void onEnable() {
        this.config = new FoundryConfig("Foundry");
        LogUtil log = new LogUtil(this.config);

        log.info("█████████████████████████████████████████████");
        log.info("█▄─▄▄─█─▄▄─█▄─██─▄█▄─▀█▄─▄█▄─▄▄▀█▄─▄▄▀█▄─█─▄█");
        log.info("██─▄███─██─██─██─███─█▄▀─███─██─██─▄─▄██▄─▄██");
        log.info("▀▄▄▄▀▀▀▄▄▄▄▀▀▄▄▄▄▀▀▄▄▄▀▀▄▄▀▄▄▄▄▀▀▄▄▀▄▄▀▀▄▄▄▀▀");
        log.info("");
        log.info("█▄─▄▄─█─▄▄─█▄─██─▄█▄─▀█▄─▄█▄─▄▄▀█▄─▄▄▀█▄─█─▄█");
        log.info("██─▄███─██─██─██─███─█▄▀─███─██─██─▄─▄██▄─▄██");
        log.info("▀▄▄▄▀▀▀▄▄▄▄▀▀▄▄▄▄▀▀▄▄▄▀▀▄▄▀▄▄▄▄▀▀▄▄▀▄▄▀▀▄▄▄▀▀");
        log.info("Running Foundry version " + this.getDescription().getVersion() + ".");
        log.info("Please report any issues with Foundry to our GitHub repository: https://github.com/lewmc/foundry/issues");

        if (this.config.verbose) {
            log.warn("Verbose mode is ENABLED.");
            log.warn("This will likely cause a lot of console spam.");
            log.warn("You should only enable this if you're having problems.");
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
