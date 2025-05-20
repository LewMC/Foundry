package net.lewmc.foundry.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;

/**
 * A command that can only be executed by the console.
 */
public abstract class FoundryConsoleCommand extends FoundryCommand {

    /**
     * Runs the command with a check to ensure that the executor is a console entity.
     * @param cs        CommandSender - The sender of the command.
     * @param command   Command - The command.
     * @param label     String - Label for the command.
     * @param args      String[] - Arguments passed to the command.
     * @return boolean - If the command was successful.
     */
    @Override
    public boolean onCommand(CommandSender cs, Command command, String label, String[] args) {
        if (!(cs instanceof ConsoleCommandSender c)) {
            cs.sendMessage(ChatColor.RED + "This command can only be run from the console.");
            return true;
        } else {
            return super.onCommand(c, command, label, args);
        }
    }
}
