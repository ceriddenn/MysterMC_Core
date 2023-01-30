package com.mystermc.mystercore.commands.subcommands;

import com.mystermc.mystercore.commands.CommandInterface;
import com.mystermc.mystercore.main.Core;
import com.mystermc.mystercore.main.MysterCore;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Test implements CommandInterface {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd,
                             String commandLabel, String[] args) {
        Player p = (Player) sender;
        boolean value = false;
        if (args.length == 2) {
            if (args[1].equalsIgnoreCase("false")) {
                value = false;
            } else {
                value = true;
            }
            MysterCore.spawnPreference.put(p.getUniqueId(), value);
            p.sendMessage(Core.getPrefix() + Core.color("&aSet your auto tp to " + value));
            System.out.println(MysterCore.spawnPreference);
        } else {
            p.sendMessage(Core.getPrefix() + Core.color("&aUSAGE: &b/core test <value>"));
        }
        return false;
    }
}
