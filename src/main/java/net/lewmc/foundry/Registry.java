package net.lewmc.foundry;

import org.bukkit.Bukkit;
import org.bukkit.command.*;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.*;

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
    private final Map<String, Command> runtimeCommands = new HashMap<>();
    private final Map<String, TabCompleter> runtimeCompleters = new HashMap<>();

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
     * @param label String - The command label, what users type into chat after the / to execute it.
     * @param executor CommandExecutor - The command executor class.
     * @return boolean - Was the command successfully registered?
     */
    public boolean ymlCommand(String label, CommandExecutor executor) {
        if (this.plugin.getCommand(label) != null) {
            Objects.requireNonNull(this.plugin.getCommand(label)).setExecutor(executor);
            this.verboseAlert("ymlCommand", label);
            return true;
        } else {
            new Logger(this.config).severe("Failed to register ymlCommand " + label + " because it is null.");
            return false;
        }
    }

    /**
     * Registers a command with the server that is already set up in the plugin.yml file, handling any potential issues. Do not use this for aliases, only use it when multiple commands use the same executor.
     * @param labels String[] - The command labels, what users type into chat after the / to execute it.
     * @param executor CommandExecutor - The command executor class.
     * @return boolean[] - Was the command successfully registered? Returns an array.
     */
    public boolean[] ymlCommand(String[] labels, CommandExecutor executor) {
        boolean[] success = new boolean[labels.length];
        int i = 0;

        for (String command : labels) {
            success[i] = this.ymlCommand(command, executor);
            i++;
        }

        return success;
    }

    /**
     * Registers a command with the server that is NOT set up in the plugin.yml file, handling any potential issues.
     * @param label String - The command label, what users type into chat after the / to execute it.
     * @param executor CommandExecutor - The command executor class.
     * @return boolean - Was the command successfully registered?
     */
    public boolean runtimeCommand(String label, CommandExecutor executor, String... aliases) {
        if (label != null && executor != null) {
            Command dynamicCommand = new Command(label) {
                @Override
                public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, String[] args) {
                    return executor.onCommand(sender, this, commandLabel, args);
                }

                @Override
                public @NotNull List<String> tabComplete(@NotNull CommandSender sender, @NotNull String alias, String[] args) {
                    TabCompleter completer = runtimeCompleters.get(getName().toLowerCase());
                    if (completer != null) {
                        return Objects.requireNonNull(completer.onTabComplete(sender, this, alias, args));
                    }
                    return super.tabComplete(sender, alias, args);
                }
            };

            dynamicCommand.setAliases(Arrays.asList(aliases));

            CommandMap map = ib.getCommandMap();
            if (map != null) {
                map.register(plugin.getDescription().getName(), dynamicCommand);
                this.runtimeCommands.put(label.toLowerCase(), dynamicCommand);
                return true;
            }
            return false;
        } else {
            new Logger(this.config).severe("Failed to register runtimeCommand " + label + " because it is null.");
            return false;
        }
    }

    /**
     * Registers a command with the server that is NOT set up in the plugin.yml file, handling any potential issues. Do not use this for aliases, only use it when multiple commands use the same executor.
     * @param labels String[] - The command labels, what users type into chat after the / to execute it.
     * @param executor CommandExecutor - The command executor class.
     * @return boolean[] - Was the command successfully registered? Returns an array.
     */
    public boolean[] runtimeCommand(String[] labels, CommandExecutor executor, String... aliases) {
        boolean[] success = new boolean[labels.length];
        int i = 0;

        for (String command : labels) {
            success[i] = this.runtimeCommand(command, executor, aliases);
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
        // ymlCommand
        Command cmd = this.plugin.getCommand(command);
        if (cmd != null && cmd instanceof PluginCommand) {
            ((PluginCommand) cmd).setTabCompleter(completer);
            this.verboseAlert("Tab completer", completer.toString());
            return true;
        }

        // runtimeCommand
        Command runtimeCmd = runtimeCommands.get(command.toLowerCase());
        if (runtimeCmd != null) {
            runtimeCompleters.put(command.toLowerCase(), completer);
            this.verboseAlert("Runtime tab completer", completer.toString());
            return true;
        }

        // Failure
        new Logger(this.config).severe("Failed to register tab completer " + completer.toString() + " because it is null.");
        return false;
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
            success[i] = this.tabCompleter(command, completer);
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
