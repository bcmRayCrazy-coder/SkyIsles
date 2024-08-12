package site.hjfunny.skyisles.game.event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;
import site.hjfunny.skyisles.game.GameManager;

public class DeathDrop extends GameEventBase {
    public DeathDrop(GameManager gameManager) {
        super(gameManager);
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {

    }
}
