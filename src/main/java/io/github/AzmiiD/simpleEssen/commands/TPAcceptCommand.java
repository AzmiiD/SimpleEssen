package io.github.AzmiiD.simpleEssen.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import io.github.AzmiiD.simpleEssen.SimpleEssen;
import java.util.Map;
import java.util.UUID;

public class TPAcceptCommand implements CommandExecutor {

    private final SimpleEssen plugin;

    public TPAcceptCommand(SimpleEssen plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Hanya pemain yang dapat menggunakan perintah ini.");
            return true;
        }

        Player target = (Player) sender;
        Map<UUID, UUID> pendingTeleports = ((TPACommand) plugin.getCommand("tpa").getExecutor()).pendingTeleports;
        UUID requesterUUID = pendingTeleports.get(target.getUniqueId());

        if (requesterUUID == null) {
            target.sendMessage(ChatColor.RED + "Tidak ada permintaan teleportasi tertunda dari pemain mana pun.");
            return true;
        }

        Player requester = plugin.getServer().getPlayer(requesterUUID);
        if (requester == null || !requester.isOnline()) {
            target.sendMessage(ChatColor.RED + "Pemain yang meminta teleportasi tidak lagi online.");
            pendingTeleports.remove(target.getUniqueId());
            return true;
        }

        requester.teleport(target);
        requester.sendMessage(ChatColor.GREEN + "Anda telah diterima oleh " + target.getName() + ".");
        target.sendMessage(ChatColor.GREEN + "Anda telah menerima permintaan teleportasi dari " + requester.getName() + ".");
        pendingTeleports.remove(target.getUniqueId());

        return true;
    }
}