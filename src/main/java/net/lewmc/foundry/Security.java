package net.lewmc.foundry;

import java.util.Objects;
import java.util.regex.Pattern;

/**
 * Security Utility
 */
public class Security {
    /**
     * Foundry's Configuration
     */
    private final FoundryConfig config;

    /**
     * Constructor for the Security Utility.
     * @param config FoundryConfig - Foundry's configuration.
     */
    public Security(FoundryConfig config) {
        this.config = config;
    }
    
    /**
     * Checks for special characters.
     * @param string String - The string to check
     * @return boolean - If it has special characters
     */
    public boolean hasSpecialCharacters(String string) {
        return Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE).matcher(string).find();
    }

    /**
     * startWatchdog keeps tabs on the startup processes, preventing your plugin from being started twice. You should only call this once during your onEnable function run.
     */
    public void startWatchdog() {
        Logger log = new Logger(this.config);

        String plugin = this.config.pluginId.toUpperCase();

        if (Objects.equals(System.getProperty("FOUNDRY_WATCHDOG_"+plugin, ""), "ACTIVE")) {
            log.severe("");
            log.severe("WARNING: RELOAD DETECTED!");
            log.severe("");
            log.severe("This may cause issues with "+plugin+", other plugins, and your server overall.");
            log.severe("These issues include breaking permissions and other crashing exceptions.");
            log.severe("If you are reloading datapacks use /minecraft:reload instead.");
            log.severe("");
            log.severe("WE HIGHLY RECOMMEND RESTARTING YOUR SERVER.");
            log.severe("");
            log.severe("We will not provide support for any issues when plugin reloaders are used.");
            log.severe("");
            log.severe("More info: https://madelinemiller.dev/blog/problem-with-reload");
            log.severe("");
        }

        log.info("Watchdog started for " + plugin + ".");

        System.setProperty("FOUNDRY_WATCHDOG_"+plugin, "ACTIVE");
    }

    /**
     * Stops the watchdog service.
     */
    public void stopWatchdog() {
        System.setProperty("FOUNDRY_WATCHDOG_"+this.config.pluginId.toUpperCase(),"INACTIVE");
    }
}
