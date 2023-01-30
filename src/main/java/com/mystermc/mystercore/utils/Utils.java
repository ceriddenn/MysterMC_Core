package com.mystermc.mystercore.utils;

import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class Utils {

    public static void playSound(Player player) {
        player.playSound(player.getLocation(), Sound.ORB_PICKUP, 500.0f, 1.0f);
    }

}
