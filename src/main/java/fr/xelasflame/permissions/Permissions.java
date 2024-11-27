package fr.xelasflame.permissions;

import fr.xelasflame.permissions.Listener.Connection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class Permissions extends JavaPlugin {
    public static Permissions instance;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        instance.getLogger().warning("Plugin permissions has been enabled");
        this.getServer().getPluginManager().registerEvents(new Connection(), this);
        this.getCommand("bse").setExecutor(new Commands());
        saveResource("permissions.yml", false);
    }

    @Override
    public void onDisable() {
        instance.getLogger().warning("Plugin permissions has been disabled");
    }
}
