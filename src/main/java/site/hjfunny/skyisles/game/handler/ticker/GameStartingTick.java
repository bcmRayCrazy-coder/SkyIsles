package site.hjfunny.skyisles.game.handler.ticker;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
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
    public void onTick(GameTickEvent event) {
        if (gameManager.getGameState() != GameState.STARTING) return;
        if (gameManager.countdown == -1) return;
        gameManager.countdown -= 1;
        if (gameManager.countdown > 0) {
            if (gameManager.countdown % 100 == 0 || (gameManager.countdown <= 100 && gameManager.countdown % 10 == 0)) {
                sendCountdown();
            }
        } else {
            gameManager.countdown = -1;
            gameManager.broadcast(Component.text("游戏开始").color(TextColor.color(0x00ee00)));
            gameManager.setGameState(GameState.RUNNING);
        }
    }

    private void sendCountdown() {
        Component message = Component
                .text("游戏将在 ").color(TextColor.color(0x00ee00))
                .append(Component.text(gameManager.countdown / 10).color(TextColor.color(0xffff00)))
                .append(Component.text(" 秒后开始").color(TextColor.color(0x00ee00)));

        gameManager.broadcast(message);
    }
}
