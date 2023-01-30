package com.mystermc.mystercore.commands.subcommands;

import com.mystermc.mystercore.main.Core;
import com.mystermc.mystercore.main.MysterCore;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Ps implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd,
                             String commandLabel, String[] args) {
        Player p = (Player) sender;
        if (args.length == 2) {
            if (args[0].equalsIgnoreCase("autotp")) {
                if (args[1].equalsIgnoreCase("on") || args[1].equalsIgnoreCase("off")) {
                    MysterCore.spawnPreference.put(p.getUniqueId(), !args[1].equalsIgnoreCase("off"));
                    p.sendMessage(Core.getPrefix() + Core.color("&aSet your auto tp to " + (args[1].equalsIgnoreCase("off") ? "off" : "on")));

                } else {
                    p.sendMessage(Core.getPrefix() + Core.color("&aUSAGE: &b/ps <autotp/ex/ex> <on:off/ex/ex>"));
                    return false;
                }
            } else {
                p.sendMessage(Core.getPrefix() + Core.color("&aUSAGE: &b/ps <autotp/ex/ex> <on:off/ex/ex>"));
                return false;
            }
        } else {
            p.sendMessage(Core.getPrefix() + Core.color("&aUSAGE: &b/ps <autotp/ex/ex> <on:off/ex/ex>"));
        }
        return false;
    }
}
