package site.hjfunny.skyisles.game.event;

import org.bukkit.event.Listener;
import site.hjfunny.skyisles.game.GameManager;

public class GameEventBase implements Listener {
    public GameManager gameManager;

    public GameEventBase(GameManager gameManager){
        this.gameManager = gameManager;
    }
}
