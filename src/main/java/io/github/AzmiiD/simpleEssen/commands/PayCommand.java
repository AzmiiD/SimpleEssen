package io.github.AzmiiD.simpleEssen.commands;

import io.github.AzmiiD.simpleEssen.SimpleEssen;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.ChatColor;

public class PayCommand implements CommandExecutor {

    private final SimpleEssen plugin;
    private final Economy economy;

    public PayCommand(SimpleEssen plugin, Economy economy) {
        this.plugin = plugin;
        this.economy = economy;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Hanya pemain yang dapat menggunakan perintah ini.");
            return true;
        }

        Player payer = (Player) sender;

        if (economy == null) {
            payer.sendMessage(ChatColor.RED + "Fitur ekonomi tidak aktif. Pastikan Vault terpasang.");
            return true;
        }

        if (args.length != 2) {
            payer.sendMessage(ChatColor.YELLOW + "Penggunaan: /pay <nama_pemain> <jumlah>");
            return true;
        }

        Player recipient = plugin.getServer().getPlayer(args[0]);
        if (recipient == null || !recipient.isOnline()) {
            payer.sendMessage(ChatColor.RED + "Pemain '" + args[0] + "' tidak ditemukan atau sedang offline.");
            return true;
        }

        double amount;
        try {
            amount = Double.parseDouble(args[1]);
            if (amount <= 0) {
                payer.sendMessage(ChatColor.RED + "Jumlah pembayaran harus lebih dari nol.");
                return true;
            }
        } catch (NumberFormatException e) {
            payer.sendMessage(ChatColor.RED + "'" + args[1] + "' bukan angka yang valid.");
            return true;
        }

        if (economy.getBalance(payer) < amount) {
            payer.sendMessage(ChatColor.RED + "Saldo Anda tidak mencukupi.");
            return true;
        }

        economy.withdrawPlayer(payer, amount);
        economy.depositPlayer(recipient, amount);

        payer.sendMessage(ChatColor.GREEN + "Anda telah membayar " + ChatColor.YELLOW + economy.format(amount) + ChatColor.GREEN + " kepada " + ChatColor.GOLD + recipient.getName() + ChatColor.GREEN + ".");
        recipient.sendMessage(ChatColor.GOLD + payer.getName() + ChatColor.GREEN + " telah membayar Anda " + ChatColor.YELLOW + economy.format(amount) + ChatColor.GREEN + ".");

        return true;
    }
}