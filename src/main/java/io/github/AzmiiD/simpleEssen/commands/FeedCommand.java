package io.github.AzmiiD.simpleEssen.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FeedCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Hanya pemain yang dapat menggunakan perintah ini.");
            return true;
        }

        Player player = (Player) sender;
        player.setFoodLevel(20);
        player.setSaturation(20);
        player.sendMessage(ChatColor.GREEN + "Perut Anda sudah kenyang!");
        return true;
    }
}