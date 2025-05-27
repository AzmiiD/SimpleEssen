package io.github.AzmiiD.simpleEssen.commands;

import io.github.AzmiiD.simpleEssen.SimpleEssen;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.Arrays;

public class BaltopCommand implements CommandExecutor {

    private final SimpleEssen plugin;
    private final Economy economy;

    public BaltopCommand(SimpleEssen plugin, Economy economy) {
        this.plugin = plugin;
        this.economy = economy;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (economy == null) {
            sender.sendMessage(ChatColor.RED + "Fitur ekonomi tidak aktif. Pastikan Vault terpasang.");
            return true;
        }

        sender.sendMessage(ChatColor.YELLOW + "----- Top Saldo -----");

        // Get all known offline players (includes online players)
        OfflinePlayer[] offlinePlayers = Bukkit.getOfflinePlayers();
        Map<String, Double> balances = new LinkedHashMap<>();

        for (OfflinePlayer player : offlinePlayers) {
            balances.put(player.getName(), economy.getBalance(player));
        }

        // Sort the map by value (balance) in descending order
        Map<String, Double> sortedBalances = balances.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(10) // Limit to top 10
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

        int rank = 1;
        for (Map.Entry<String, Double> entry : sortedBalances.entrySet()) {
            sender.sendMessage(ChatColor.GRAY + "#" + rank + " " + ChatColor.GOLD + entry.getKey() + ChatColor.WHITE + ": " + ChatColor.GREEN + economy.format(entry.getValue()));
            rank++;
        }

        return true;
    }
}