package site.hjfunny.skyisles.map.world;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.generator.ChunkGenerator;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

/**
 * 生成空结构
 */
public class VoidChunkGenerator extends ChunkGenerator {
    @Override
    public final Location getFixedSpawnLocation(@NotNull World world, @NotNull Random random) {
        return new Location(world, 0.0D, 64.0D, 0.0D);
    }
}
