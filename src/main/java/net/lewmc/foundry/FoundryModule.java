package net.lewmc.foundry;

import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

/**
 * Essence Module class
 */
abstract public class FoundryModule {
    /**
     * The plugin
     */
    protected JavaPlugin plugin;

    /**
     * The Foundry Registry
     */
    protected Registry reg;

    /**
     * Executes all other functions.
     * @param plugin    Reference to the main plugin class.
     * @param reg       The Foundry Registry.
     */
    public FoundryModule(@NotNull JavaPlugin plugin, @NotNull Registry reg) {
        this.plugin = plugin;
        this.reg = reg;

        this.registerCommands();
        this.registerEvents();
        this.registerTabCompleters();
    }

    /**
     * Where commands to be registered should be placed.
     */
    public abstract void registerCommands();

    /**
     * Where events to be registered should be placed.
     */
    public abstract void registerEvents();

    /**
     * Where tab completers to be registered should be placed.
     */
    public abstract void registerTabCompleters();
}
