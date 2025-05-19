package net.lewmc.foundry;

import org.bukkit.plugin.Plugin;

public class FoundryConfig {
    /**
     * The plugin's ID
     */
    public String pluginId;

    /**
     * Reference to the parent plugin.
     */
    public Plugin plugin;

    /**
     * Should Foundry
     */
    public boolean verbose;

    /**
     * Sets up the FoundryConfig class.
     * @param plugin Plugin - The parent plugin.
     */
    public FoundryConfig(Plugin plugin) {
        this.pluginId = plugin.getName().toUpperCase();
    }
}
