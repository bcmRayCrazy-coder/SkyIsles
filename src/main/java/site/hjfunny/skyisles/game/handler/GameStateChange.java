package site.hjfunny.skyisles.game.handler;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import site.hjfunny.skyisles.game.GameManager;
import site.hjfunny.skyisles.game.GameState;
import site.hjfunny.skyisles.game.event.GameStateChangeEvent;

public class GameStateChange extends GameHandlerBase {
    public GameStateChange(GameManager gameManager) {
        super(gameManager);
    }

    @EventHandler
    public void onGameStateChange(GameStateChangeEvent event) {
        if (event.getNewGameState() == GameState.WAITING && event.getOldGameState() == GameState.STARTING) {
            Bukkit.getServer().broadcast(Component.text("人数不足, 倒计时取消!").color(TextColor.color(0xff0000)));
        } else if (event.getNewGameState() == GameState.STARTING) {
            gameManager.countdown = gameManager.gameConfig.getInt("startCountdown");
        }
    }
}
