package site.hjfunny.skyisles.game.handler;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import site.hjfunny.skyisles.game.GameManager;
import site.hjfunny.skyisles.game.GameState;
import site.hjfunny.skyisles.game.event.PlayerLeaveGameEvent;

public class LeaveGame extends GameHandlerBase {
    public LeaveGame(GameManager gameManager) {
        super(gameManager);
    }

    @EventHandler
    public void onPlayerLeave(PlayerLeaveGameEvent event) {
        Player player = event.getPlayer();
        if (player.getGameMode() == GameMode.SPECTATOR) return;
        switch (gameManager.getGameState()) {
            case WAITING, STARTING -> quitGame(player);
            case RUNNING, PLAYING -> playerDisconnect(player);
        }
    }

    private void quitGame(Player player) {
        gameManager.players.remove(player.getUniqueId().toString());
        if(gameManager.getGameState() == GameState.STARTING && gameManager.players.size() < gameManager.gameConfig.getInt("minPlayers")){
            gameManager.setGameState(GameState.WAITING);
        }
    }

    private void playerDisconnect(Player player) {
    }
}
