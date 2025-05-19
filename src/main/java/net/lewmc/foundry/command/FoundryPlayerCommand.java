package net.lewmc.foundry.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * A command that can only be executed by the console.
 */
public abstract class FoundryPlayerCommand extends FoundryCommand {

    /**
     * Runs the command with a check to ensure that the executor is a player entity.
     * @param sender CommandSender - The sender of the command.
     * @param command Command - The command.
     * @param label String - Label for the command.
     * @param args String[] - Arguments passed to the command.
     * @return boolean - If the command was successful.
     */
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "This command can only be run by players in-game.");
            return true;
        }
        return super.onCommand(sender, command, label, args);
    }
}
