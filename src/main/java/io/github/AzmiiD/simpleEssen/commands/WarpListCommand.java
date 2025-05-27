package io.github.AzmiiD.simpleEssen.commands;

import io.github.AzmiiD.simpleEssen.SimpleEssen;
import io.github.AzmiiD.simpleEssen.data.WarpData;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import java.util.Set;

public class WarpListCommand implements CommandExecutor {

    private final SimpleEssen plugin;
    private final WarpData warpData;

    public WarpListCommand(SimpleEssen plugin, WarpData warpData) {
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
        Set<String> warps = warpData.getWarpList();

        if (warps.isEmpty()) {
            player.sendMessage(ChatColor.YELLOW + "Belum ada warp yang dibuat.");
        } else {
            player.sendMessage(ChatColor.YELLOW + "Daftar Warp:");
            for (String warpName : warps) {
                player.sendMessage(ChatColor.GRAY + "- " + warpName);
            }
        }
        return true;
    }
}