package net.lewmc.foundry.command;

import net.lewmc.foundry.FoundryConfig;
import net.lewmc.foundry.Logger;
import org.bukkit.command.CommandExecutor;
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

    /**
     * Registers a command with the server, handling any potential issues. Do not use this for aliases, only use it when multiple commands use the same executor.
     * @param commands String[] - The command labels, what users type into chat after the / to execute it.
     * @param executor CommandExecutor - The command executor class.
     * @return boolean[] - Was the command successfully registered? Returns an array.
     */
    public boolean[] register(String[] commands, CommandExecutor executor) {
        boolean[] success = new boolean[commands.length];
        int i = 0;

        for (String command : commands) {
            if (this.plugin.getCommand(command) != null) {
                Objects.requireNonNull(this.plugin.getCommand(command)).setExecutor(executor);
                success[i] = true;
            } else {
                new Logger(this.config).severe("Failed to register command " + command + " because it is null.");
                success[i] = false;
            }
            i++;
        }
        return success;
    }
}
