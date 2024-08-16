package site.hjfunny.skyisles.game.handler;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import site.hjfunny.skyisles.game.GameManager;
import site.hjfunny.skyisles.game.GameState;
import site.hjfunny.skyisles.game.event.PlayerJoinGameEvent;

public class JoinGame extends GameHandlerBase {
    public JoinGame(GameManager gameManager) {
        super(gameManager);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinGameEvent event) {
        switch (gameManager.getGameState()) {
            case WAITING, STARTING -> joinGame(event.getPlayer());
            case RUNNING, PLAYING, ENDING -> spectateGame(event.getPlayer());
        }
    }

    private void joinGame(Player player) {
        gameManager.players.put(player.getUniqueId().toString(), player.getName());
        Bukkit.getServer().broadcast(Component.text("游戏人数" + gameManager.players.size()));
        if(gameManager.getGameState() == GameState.WAITING && gameManager.players.size() >= gameManager.gameConfig.getInt("minPlayers")){
            gameManager.setGameState(GameState.STARTING);
        }
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
