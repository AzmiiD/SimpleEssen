package io.github.AzmiiD.simpleEssen.data;

import io.github.AzmiiD.simpleEssen.SimpleEssen;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class WarpData {

    private final SimpleEssen plugin;
    private File warpFile;
    private FileConfiguration warps;
    private final Map<String, Location> cachedWarps = new HashMap<>();

    public WarpData(SimpleEssen plugin) {
        this.plugin = plugin;
        createWarpFile();
        loadWarps();
    }

    private void createWarpFile() {
        warpFile = new File(plugin.getDataFolder(), "warps.yml");
        if (!warpFile.exists()) {
            warpFile.getParentFile().mkdirs();
            try {
                warpFile.createNewFile();
            } catch (IOException e) {
                plugin.getLogger().severe("Gagal membuat file warps.yml!");
            }
        }
    }

    private void loadWarps() {
        warps = YamlConfiguration.loadConfiguration(warpFile);
        cachedWarps.putAll(warps.getValues(false).entrySet().stream()
                .filter(entry -> entry.getValue() instanceof Location)
                .collect(java.util.stream.Collectors.toMap(Map.Entry::getKey, entry -> (Location) entry.getValue())));
    }

    public void saveWarps() {
        for (Map.Entry<String, Location> entry : cachedWarps.entrySet()) {
            warps.set(entry.getKey(), entry.getValue());
        }
        try {
            warps.save(warpFile);
        } catch (IOException e) {
            plugin.getLogger().severe("Gagal menyimpan file warps.yml!");
        }
    }

    public Location getWarp(String warpName) {
        return cachedWarps.get(warpName.toLowerCase());
    }

    public void setWarp(String warpName, Location location) {
        cachedWarps.put(warpName.toLowerCase(), location);
        saveWarps();
    }

    public boolean deleteWarp(String warpName) {
        if (cachedWarps.containsKey(warpName.toLowerCase())) {
            cachedWarps.remove(warpName.toLowerCase());
            warps.set(warpName.toLowerCase(), null);
            saveWarps();
            return true;
        }
        return false;
    }

    public Set<String> getWarpList() {
        return cachedWarps.keySet();
    }
}