package site.hjfunny.skyisles.game;

import org.bukkit.Bukkit;
import org.bukkit.configuration.Configuration;
import site.hjfunny.skyisles.SkyIsles;
import site.hjfunny.skyisles.game.handler.DeathDrop;
import site.hjfunny.skyisles.game.handler.GameTickHandlerManager;
import site.hjfunny.skyisles.game.handler.JoinGame;
import site.hjfunny.skyisles.game.handler.LeaveGame;
import site.hjfunny.skyisles.map.MapManager;

import java.util.HashMap;
import java.util.Objects;
import java.util.Random;
import java.util.Set;

public class GameManager {
    public final SkyIsles plugin;
    private final MapManager mapManager = new MapManager();

    public HashMap<String, String> players = new HashMap<>();
    public HashMap<String, Boolean> playersAlive = new HashMap<>();
    public HashMap<String, PlayerState> playersState = new HashMap<>();

    public String mapName;

    private GameState gameState = GameState.WAITING;
    public Configuration gameConfig;
    public MapConfig mapConfig;

    public int countdown = 80;
    public boolean ticking = false;
    private int tick = 0;

    public GameManager(SkyIsles plugin) {
        this.plugin = plugin;
        this.gameConfig = plugin.getConfig();

        registerHandlers();

        resetMap();

        Ticker ticker = new Ticker(this);
        ticker.start();
    }

    private void registerHandlers(){
        Bukkit.getPluginManager().registerEvents(new DeathDrop(this), plugin);
        Bukkit.getPluginManager().registerEvents(new JoinGame(this), plugin);
        Bukkit.getPluginManager().registerEvents(new LeaveGame(this), plugin);

        GameTickHandlerManager gameTickHandlerManager = new GameTickHandlerManager(this);
        gameTickHandlerManager.register();
    }

    /**
     * 重置随机地图
     */
    public void resetMap() {
        Set<String> mapNames = Objects.requireNonNull(plugin.getConfig().getConfigurationSection("map")).getKeys(false);

        mapName = selectRandom(mapNames);
        loadGameConfig();
        mapManager.restoreMap(mapName);
    }

    public void loadGameConfig() {
        this.mapConfig = plugin.getConfig().getObject("map." + mapName, MapConfig.class);
    }

    private <T> T selectRandom(Set<T> set) throws IllegalStateException {
        if (set.isEmpty()) throw new IllegalStateException("Set can't be empty");
        int randomIndex = new Random().nextInt(set.size());
        int i = 0;
        for (T key : set) {
            if (i == randomIndex) return key;
            i++;
        }
        return selectRandom(set);
    }

    /**
     * 更改倒计时
     * 若新倒计时大于目前倒计时, 更新无效
     * 若需增加倒计时, 请直接修改countdown
     *
     * @param newCountdown 新倒计时
     */
    public void updateCountdown(int newCountdown) {
        if (newCountdown > countdown) return;
        countdown = newCountdown;
    }

    public int getTick() {
        return tick;
    }

    public void setTick(int tick) {
        this.tick = tick;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }
}
