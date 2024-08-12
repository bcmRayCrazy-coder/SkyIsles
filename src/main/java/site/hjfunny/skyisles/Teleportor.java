package site.hjfunny.skyisles;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class Teleportor {
    public static void teleport(Player player, String worldName, double x, double y, double z) {
        Location location = new Location(Bukkit.getWorld(worldName), x, y, z);
        player.teleport(location);
    }
}
