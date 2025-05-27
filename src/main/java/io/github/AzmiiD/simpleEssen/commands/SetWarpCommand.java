package io.github.AzmiiD.simpleEssen.commands;

import io.github.AzmiiD.simpleEssen.SimpleEssen;
import io.github.AzmiiD.simpleEssen.data.WarpData;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetWarpCommand implements CommandExecutor {

    private final SimpleEssen plugin;
    private final WarpData warpData;

    public SetWarpCommand(SimpleEssen plugin, WarpData warpData) {
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

        if (args.length != 1) {
            player.sendMessage(ChatColor.YELLOW + "Penggunaan: /setwarp <nama>");
            return true;
        }

        String warpName = args[0].toLowerCase();
        warpData.setWarp(warpName, player.getLocation());
        player.sendMessage(ChatColor.GREEN + "Warp '" + warpName + "' telah dibuat!");
        return true;
    }
}