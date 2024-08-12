package site.hjfunny.skyisles.map;

import site.hjfunny.skyisles.map.world.WorldManager;

public class MapManager {
    public void restoreMap(final String mapName) {
        WorldManager.restoreWorld("game", mapName);
    }
}
