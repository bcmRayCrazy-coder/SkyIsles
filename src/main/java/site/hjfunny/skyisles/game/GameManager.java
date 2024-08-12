package site.hjfunny.skyisles.game;

import org.bukkit.Bukkit;
import site.hjfunny.skyisles.SkyIsles;
import site.hjfunny.skyisles.game.event.DeathDrop;
import site.hjfunny.skyisles.map.MapManager;

import java.util.*;

public class GameManager {
    private final SkyIsles plugin;
    private MapManager mapManager = new MapManager();

    public HashMap<String, String> playersName = new HashMap<>();
    public HashMap<String, Boolean> playersAlive = new HashMap<>();
    public HashMap<String, PlayerState> playersState = new HashMap<>();

    public String mapName;

    public GameState gameState = GameState.WAITING;
    public GameConfig gameConfig;

    public GameManager(SkyIsles plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(new DeathDrop(this), plugin);

        resetMap();
    }

    public void resetMap() {
        Set<String> mapNames = Objects.requireNonNull(plugin.getConfig().getConfigurationSection("map")).getKeys(false);

        mapName = selectRandom(mapNames);
        loadGameConfig();
        mapManager.restoreMap(mapName);
    }

    public void loadGameConfig() {
        this.gameConfig = plugin.getConfig().getObject("map." + mapName, GameConfig.class);
    }

    private <T> T selectRandom(Set<T> set) throws IllegalStateException {
        if(set.isEmpty()) throw new IllegalStateException("Set can't be empty");
        int randomIndex = new Random().nextInt(set.size());
        int i = 0;
        for (T key : set) {
            if (i == randomIndex) return key;
            i++;
        }
        return selectRandom(set);
    }
}
