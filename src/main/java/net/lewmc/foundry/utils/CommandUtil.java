package net.lewmc.foundry.utils;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.File;

/**
 * The command utility.
 */
public class CommandUtil {
    /**
     * Checks if the server is compatible with PaperMC.
     * @return boolean - If the server is paper compatible.
     */
    public boolean isPaperCompatible() {
        File pwd = new File("config/paper-world-defaults.yml");
        File g = new File("config/paper-global.yml");

        return pwd.exists() && g.exists();
    }

    /**
     * Checks if the commandSender is the console.
     * @param commandSender CommandSender
     * @return true if the command sender is the console.
     */
    public boolean console(CommandSender commandSender) {
        return !(commandSender instanceof Player);
    }
}
