package site.hjfunny.skyisles.game.handler;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import site.hjfunny.skyisles.game.GameManager;
import site.hjfunny.skyisles.game.event.PlayerJoinGameEvent;

public class JoinGame extends GameHandlerBase {
    public JoinGame(GameManager gameManager) {
        super(gameManager);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinGameEvent event) {
        switch (gameManager.gameState) {
            case WAITING, STARTING -> joinGame(event.getPlayer());
            case RUNNING, PLAYING, ENDING -> spectateGame(event.getPlayer());
        }
    }

    private void joinGame(Player player) {
        gameManager.players.put(player.getUniqueId().toString(), player.getName());
    }

    private void spectateGame(Player player) {
        if (gameManager.players.get(player.getUniqueId().toString()) != null && gameManager.playersAlive.get(player.getUniqueId().toString())) {
            playerReconnect(player);
            return;
        }
        player.setGameMode(GameMode.SPECTATOR);
    }

    private void playerReconnect(Player player) {
    }
}
