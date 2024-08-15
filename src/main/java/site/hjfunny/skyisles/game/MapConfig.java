package site.hjfunny.skyisles.game;

import java.util.List;

public class MapConfig {
    public String name;
    public List<Double> spawn;
    public List<List<Double>> itemSpawn;
    public List<Integer> goal;
    public Double voidY;

    public MapConfig(String name, List<Double> spawn, List<List<Double>> itemSpawn, List<Integer> goal, Double voidY) {
        this.name = name;
        this.spawn = spawn;
        this.itemSpawn = itemSpawn;
        this.goal = goal;
        this.voidY = voidY;
    }
}
