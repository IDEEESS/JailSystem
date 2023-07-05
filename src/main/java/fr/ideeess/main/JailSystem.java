package fr.ideeess.main;

import org.bukkit.plugin.java.JavaPlugin;

public class JailSystem extends JavaPlugin {

    @Override
    public void onEnable() {
        super.onEnable();

        System.out.println("This plugin has been enabled");

        this.saveConfig();
        this.saveDefaultConfig();
        getConfig().options().copyDefaults(true);

    }

    @Override
    public void onDisable() {
        super.onDisable();

        System.out.println("This plugin has been disabled");

        saveConfig();
    }
}
