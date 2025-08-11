package net.lewmc.foundry;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandMap;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * IBukkit class, handles some internal processes.
 */
public class IBukkit {
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
    public IBukkit(FoundryConfig config, JavaPlugin plugin) {
        this.config = config;
        this.plugin = plugin;
    }

    /**
     * Fetches the Bukkit Command Map
     * @return CommandMap on success, null on fail.
     */
    public CommandMap getCommandMap() {
       try {
           return this.plugin.getServer().getCommandMap();
       } catch (Exception e) {
           new Logger(this.config).severe("Foundry tried to access Bukkit internals (IBukkit.getCommandMap) and died. Threw exception: "+e.getMessage());
           return null;
       }
    }

    /**
     * Constructs a runtime command. Note: If you're registering command on runtime use the Registry function instead.
     * @see Registry#runtimeCommand(String, CommandExecutor, String...)
     * @param name The command's name.
     * @return PluginCommand the constructed command.
     */
    public PluginCommand constructRuntimeCommand(String name) {
        PluginCommand command = null;

        try {
            Constructor<PluginCommand> c = PluginCommand.class.getDeclaredConstructor(String.class, JavaPlugin.class);
            c.setAccessible(true);

            command = c.newInstance(name, plugin);
        } catch (SecurityException | InvocationTargetException | IllegalArgumentException | IllegalAccessException |
                 InstantiationException | NoSuchMethodException e) {
            new Logger(this.config).severe("Foundry tried to access Bukkit internals (IBukkit.constructRuntimeCommand) and died. Threw exception: "+e.getMessage());
        }

        return command;
    }

    /**
     * Fetches the Bukkit Plugin Manager
     * @return PluginManager on success, null on fail.
     */
    public PluginManager getPluginManager() {
       try {
           return this.plugin.getServer().getPluginManager();
       } catch (Exception e) {
           new Logger(this.config).severe("Foundry tried to access Bukkit internals (IBukkit.getPluginManager) and died. Threw exception: "+e.getMessage());
           return null;
       }
    }
}
