package net.lewmc.foundry;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * The configuration class.
 */
public class FoundryConfig {
    /**
     * The plugin's ID
     */
    public String pluginId;

    /**
     * Reference to the parent plugin.
     */
    public JavaPlugin plugin;

    /**
     * Should Foundry be verbose?
     */
    public boolean verbose = false;

    /**
     * Sets up the FoundryConfig class.
     * @param plugin Plugin - The parent plugin.
     */
    public FoundryConfig(JavaPlugin plugin) {
        this.plugin = plugin;
        this.pluginId = plugin.getName().toLowerCase();
    }

    /**
     * Sets if Foundry should be verbose.
     * @param verbose boolean - true/false
     */
    public void setVerbose(boolean verbose) {
        this.verbose = verbose;
    }
}
