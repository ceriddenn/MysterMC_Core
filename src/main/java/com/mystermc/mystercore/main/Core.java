package com.mystermc.mystercore.main;

import com.mystermc.mystercore.manager.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.UUID;

public class Core {

    private MysterCore instance;

    public Core(MysterCore instance) {
        this.instance = instance;
    }

    public static String color(String str) {
        return ChatColor.translateAlternateColorCodes('&', str);
    }

    public static String uuidToName(UUID uuid) {
        return Bukkit.getOfflinePlayer(uuid).getName();
    }

    public static Player uuidToPlayer(UUID uuid) {
        return Bukkit.getPlayer(uuid);
    }

    public static OfflinePlayer uuidToOfflinePlayer(UUID uuid) {
        return Bukkit.getOfflinePlayer(uuid);
    }

    public static UUID nameTOUuid(String name) {
        return Bukkit.getPlayer(name).getUniqueId();
    }

    public static String getPrefix() {
        return color(ConfigManager.getPrefix());
    }
}
