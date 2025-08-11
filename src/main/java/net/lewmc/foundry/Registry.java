package net.lewmc.foundry;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.TabCompleter;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.Objects;

/**
 * Command Registry
 */
public class Registry {

    /**
     * Reference to the main plugin class.
     */
    private final JavaPlugin plugin;

    /**
     * Foundry's configuration.
     */
    private final FoundryConfig config;

    /**
     * Link to the IBukkit class which handles some Bukkit internal processes.
     */
    private final IBukkit ib;

    /**
     * Constructor for the class.
     * @param config FoundryConfig - Foundry's configuration.
     * @param plugin Plugin - Reference to the main plugin class.
     */
    public Registry(FoundryConfig config, JavaPlugin plugin) {
        this.config = config;
        this.plugin = plugin;
        this.ib = new IBukkit(config, plugin);
    }

    /**
     * Registers a command with the server that is already set up in the plugin.yml file, handling any potential issues.
     * @param command String - The command label, what users type into chat after the / to execute it.
     * @param executor CommandExecutor - The command executor class.
     * @return boolean - Was the command successfully registered?
     */
    public boolean command(String command, CommandExecutor executor) {
        return this.ymlCommand(command, executor);
    }

    /**
     * Registers a command with the server that is already set up in the plugin.yml file, handling any potential issues. Do not use this for aliases, only use it when multiple commands use the same executor.
     * @param commands String[] - The command labels, what users type into chat after the / to execute it.
     * @param executor CommandExecutor - The command executor class.
     * @return boolean[] - Was the command successfully registered? Returns an array.
     */
    public boolean[] command(String[] commands, CommandExecutor executor) {
        return this.ymlCommand(commands, executor);
    }

    /**
     * Registers a command with the server that is already set up in the plugin.yml file, handling any potential issues.
     * @param command String - The command label, what users type into chat after the / to execute it.
     * @param executor CommandExecutor - The command executor class.
     * @return boolean - Was the command successfully registered?
     */
    public boolean ymlCommand(String command, CommandExecutor executor) {
        if (this.plugin.getCommand(command) != null) {
            Objects.requireNonNull(this.plugin.getCommand(command)).setExecutor(executor);
            this.verboseAlert("ymlCommand", command);
            return true;
        } else {
            new Logger(this.config).severe("Failed to register ymlCommand " + command + " because it is null.");
            return false;
        }
    }

    /**
     * Registers a command with the server that is already set up in the plugin.yml file, handling any potential issues. Do not use this for aliases, only use it when multiple commands use the same executor.
     * @param commands String[] - The command labels, what users type into chat after the / to execute it.
     * @param executor CommandExecutor - The command executor class.
     * @return boolean[] - Was the command successfully registered? Returns an array.
     */
    public boolean[] ymlCommand(String[] commands, CommandExecutor executor) {
        boolean[] success = new boolean[commands.length];
        int i = 0;

        for (String command : commands) {
            if (this.plugin.getCommand(command) != null) {
                Objects.requireNonNull(this.plugin.getCommand(command)).setExecutor(executor);
                this.verboseAlert("ymlCommand", command);
                success[i] = true;
            } else {
                new Logger(this.config).severe("Failed to register ymlCommand " + command + " because it is null.");
                success[i] = false;
            }
            i++;
        }
        return success;
    }

    /**
     * Registers a command with the server that is NOT set up in the plugin.yml file, handling any potential issues.
     * @param command String - The command label, what users type into chat after the / to execute it.
     * @param executor CommandExecutor - The command executor class.
     * @return boolean - Was the command successfully registered?
     */
    public boolean runtimeCommand(String command, CommandExecutor executor, String... aliases) {
        if (command != null && executor != null) {
            PluginCommand rCmd = ib.constructRuntimeCommand(aliases[0]);
            rCmd.setAliases(Arrays.asList(aliases));
            ib.getCommandMap().register(this.plugin.getDescription().getName(), rCmd);

            this.verboseAlert("runtimeCommand", command);
            return true;
        } else {
            new Logger(this.config).severe("Failed to register runtimeCommand " + command + " because it is null.");
            return false;
        }
    }

    /**
     * Registers a command with the server that is NOT set up in the plugin.yml file, handling any potential issues. Do not use this for aliases, only use it when multiple commands use the same executor.
     * @param commands String[] - The command labels, what users type into chat after the / to execute it.
     * @param executor CommandExecutor - The command executor class.
     * @return boolean[] - Was the command successfully registered? Returns an array.
     */
    public boolean[] runtimeCommand(String[] commands, CommandExecutor executor, String... aliases) {
        boolean[] success = new boolean[commands.length];
        int i = 0;

        for (String command : commands) {
            if (command != null && executor != null) {
                PluginCommand rCmd = ib.constructRuntimeCommand(aliases[0]);
                rCmd.setAliases(Arrays.asList(aliases));
                ib.getCommandMap().register(this.plugin.getDescription().getName(), rCmd);

                this.verboseAlert("runtimeCommand", command);
                success[i] = true;
            } else {
                new Logger(this.config).severe("Failed to register runtimeCommand " + command + " because it is null.");
                success[i] = false;
            }
            i++;
        }
        return success;
    }

    /**
     * Registers a Tab Completer with the server, handling any potential issues.
     * @param command String - The command label, what users type into chat after the / to execute it.
     * @param completer TabCompleter - The tab completer class.
     * @return boolean - Was the tab completer successfully registered?
     */
    public boolean tabCompleter(String command, TabCompleter completer) {
        if (this.plugin.getCommand(command) != null) {
            Objects.requireNonNull(this.plugin.getCommand(command)).setTabCompleter(completer);
            this.verboseAlert("Tab completer", completer.toString());
            return true;
        } else {
            new Logger(this.config).severe("Failed to register tab completer " + completer.toString() + " because it is null.");
            return false;
        }
    }

    /**
     * Registers a Tab Completer with the server, handling any potential issues.
     * @param commands  String - The command label, what users type into chat after the / to execute it.
     * @param completer TabCompleter - The tab completer class.
     * @return boolean - Was the tab completer successfully registered?
     */
    public boolean[] tabCompleter(String[] commands, TabCompleter completer) {

        boolean[] success = new boolean[commands.length];
        int i = 0;

        for (String command : commands) {
            if (this.plugin.getCommand(command) != null) {
                Objects.requireNonNull(this.plugin.getCommand(command)).setTabCompleter(completer);
                this.verboseAlert("Tab completer", completer.toString());
                success[i] = true;
            } else {
                new Logger(this.config).severe("Failed to register tab completer " + completer.toString() + " because it is null.");
                success[i] = false;
            }
            i++;
        }
        return success;
    }

    /**
     * Registers an event with the server, handling any potential issues.
     * @param event     Listener - The event listener class.
     * @return boolean - Was the event successfully registered?
     */
    public boolean event(Listener event) {
        Bukkit.getServer().getPluginManager().registerEvents(event, this.plugin);
        this.verboseAlert("Event", event.toString());
        return true;
    }

    /**
     * Alerts the verbose logs of a registration.
     * @param type String - The type of registration.
     * @param asset String - The asset registered.
     */
    public void verboseAlert(String type, String asset) {
        if (this.config.verbose) {
            new Logger(this.config).info("FoundryRegistry > "+type+" "+asset+" registered.");
        }
    }
}
