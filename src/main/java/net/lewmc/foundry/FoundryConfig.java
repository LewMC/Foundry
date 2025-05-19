package net.lewmc.foundry;

import org.bukkit.plugin.java.JavaPlugin;

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
     * Should Foundry
     */
    public boolean verbose;

    /**
     * Sets up the FoundryConfig class.
     * @param plugin Plugin - The parent plugin.
     */
    public FoundryConfig(JavaPlugin plugin) {
        this.pluginId = plugin.getName().toLowerCase();
    }
}
