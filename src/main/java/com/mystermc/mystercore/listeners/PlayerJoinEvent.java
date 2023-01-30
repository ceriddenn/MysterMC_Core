package com.mystermc.mystercore.listeners;

import com.mystermc.mystercore.main.Core;
import com.mystermc.mystercore.main.MysterCore;
import com.mystermc.mystercore.manager.ConfigManager;
import com.mystermc.mystercore.utils.Utils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PlayerJoinEvent implements Listener {

    @EventHandler
    public void playerJoin(org.bukkit.event.player.PlayerJoinEvent e) {
        Player p = e.getPlayer();
        // tp to spawn logic and decisions
        boolean isSpawnModuleDisabled = ConfigManager.isSpawnModuleEnabled();
        if (isSpawnModuleDisabled) {
            // check is players setting is enabled or not
            if (MysterCore.spawnPreference.containsKey(p.getUniqueId())) {
                if (MysterCore.spawnPreference.get(p.getUniqueId())) {
                    p.teleport(ConfigManager.getSpawnLocation());
                } else {
                    p.sendMessage(Core.getPrefix() + Core.color("&aYou were not teleported to the spawn because you have auto tp toggled off."));
                }
            } else {
                // add user to hashmap
                MysterCore.spawnPreference.put(p.getUniqueId(), true);
                return;
            }
        }
        // join messages
        boolean isNotNewPlayer = p.hasPlayedBefore();
        if (!(isNotNewPlayer)) {
            p.sendMessage(Core.getPrefix() + Core.color("&aWelcome to MysterMC, we hope you enjoy your stay!"));
        } else {
            p.sendMessage(Core.getPrefix() + Core.color("&aWelcome back, " + "&b" + p.getName() + "&a!"));
        }
        e.setJoinMessage(null);
        Utils.playSound(p);
    }
}
