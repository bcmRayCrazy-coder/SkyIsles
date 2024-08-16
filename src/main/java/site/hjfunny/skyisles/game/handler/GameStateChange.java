package site.hjfunny.skyisles.game.handler;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.*;
import org.bukkit.entity.BlockDisplay;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.util.Transformation;
import org.joml.AxisAngle4f;
import org.joml.Vector3f;
import site.hjfunny.skyisles.Util;
import site.hjfunny.skyisles.game.GameManager;
import site.hjfunny.skyisles.game.GameState;
import site.hjfunny.skyisles.game.PlayerState;
import site.hjfunny.skyisles.game.event.GameStateChangeEvent;

import java.util.HashSet;
import java.util.List;
import java.util.UUID;

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
                gameManager.sendTitle(Component.text(gameManager.gameConfig.getString("title.run", "")));
                gameManager.countdown = gameManager.gameConfig.getInt("countdown.run");
                gameManager.broadcast(Component.text(gameManager.gameConfig.getInt("tip.run")));
                gameManager.setGamePlayers(gameManager.playersState, PlayerState.PLAYING);

                for (String uid : gameManager.players.keySet()) {
                    Player player = Bukkit.getPlayer(UUID.fromString(uid));
                    if (player != null) {
                        player.setGameMode(GameMode.ADVENTURE);
                    }
                }
            }
            case PLAYING -> {
                onPlaying();
                gameManager.sendTitle(Component.text(gameManager.gameConfig.getString("title.play", "")));
                gameManager.countdown = gameManager.gameConfig.getInt("countdown.play");
                gameManager.broadcast(Component.text(gameManager.gameConfig.getInt("tip.play")));
                gameManager.setGamePlayers(gameManager.playersRemainingRespawnTimes, gameManager.gameConfig.getInt("respawnTimes"));
            }
            case ENDING -> {
                gameManager.countdown = gameManager.gameConfig.getInt("countdown.end");

                for (String uid : gameManager.players.keySet()) {
                    Player player = Bukkit.getPlayer(UUID.fromString(uid));
                    if (player != null) {
                        PlayerState playerState = gameManager.playersState.get(uid);
                        if (playerState != PlayerState.WIN) player.setGameMode(GameMode.SPECTATOR);
                        else player.setGameMode(GameMode.CREATIVE);
                    }
                }
            }
        }
    }

    private void onPlaying() {
        World world = gameManager.getWorld();
        Location itemLocation = Util.toLocation("game", Util.selectRandom(new HashSet<List<Double>>(gameManager.mapConfig.itemSpawn)));
        assert world != null;
        gameManager.targetItem = world.spawnEntity(itemLocation, EntityType.BLOCK_DISPLAY);

        BlockDisplay blockDisplay = (BlockDisplay) gameManager.targetItem;
        blockDisplay.setGlowing(true);
        blockDisplay.setBlock(Material.GOLD_BLOCK.createBlockData());
        blockDisplay.setTransformation(new Transformation(
                new Vector3f(0, 0, 0),
                new AxisAngle4f((float) Math.toRadians(45), 0, 1, 0),
                new Vector3f(1, 1, 1),
                new AxisAngle4f(0, 0, 0, 0)
        ));
    }
}
