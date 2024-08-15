package site.hjfunny.skyisles.game.handler;

import org.bukkit.event.Listener;
import site.hjfunny.skyisles.game.GameManager;

public class GameHandlerBase implements Listener {
    public GameManager gameManager;

    public GameHandlerBase(GameManager gameManager) {
        this.gameManager = gameManager;
    }
}
