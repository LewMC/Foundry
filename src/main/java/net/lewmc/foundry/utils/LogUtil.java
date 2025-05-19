package net.lewmc.foundry.utils;

import net.lewmc.foundry.FoundryConfig;
import org.bukkit.Bukkit;

/**
 * Essence's logger.
 */
public class LogUtil {
    private final FoundryConfig config;

    /**
     * Constructor for the LogUtil class.
     * @param config - Reference to the main Essence class.
     */
    public LogUtil(FoundryConfig config) {
        this.config = config;
    }

    /**
     * Logs a message to the server console as informational (standard).
     * @param message String - Message to log.
     */
    public void info(String message) {
        Bukkit.getLogger().info("[" + this.config.pluginId.toUpperCase() + "] " + message);
    }

    /**
     * Logs a message to the server console as warning (non-fatal error).
     * @param message String - Message to log.
     */
    public void warn(String message) {
        Bukkit.getLogger().warning("[" + this.config.pluginId.toUpperCase() + "] " + message);
    }

    /**
     * Logs a message to the server console as severe (fatal error).
     * @param message String - Message to log.
     */
    public void severe(String message) {
        Bukkit.getLogger().severe("[" + this.config.pluginId.toUpperCase() + "] " + message);
    }

    /**
     * Logs a message to the console, informing the console operator that it cannot run the command requested.
     */
    public boolean noConsole() {
        this.warn("Sorry, you need to be an in-game player to use this command.");
        return true;
    }
}
