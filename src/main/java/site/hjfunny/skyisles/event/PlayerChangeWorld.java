package site.hjfunny.skyisles.event;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import site.hjfunny.skyisles.game.IEvent.PlayerJoinGameEvent;
import site.hjfunny.skyisles.game.IEvent.PlayerLeaveGameEvent;

public class PlayerChangeWorld implements Listener {
    @EventHandler
    public void onPlayer(PlayerChangedWorldEvent event) {
        if (event.getFrom().getName().equals("world")) {
            Bukkit.getPluginManager().callEvent(new PlayerJoinGameEvent(event.getPlayer()));
        } else if (event.getFrom().getName().equals("game")) {
            Bukkit.getPluginManager().callEvent(new PlayerLeaveGameEvent(event.getPlayer()));
        }
    }
}
