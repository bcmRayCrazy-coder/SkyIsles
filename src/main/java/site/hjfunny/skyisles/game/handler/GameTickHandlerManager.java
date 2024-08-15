package site.hjfunny.skyisles.game.handler;

import org.bukkit.Bukkit;
import site.hjfunny.skyisles.game.GameManager;
import site.hjfunny.skyisles.game.handler.ticker.*;

public class GameTickHandlerManager {
    private final GameManager gameManager;

    public GameTickHandlerManager(GameManager gameManager){
        this.gameManager = gameManager;
    }

    public void register(){
        Bukkit.getPluginManager().registerEvents(new GameStartingTick(gameManager),gameManager.plugin);
        Bukkit.getPluginManager().registerEvents(new GameRunningTick(gameManager),gameManager.plugin);
        Bukkit.getPluginManager().registerEvents(new GamePlayingTick(gameManager),gameManager.plugin);
        Bukkit.getPluginManager().registerEvents(new GameEndingTick(gameManager),gameManager.plugin);
    }
}
