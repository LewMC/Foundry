package net.lewmc.foundry;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * The main Foundry class.
 */
public class Foundry extends JavaPlugin {

    /**
     * This function runs when Foundry is enabled.
     */
    @Override
    public void onEnable() {
        FoundryConfig config = new FoundryConfig(this);

        Logger log = new Logger(config);

        log.info("");
        log.info("███████╗ █████╗ ██╗   ██╗███╗  ██╗██████╗ ██████╗ ██╗   ██╗");
        log.info("██╔════╝██╔══██╗██║   ██║████╗ ██║██╔══██╗██╔══██╗╚██╗ ██╔╝");
        log.info("█████╗  ██║  ██║██║   ██║██╔██╗██║██║  ██║██████╔╝ ╚████╔╝");
        log.info("██╔══╝  ██║  ██║██║   ██║██║╚████║██║  ██║██╔══██╗  ╚██╔╝");
        log.info("██║     ╚█████╔╝╚██████╔╝██║ ╚███║██████╔╝██║  ██║   ██║");
        log.info("╚═╝      ╚════╝  ╚═════╝ ╚═╝  ╚══╝╚═════╝ ╚═╝  ╚═╝   ╚═╝");
        log.info("");
        log.info("Running Foundry version " + this.getDescription().getVersion() + ".");
        log.info("Please report any issues with Foundry to our GitHub repository: https://github.com/lewmc/foundry/issues");
        log.info("");
        log.info("Some plugins may require a specific version of Foundry.");
        log.info("Please check with the plugin author for more information.");
        log.info("");

        new Security(config).startWatchdog();
    }
}
