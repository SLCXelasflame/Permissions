package fr.xelasflame.permissions.Listener;

import fr.xelasflame.permissions.Permissions;
import fr.xelasflame.permissions.SkinLoader;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Connection implements Listener {
    private File file = new File(Permissions.instance.getDataFolder(), "permissions.yml");
    private YamlConfiguration config = YamlConfiguration.loadConfiguration(file);

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        ConfigurationSection section = config.getConfigurationSection(player.getUniqueId().toString());
        if(section == null) return;
        List<String> permissions = section.getStringList("permissions");
        if(permissions.isEmpty()) return;
        for(String permission : permissions) {
            player.addAttachment(Permissions.instance, "bse." + permission, true);
        }
        SkinLoader.loadSkin(player);
    }
}
