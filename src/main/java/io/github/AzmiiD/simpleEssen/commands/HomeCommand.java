package io.github.AzmiiD.simpleEssen.commands;

import io.github.AzmiiD.simpleEssen.SimpleEssen;
import io.github.AzmiiD.simpleEssen.data.HomeData;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HomeCommand implements CommandExecutor {

    private final SimpleEssen plugin;
    private final HomeData homeData;

    public HomeCommand(SimpleEssen plugin, HomeData homeData) {
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
        Location homeLocation = homeData.getHome(player.getUniqueId());

        if (homeLocation != null) {
            player.teleport(homeLocation);
            player.sendMessage(ChatColor.GREEN + "Berpindah ke home Anda.");
        } else {
            player.sendMessage(ChatColor.RED + "Anda belum mengatur lokasi home Anda! Gunakan /sethome.");
        }
        return true;
    }
}