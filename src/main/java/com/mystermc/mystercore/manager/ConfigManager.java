package com.mystermc.mystercore.manager;

import com.mystermc.mystercore.main.MysterCore;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

public class ConfigManager {
    public static String prefix;
    public static File configFile;

    public static FileConfiguration config;

    public static void setupConfig(MysterCore instance) {
        configFile = new File(instance.getDataFolder(), "config.yml");
        if (!(configFile.exists())) {
            instance.saveDefaultConfig();
        }
        config = instance.getConfig();
        prefix = config.getString("prefix");
    }

    // config methods here;
    // getters
    public static String getPrefix() {
        return prefix;
    }

    public static boolean doesUserExistInConfig(UUID uuid) {
        for (String itemUuid : config.getConfigurationSection("users").getKeys(false)) {
            if (itemUuid.toString() == uuid.toString()) {
                return true;
            }
        }
        return false;
    }

    public static void savePreferences() {
        for (Map.Entry<UUID, Boolean> element : MysterCore.spawnPreferance.entrySet()) {
            if (doesUserExistInConfig(element.getKey())) {
                config.getConfigurationSection("users." + element.getKey()).set("joinSpawnTp", element.getValue());
            } else {
                config.createSection("users." + element.getKey());
                config.getConfigurationSection("users." + element.getKey()).set("joinSpawnTp", element.getValue());
            }
        }
        try {
            config.save(configFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void populatePreferences() {
        for (String uuid : config.getConfigurationSection("users").getKeys(false)) {
            boolean preference = config.getBoolean("users." + uuid + ".joinSpawnTp");
            MysterCore.spawnPreferance.put(UUID.fromString(uuid), preference);
        }
    }

    public static Location getSpawnLocation() {
        return new Location(
                Bukkit.getWorld(config.getString("spawnLocation.world")),
                config.getDouble("spawnLocation.x"),
                config.getDouble("spawnLocation.y"),
                config.getDouble("spawnLocation.z"),
                (float) config.getDouble("spawnLocation.yaw"),
                (float) config.getDouble("spawnLocation.pitch")
                );
    }

    public static boolean isSpawnModuleEnabled() {
        return config.getBoolean("spawnModule");
    }

    //setters

    public static void updatePrefix(String str) {
        config.set("prefix", str + " ");
        try {
            config.save(configFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        MysterCore.reload();
    }
    public static void updateSpawnLocation(Location location) {
        double x = location.getX();
        double y = location.getY();
        double z = location.getZ();
        float yaw = location.getYaw();
        float pitch = location.getPitch();
        World world = location.getWorld();
        config.set("spawnLocation.x", x);
        config.set("spawnLocation.y", y);
        config.set("spawnLocation.z", z);
        config.set("spawnLocation.yaw", yaw);
        config.set("spawnLocation.pitch", pitch);
        config.set("spawnLocation.world", world.getName());
        try {
            config.save(configFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        MysterCore.reload();
    }

    public static void toggleSpawnModule(boolean value) {
        config.set("spawnModule", value);
        try {
            config.save(configFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        MysterCore.reload();
    }
}
