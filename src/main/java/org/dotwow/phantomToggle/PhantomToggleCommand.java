package org.dotwow.phantomToggle;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PhantomToggleCommand implements CommandExecutor {

    private final PhantomManager phantomManager;

    public PhantomToggleCommand(PhantomManager phantomManager) {
        this.phantomManager = phantomManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            phantomManager.togglePhantomSpawning(player);
            return true;
        } else {
            sender.sendMessage("This command can only be executed by a player.");
            return false;
        }
    }
}
