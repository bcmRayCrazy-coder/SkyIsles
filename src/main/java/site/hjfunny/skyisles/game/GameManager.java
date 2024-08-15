package site.hjfunny.skyisles.game;

import org.bukkit.Bukkit;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationSection;
import org.jetbrains.annotations.NotNull;
import site.hjfunny.skyisles.LOGGER;
import site.hjfunny.skyisles.SkyIsles;
import site.hjfunny.skyisles.game.event.GameStateChangeEvent;
import site.hjfunny.skyisles.game.handler.DeathDrop;
import site.hjfunny.skyisles.game.handler.GameTickHandlerManager;
import site.hjfunny.skyisles.game.handler.JoinGame;
import site.hjfunny.skyisles.game.handler.LeaveGame;
import site.hjfunny.skyisles.map.MapManager;

import java.util.*;

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

    public int countdown = 0;
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

    private void registerHandlers() {
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
        ConfigurationSection originMapConfig = plugin.getConfig().getConfigurationSection("map." + mapName);
        if (originMapConfig == null)
            throw new IllegalStateException("Cannot load map [" + mapName + "] config!");

        List<?> _itemSpawnList = originMapConfig.getList("itemSpawn");
        if (_itemSpawnList == null) throw new IllegalStateException("Invalid map config itemSpawn");

        List<List<Double>> itemSpawnList = getPositionList(_itemSpawnList);

        this.mapConfig = new MapConfig(originMapConfig.getString("name"),
                originMapConfig.getDoubleList("spawn"),
                itemSpawnList,
                originMapConfig.getIntegerList("goal"),
                originMapConfig.getDouble("voidY"));

        LOGGER.debug(String.format("Loaded Map Config:\nName: %s\nvoidY: %s", mapConfig.name, mapConfig.voidY));
    }

    private static @NotNull List<List<Double>> getPositionList(List<?> _itemSpawnList) {
        List<List<Double>> itemSpawnList = new ArrayList<>();
        for (Object _itemSpawn : _itemSpawnList) {
            if (!(_itemSpawn instanceof List<?>)) throw new IllegalStateException("Invalid map config itemSpawn");
            List<Double> innerList = new ArrayList<>();
            for (Object _p : (List<?>) _itemSpawn) {
                if (_p instanceof Integer) innerList.add(((Integer) _p).doubleValue());
                else if (_p instanceof Double)innerList.add((Double) _p);
                else throw new IllegalStateException("Invalid map config itemSpawn type " + _p.getClass().getName());
            }
            itemSpawnList.add(innerList);
        }
        return itemSpawnList;
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
        Bukkit.getPluginManager().callEvent(new GameStateChangeEvent(this.gameState, gameState));
        this.gameState = gameState;
    }
}
