package com.mystermc.mystercore.commands.subcommands;

import com.mystermc.mystercore.commands.CommandInterface;
import com.mystermc.mystercore.main.Core;
import com.mystermc.mystercore.manager.ConfigManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PrefixSet implements CommandInterface {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd,
                             String commandLabel, String[] args) {
        Player p = (Player) sender;
        // cache to show changes
        String cachedPrefix = Core.getPrefix();
            if (args.length == 4) {
                if (!(args[1].equalsIgnoreCase("prefix"))) {
                    return false;
                }
                if (!(args[2].equalsIgnoreCase("set"))) {
                   return false;
                }
                if (args.length == 4) {
                    ConfigManager.updatePrefix(args[3]);
                    p.sendMessage(Core.getPrefix() + Core.color("&aChanged the prefix from " +  cachedPrefix + "&a to &r" + args[3]));
                    return true;
                } else {
                    return false;
                }
            }
           return false;
    }
}
