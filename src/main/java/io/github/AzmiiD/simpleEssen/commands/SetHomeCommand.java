package io.github.AzmiiD.simpleEssen.commands;

import io.github.AzmiiD.simpleEssen.SimpleEssen;
import io.github.AzmiiD.simpleEssen.data.HomeData;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetHomeCommand implements CommandExecutor {

    private final SimpleEssen plugin;
    private final HomeData homeData;

    public SetHomeCommand(SimpleEssen plugin, HomeData homeData) {
        this.plugin = plugin;
        this.homeData = homeData;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Hanya pemain yang dapat menggunakan perintah ini.");
            return true;
        }

        Player player = (Player) sender;
        homeData.setHome(player.getUniqueId(), player.getLocation());
        player.sendMessage(ChatColor.GREEN + "Lokasi home Anda telah diatur!");
        return true;
    }
}