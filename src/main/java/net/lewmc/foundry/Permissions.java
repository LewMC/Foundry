package net.lewmc.foundry;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Permission Handler.
 */
public class Permissions {

    private final CommandSender cs;

    /**
     * Constructor.
     * @param cs CommandSender - The user who executed the command.
     */
    public Permissions(CommandSender cs) {
        this.cs = cs;
    }

    /**
     * Checks if the user has a specific permission.
     * @param node String - the permission node to check.
     * @return boolean - If the user has a permission (true/false)
     */
    public boolean has(String node) {
        if (this.cs instanceof Player p) {
            return p.hasPermission(node);
        } else {
            return true;
        }
    }
}