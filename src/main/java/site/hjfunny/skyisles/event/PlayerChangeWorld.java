package site.hjfunny.skyisles.event;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import site.hjfunny.skyisles.LOGGER;
import site.hjfunny.skyisles.game.event.PlayerJoinGameEvent;
import site.hjfunny.skyisles.game.event.PlayerLeaveGameEvent;

public class PlayerChangeWorld implements Listener {
    @EventHandler
    public void onPlayer(PlayerChangedWorldEvent event) {
        LOGGER.debug("Player " + event.getPlayer().getName() + " change world to " + event.getPlayer().getWorld().getName());
        if (event.getFrom().getName().equals("world")) {
            Bukkit.getPluginManager().callEvent(new PlayerJoinGameEvent(event.getPlayer()));
        } else if (event.getFrom().getName().equals("game")) {
            Bukkit.getPluginManager().callEvent(new PlayerLeaveGameEvent(event.getPlayer()));
        }

        if (event.getPlayer().getWorld().getName().equals("world")) {
            event.getPlayer().setGameMode(GameMode.ADVENTURE);
        }
    }
}
