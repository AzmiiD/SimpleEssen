package io.github.AzmiiD.simpleEssen.data;

import io.github.AzmiiD.simpleEssen.SimpleEssen;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class HomeData {

    private final SimpleEssen plugin;
    private File homeFile;
    private FileConfiguration homes;
    private final Map<UUID, Location> cachedHomes = new HashMap<>();

    public HomeData(SimpleEssen plugin) {
        this.plugin = plugin;
        createHomeFile();
        loadHomes();
    }

    private void createHomeFile() {
        homeFile = new File(plugin.getDataFolder(), "homes.yml");
        if (!homeFile.exists()) {
            homeFile.getParentFile().mkdirs();
            try {
                homeFile.createNewFile();
            } catch (IOException e) {
                plugin.getLogger().severe("Gagal membuat file homes.yml!");
            }
        }
    }

    private void loadHomes() {
        homes = YamlConfiguration.loadConfiguration(homeFile);
        for (String key : homes.getKeys(false)) {
            try {
                UUID uuid = UUID.fromString(key);
                cachedHomes.put(uuid, homes.getLocation(key));
            } catch (IllegalArgumentException e) {
                plugin.getLogger().warning("Entri home tidak valid: " + key);
            }
        }
    }

    public void saveHomes() {
        for (Map.Entry<UUID, Location> entry : cachedHomes.entrySet()) {
            homes.set(entry.getKey().toString(), entry.getValue());
        }
        try {
            homes.save(homeFile);
        } catch (IOException e) {
            plugin.getLogger().severe("Gagal menyimpan file homes.yml!");
        }
    }

    public Location getHome(UUID playerId) {
        return cachedHomes.get(playerId);
    }

    public void setHome(UUID playerId, Location location) {
        cachedHomes.put(playerId, location);
    }

    public void deleteHome(UUID playerId) {
        cachedHomes.remove(playerId);
        homes.set(playerId.toString(), null);
        saveHomes();
    }
}