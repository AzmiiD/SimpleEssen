package io.github.AzmiiD.simpleEssen.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import io.github.AzmiiD.simpleEssen.SimpleEssen;
import org.bukkit.ChatColor;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TPACommand implements CommandExecutor {

    private final SimpleEssen plugin;
    public static Map<UUID, UUID> pendingTeleports = new HashMap<>();

    public TPACommand(SimpleEssen plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Hanya pemain yang dapat menggunakan perintah ini.");
            return true;
        }

        Player requester = (Player) sender;

        if (args.length != 1) {
            requester.sendMessage(ChatColor.YELLOW + "Penggunaan: /tpa <nama_pemain>");
            return true;
        }

        Player target = plugin.getServer().getPlayer(args[0]);
        if (target == null || !target.isOnline()) {
            requester.sendMessage(ChatColor.RED + "Pemain '" + args[0] + "' tidak ditemukan atau sedang offline.");
            return true;
        }

        if (target.equals(requester)) {
            requester.sendMessage(ChatColor.RED + "Anda tidak dapat mengirim permintaan teleportasi ke diri sendiri.");
            return true;
        }

        pendingTeleports.put(target.getUniqueId(), requester.getUniqueId());
        target.sendMessage(ChatColor.YELLOW + requester.getName() + " ingin teleportasi ke Anda. Ketik " + ChatColor.GREEN + "/tpaccept" + ChatColor.YELLOW + " untuk menerima atau " + ChatColor.RED + "/tpdeny" + ChatColor.YELLOW + " untuk menolak.");
        requester.sendMessage(ChatColor.GREEN + "Permintaan teleportasi telah dikirim ke " + target.getName() + ".");

        return true;
    }
}