package site.hjfunny.skyisles.game.handler;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.title.Title;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import site.hjfunny.skyisles.game.GameManager;
import site.hjfunny.skyisles.game.GameState;
import site.hjfunny.skyisles.game.PlayerState;
import site.hjfunny.skyisles.game.event.GameStateChangeEvent;

public class GameStateChange extends GameHandlerBase {
    public GameStateChange(GameManager gameManager) {
        super(gameManager);
    }

    @EventHandler
    public void onGameStateChange(GameStateChangeEvent event) {
        switch (event.getNewGameState()) {
            case WAITING -> {
                if (event.getOldGameState() == GameState.STARTING) {
                    gameManager.broadcast(Component.text("人数不足, 倒计时取消!").color(TextColor.color(0xff0000)));
                }
            }

            case STARTING -> gameManager.countdown = gameManager.gameConfig.getInt("countdown.start");
            case RUNNING -> {
                gameManager.sendTitle(Component.text(gameManager.gameConfig.getString("title.run","")));
                gameManager.countdown = gameManager.gameConfig.getInt("countdown.run");
                gameManager.broadcast(Component.text(gameManager.gameConfig.getInt("tip.run")));
                gameManager.setGamePlayers(gameManager.playersState, PlayerState.PLAYING);

                for (String uid : gameManager.players.keySet()) {
                    Player player = Bukkit.getPlayer(uid);
                    if (player != null) {
                        player.setGameMode(GameMode.ADVENTURE);
                    }
                }
            }
            case PLAYING -> {
                onPlaying();
                gameManager.sendTitle(Component.text(gameManager.gameConfig.getString("title.play","")));
                gameManager.countdown = gameManager.gameConfig.getInt("countdown.play");
                gameManager.broadcast(Component.text(gameManager.gameConfig.getInt("tip.play")));
                gameManager.setGamePlayers(gameManager.playersRemainingRespawnTimes,gameManager.gameConfig.getInt("respawnTimes"));
            }
            case ENDING -> {
                gameManager.countdown = gameManager.gameConfig.getInt("countdown.end");

                for (String uid : gameManager.players.keySet()) {
                    Player player = Bukkit.getPlayer(uid);
                    if (player != null) {
                        PlayerState playerState = gameManager.playersState.get(uid);
                        if(playerState != PlayerState.WIN) player.setGameMode(GameMode.SPECTATOR);
                        else player.setGameMode(GameMode.CREATIVE);
                    }
                }
            }
        }
    }

    private void onPlaying(){

    }
}
