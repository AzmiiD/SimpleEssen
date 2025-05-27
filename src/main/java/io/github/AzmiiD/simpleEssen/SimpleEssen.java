package io.github.AzmiiD.simpleEssen;

import io.github.AzmiiD.simpleEssen.commands.*;
import io.github.AzmiiD.simpleEssen.data.HomeData;
import io.github.AzmiiD.simpleEssen.data.WarpData;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import net.milkbowl.vault.economy.Economy;

public final class SimpleEssen extends JavaPlugin {

    private WarpData warpData;
    private HomeData homeData;
    private Economy economy = null;

    @Override
    public void onEnable() {
        // Inisialisasi Data
        warpData = new WarpData(this);
        homeData = new HomeData(this);

        // Setup Vault Economy
        if (!setupEconomy()) {
            getLogger().severe("Vault tidak ditemukan! Fitur ekonomi tidak akan aktif.");
        }

        // Registrasi Command
        getCommand("tpa").setExecutor(new TPACommand(this));
        getCommand("tpaccept").setExecutor(new TPAcceptCommand(this));
        getCommand("tpdeny").setExecutor(new TPADenyCommand(this));
        getCommand("setwarp").setExecutor(new SetWarpCommand(this, warpData));
        getCommand("warp").setExecutor(new WarpCommand(this, warpData));
        getCommand("delwarp").setExecutor(new WarpCommand(this, warpData));
        getCommand("warplist").setExecutor(new WarpListCommand(this, warpData));
        getCommand("pay").setExecutor(new PayCommand(this, economy));
        getCommand("feed").setExecutor(new FeedCommand());
        getCommand("heal").setExecutor(new HealCommand());
        getCommand("sethome").setExecutor(new SetHomeCommand(this, homeData));
        getCommand("home").setExecutor(new HomeCommand(this, homeData));
        getCommand("baltop").setExecutor(new BaltopCommand(this, economy));

        getLogger().info("SimpleEssen v" + getDescription().getVersion() + " telah diaktifkan!");
    }

    @Override
    public void onDisable() {
        getLogger().info("SimpleEssen telah dinonaktifkan!");
        warpData.saveWarps();
        homeData.saveHomes();
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        economy = rsp.getProvider();
        return economy != null;
    }

    public Economy getEconomy() {
        return economy;
    }
}