package com.mystermc.mystercore.commands.subcommands;

import com.mystermc.mystercore.commands.CommandInterface;
import com.mystermc.mystercore.main.Core;
import com.mystermc.mystercore.main.MysterCore;
import com.mystermc.mystercore.manager.ConfigManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Reload implements CommandInterface {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd,
                             String commandLabel, String[] args) {
        Player p = (Player) sender;
        if (args.length == 1) {
            p.sendMessage(Core.getPrefix() + Core.color("&aReloading the core modules..."));
            MysterCore.reload();
            p.sendMessage(Core.getPrefix() + Core.color("&c&nReloaded the core modules!"));
        } else {
            p.sendMessage(Core.getPrefix() + Core.color("&aUSAGE: &b/core reload"));
        }
        return false;
    }
}
