package net.lewmc.foundry.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * Foundry's command class.
 */
public abstract class FoundryCommand implements CommandExecutor {

    /**
     * Stores the permission that is required.
     * @return String - The permission string.
     */
    protected abstract String requiredPermission(); // The permission required for this command

    /**
     * Runs the command.
     * @param sender CommandSender - The sender of the command.
     * @param command Command - The command.
     * @param label String - Label for the command.
     * @param args String[] - Arguments passed to the command.
     * @return boolean - If the command was successful.
     */
    protected abstract boolean onRun(CommandSender sender, Command command, String label, String[] args);

    /**
     * Checks if the CommandSender has the required permission.
     * @param sender CommandSender - The sender of the command.
     * @param permission String - The permission string.
     * @return boolean - If the CommandSender has the required permission.
     */
    protected boolean hasPermission(CommandSender sender, String permission) {
        if (permission == null) {
            return true;
        }

        return sender.hasPermission(permission);
    }

    /**
     * Runs the command.
     * @param cs        CommandSender - The sender of the command.
     * @param command   Command - The command.
     * @param label     String - Label for the command.
     * @param args      String[] - Arguments passed to the command.
     * @return boolean - If the command was successful.
     */
    @Override
    public boolean onCommand(CommandSender cs, Command command, String label, String[] args) {
        if (hasPermission(cs, requiredPermission())) {
            return onRun(cs, command, label, args);
        } else {
            cs.sendMessage(ChatColor.RED + "You do not have permission to use this command.");
            return true;
        }
    }
}
