package io.github.AzmiiD.simpleEssen.commands;

import io.github.AzmiiD.simpleEssen.SimpleEssen;
import io.github.AzmiiD.simpleEssen.data.WarpData;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WarpCommand implements CommandExecutor {

    private final SimpleEssen plugin;
    private final WarpData warpData;

    public WarpCommand(SimpleEssen plugin, WarpData warpData) {
        this.plugin = plugin;
        this.warpData = warpData;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Hanya pemain yang dapat menggunakan perintah ini.");
            return true;
        }

        Player player = (Player) sender;

        if (command.getName().equalsIgnoreCase("warp")) {
            if (args.length != 1) {
                player.sendMessage(ChatColor.YELLOW + "Penggunaan: /warp <nama>");
                return true;
            }

            String warpName = args[0].toLowerCase();
            Location warpLocation = warpData.getWarp(warpName);
            if (warpLocation != null) {
                player.teleport(warpLocation);
                player.sendMessage(ChatColor.GREEN + "Berpindah ke warp '" + warpName + "'.");
            } else {
                player.sendMessage(ChatColor.RED + "Warp '" + warpName + "' tidak ditemukan!");
            }
            return true;
        } else if (command.getName().equalsIgnoreCase("delwarp")) {
            if (args.length != 1) {
                player.sendMessage(ChatColor.YELLOW + "Penggunaan: /delwarp <nama>");
                return true;
            }

            String warpName = args[0].toLowerCase();
            if (warpData.deleteWarp(warpName)) {
                player.sendMessage(ChatColor.GREEN + "Warp '" + warpName + "' telah dihapus!");
            } else {
                player.sendMessage(ChatColor.RED + "Warp '" + warpName + "' tidak ditemukan!");
            }
            return true;
        }
        return false;
    }
}