package com.mystermc.mystercore.commands.subcommands;

import com.mystermc.mystercore.main.Core;
import com.mystermc.mystercore.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Bedwars implements CommandExecutor {

    private final String bedwarsServerBGID = "bwlobbyone";
    @Override
    public boolean onCommand(CommandSender sender, Command cmd,
                             String commandLabel, String[] args) {
        Player p = (Player) sender;
        if (sender instanceof Player) {
            Utils.sendPlayerToServer(p, bedwarsServerBGID);
            p.sendMessage(Core.getPrefix() + Core.color("&aConnecting to bedwars..."));
        } else {
            sender.sendMessage("This command can only be ran by a player.");
        }
        return false;
    }
}
