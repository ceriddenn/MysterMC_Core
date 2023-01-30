package com.mystermc.mystercore.main;

import com.mystermc.mystercore.commands.BaseCommand;
import com.mystermc.mystercore.commands.CommandHandler;
import com.mystermc.mystercore.commands.subcommands.PrefixSet;
import com.mystermc.mystercore.commands.subcommands.Reload;
import com.mystermc.mystercore.commands.subcommands.Spawn;
import com.mystermc.mystercore.commands.subcommands.Test;
import com.mystermc.mystercore.listeners.PlayerJoinEvent;
import com.mystermc.mystercore.manager.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

public final class MysterCore extends JavaPlugin {
    public static Map<UUID, Boolean> spawnPreference = new HashMap<>();
    private static MysterCore instance;

    @Override
    public void onEnable() {
        getLogger().info("Myster Core enabling.... please wait");
        instance = this;
        ConfigManager.setupConfig(instance);
        ConfigManager.populateSpawnPreferences();
        // give core an instance of main class / register
        new Core(instance);
        // register base systems
        registerEvents();
        registerCommands();
        System.out.println(spawnPreference.get(0));
        getLogger().info("Myster Core enabled!");

    }

    @Override
    public void onDisable() {
        ConfigManager.saveSpawnPreferences();
    }
    public void registerEvents() {
        Bukkit.getPluginManager().registerEvents(new PlayerJoinEvent(), this);
    }

    public void registerCommands() {
        CommandHandler handler = new CommandHandler();

        handler.register("core", new BaseCommand());
        // register sub cmds
        handler.register("config", new PrefixSet());
        handler.register("reload", new Reload());
        handler.register("spawn", new Spawn());
        handler.register("test", new Test());

        getCommand("core").setExecutor(handler);
    }

    public static void reload() {
        ConfigManager.saveSpawnPreferences();
        ConfigManager.setupConfig(instance);
    }
}
