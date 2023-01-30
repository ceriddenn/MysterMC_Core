package com.mystermc.mystercore.commands.subcommands;

import com.mystermc.mystercore.commands.CommandInterface;
import com.mystermc.mystercore.main.Core;
import com.mystermc.mystercore.main.MysterCore;
import com.mystermc.mystercore.manager.ConfigManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Spawn implements CommandInterface {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd,
                             String commandLabel, String[] args) {
        Player p = (Player) sender;
        if (args.length == 1) {
            if (ConfigManager.isSpawnModuleEnabled()) {
                p.sendMessage(Core.getPrefix() + Core.color("&aTeleporting you to the spawn!"));
                p.teleport(ConfigManager.getSpawnLocation());
            } else {
                p.sendMessage(Core.getPrefix() + Core.color("&c&nThe spawn module is disabled! &aYou can't run this command" +
                        " due to the spawn module being disabled, possibly due to maintenance. &ePlease try again later"));
            }
            return true;
        }
        if (args.length == 2) {
            if (!(args[1].equalsIgnoreCase("set") || !(args[1].equalsIgnoreCase("disable")) || !(args[1].equalsIgnoreCase("enable")))) {
                return false;
            }
            if (args.length == 2 && args[1].equalsIgnoreCase("set")) {
                ConfigManager.updateSpawnLocation(p.getLocation());
                p.sendMessage(Core.getPrefix() + Core.color("&aUpdated the spawn location!"));
                return true;
            } else if (args[1].equalsIgnoreCase("disable")){
                ConfigManager.toggleSpawnModule(false);
                p.sendMessage(Core.getPrefix() + Core.color("&c&nToggled the spawn module off. " +
                        "&r&aPlayer's won't be able to teleport to the spawn on join or with the command"));
                return true;
            } else if (args[1].equalsIgnoreCase("enable")) {
                ConfigManager.toggleSpawnModule(true);
                p.sendMessage(Core.getPrefix() + Core.color("&aToggled the spawn module on. " +
                        "Player's will now be able to teleport to the spawn on join and with the command"));
                return true;
            } else {
                return false;
            }
        }
        p.sendMessage(Core.getPrefix() + Core.color("&aUSAGE: &b/core spawn OPTIONAL:<set/disable/enable>"));
        return false;
    }
}
