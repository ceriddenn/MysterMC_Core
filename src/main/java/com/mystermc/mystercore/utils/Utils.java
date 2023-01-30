package com.mystermc.mystercore.utils;

import com.mystermc.mystercore.main.Core;
import com.mystermc.mystercore.main.MysterCore;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

public class Utils {

    private static MysterCore instance;

    public Utils(MysterCore instance) {
        Utils.instance = instance;
    }

    public static void playSound(Player player) {
        player.playSound(player.getLocation(), Sound.ORB_PICKUP, 500.0f, 1.0f);
    }

    public static void sendPlayerToServer(Player player, String server) {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(b);

        try {
            out.writeUTF("Connect");
            out.writeUTF(server);
        } catch (Exception e) {
            player.sendMessage(Core.getPrefix() + Core.color("&cThere was a problem connecting you to " + server + "! &a Please" +
                    "contact a staff member if this issue still persists."));
            return;
        }

        player.sendPluginMessage(instance, "BungeeCord", b.toByteArray());
    }

}
