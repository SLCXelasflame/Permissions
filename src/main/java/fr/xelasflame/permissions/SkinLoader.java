package fr.xelasflame.permissions;

import com.destroystokyo.paper.profile.PlayerProfile;
import com.destroystokyo.paper.profile.ProfileProperty;
import org.bukkit.entity.Player;
import org.bukkit.profile.PlayerTextures;

public class SkinLoader {

    public static void loadSkin(Player player){
        if(player.hasPermission("bse.admin")) {
            PlayerProfile profile = player.getPlayerProfile();
            profile.getProperties().remove("textures");
            profile.getProperties().add(new ProfileProperty("textures", "ewogICJ0aW1lc3RhbXAiIDogMTY3MTY1NTU0ODAyNywKICAicHJvZmlsZUlkIiA6ICI4NDk1NTRmOWYzMGQ0NWQxYjUwNGY4ZDYzZmExZTM1OSIsCiAgInByb2ZpbGVOYW1lIiA6ICJSYWtoYVNoaSIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS8yMzgxNDlhNjcxOTU2M2QxZWQzOTNiYThlNDBhMWI4N2U0YjZkNTQ2YzVjZmVhYzE2MmFkOGUzMTU4MmM3YzVmIgogICAgfQogIH0KfQ=="));
            player.setPlayerProfile(profile);
        }

    }
}
