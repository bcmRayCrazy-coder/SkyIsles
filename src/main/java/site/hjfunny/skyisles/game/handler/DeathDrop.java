package site.hjfunny.skyisles.game.handler;

import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;
import site.hjfunny.skyisles.game.GameManager;

public class DeathDrop extends GameHandlerBase {
    public DeathDrop(GameManager gameManager) {
        super(gameManager);
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {

    }
}
