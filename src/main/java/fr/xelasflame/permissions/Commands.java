package fr.xelasflame.permissions;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.awt.image.BufferedImage;
import java.io.Console;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class Commands implements CommandExecutor {
    private File file = new File(Permissions.instance.getDataFolder(), "permissions.yml");
    private YamlConfiguration config = YamlConfiguration.loadConfiguration(file);

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(!s.equalsIgnoreCase("bse")) return false;
        Player player = Bukkit.getPlayer(strings[2]);
            if((strings[0].equalsIgnoreCase("permissions") || strings[0].equalsIgnoreCase("perms")) && strings.length == 4) {
                if((commandSender instanceof Player && commandSender.hasPermission("bse.admin")) || commandSender instanceof ConsoleCommandSender) {
                    assert player != null;
                    ConfigurationSection section = config.getConfigurationSection(player.getUniqueId().toString());
                    if(section == null){
                        section = config.createSection(player.getUniqueId().toString());
                        section.createSection("permissions");
                    }
                    List<String> perms = section.getStringList("permissions");
                    if(strings[1].equalsIgnoreCase("remove")){
                        if(perms.contains(strings[3])) {perms.remove(strings[3]);}
                    }
                    else{
                        if(!perms.contains(strings[3])) {perms.add(strings[3]);}
                    }
                    if(perms.isEmpty()){
                        config.set(player.getUniqueId().toString(), null);
                    }
                    else{
                        section.set("permissions", perms);

                    }
                    player.addAttachment(Permissions.instance, "bse." + strings[3], strings[1].equalsIgnoreCase("add"));
                    if(strings[1].equalsIgnoreCase("add")){
                        commandSender.sendMessage("Vous avez mis la permission " + strings[3] + " au joueur " + strings[2]);
                        player.sendMessage("Vous êtes maintenant " + strings[3]);
                    }
                    else{
                        commandSender.sendMessage("Vous avez retiré la permission " + strings[3] + " au joueur " + strings[2]);
                        player.sendMessage("Vous n'êtes desormais plus " + strings[3]);
                    }

                    try {
                        config.save(file);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    return true;
                }
                commandSender.sendMessage("Vous n'avez pas les drois de le faire");
                return false;
            }
            commandSender.sendMessage("Erreur dans la commande " + s);

        return false;
    }
}
