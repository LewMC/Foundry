package net.lewmc.foundry.command;

import net.lewmc.foundry.FoundryConfig;
import net.lewmc.foundry.Logger;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

/**
 * Command Registry
 */
public class CommandRegistry {

    /**
     * Reference to the main plugin class.
     */
    private final JavaPlugin plugin;

    /**
     * Foundry's configuration.
     */
    private final FoundryConfig config;

    /**
     * Constructor for the class.
     * @param config FoundryConfig - Foundry's configuration.
     * @param plugin Plugin - Reference to the main plugin class.
     */
    public CommandRegistry(FoundryConfig config, JavaPlugin plugin) {
        this.config = config;
        this.plugin = plugin;
    }

    /**
     * Registers a command with the server, handling any potential issues.
     * @param command String - The command label, what users type into chat after the / to execute it.
     * @param executor CommandExecutor - The command executor class.
     * @return boolean - Was the command successfully registered?
     */
    public boolean register(String command, CommandExecutor executor) {
        if (this.plugin.getCommand(command) != null) {
            Objects.requireNonNull(this.plugin.getCommand(command)).setExecutor(executor);
            return true;
        } else {
            new Logger(this.config).severe("Failed to register command " + command + " because it is null.");
            return false;
        }
    }
}
