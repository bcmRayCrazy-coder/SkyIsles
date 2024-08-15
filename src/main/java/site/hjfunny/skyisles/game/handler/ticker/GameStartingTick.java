package site.hjfunny.skyisles.game.handler.ticker;

import org.bukkit.event.EventHandler;
import site.hjfunny.skyisles.game.GameManager;
import site.hjfunny.skyisles.game.GameState;
import site.hjfunny.skyisles.game.event.GameTickEvent;
import site.hjfunny.skyisles.game.handler.GameHandlerBase;

public class GameStartingTick extends GameHandlerBase {
    public GameStartingTick(GameManager gameManager) {
        super(gameManager);
    }

    @EventHandler
    public void onTick(GameTickEvent event){
        if(gameManager.getGameState() != GameState.STARTING) return;
    }
}
