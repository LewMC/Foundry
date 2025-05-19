package net.lewmc.foundry;

import org.bukkit.plugin.Plugin;

public class FoundryConfig {
    /**
     * Should Foundry output verbose data?
     */
    public boolean verbose;

    /**
     * The plugin's ID
     */
    public String pluginId;

    /**
     * Reference to the parent plugin.
     */
    public Plugin plugin;

    /**
     * Sets up the FoundryConfig class.
     * @param plugin Plugin - The parent plugin.
     * @param pluginId String - The plugin ID.
     */
    public FoundryConfig(Plugin plugin, String pluginId) {
        this.pluginId = pluginId.toLowerCase();
    }
}
