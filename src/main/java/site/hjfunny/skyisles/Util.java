package site.hjfunny.skyisles;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.List;
import java.util.Random;
import java.util.Set;

public class Util {
    public static <T> T selectRandom(Set<T> set) throws IllegalStateException {
        if (set.isEmpty()) throw new IllegalStateException("Set can't be empty");
        int randomIndex = new Random().nextInt(set.size());
        int i = 0;
        for (T key : set) {
            if (i == randomIndex) return key;
            i++;
        }
        return selectRandom(set);
    }

    public static Location toLocation(String worldName, List<Double> vector) {
        return new Location(Bukkit.getWorld(worldName), vector.get(0), vector.get(1), vector.get(2));
    }
}
