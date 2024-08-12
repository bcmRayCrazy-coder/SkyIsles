package site.hjfunny.skyisles.map.world;

import org.bukkit.Bukkit;
import org.bukkit.Difficulty;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import site.hjfunny.skyisles.LOGGER;
import site.hjfunny.skyisles.SkyIsles;
import site.hjfunny.skyisles.map.file.FileUtil;
import site.hjfunny.skyisles.map.file.ZipFileUtil;

import java.io.File;
import java.io.IOException;

public class WorldManager {

    public static final File backupFolder = new File(SkyIsles.plugin.getDataFolder() + "/Backup");

    /**
     * 删除世界多余数据(还原为未初始化的世界)
     *
     * @param world 目标世界
     */
    public static void deleteWorldTrash(String world) {
        for (String filename : new String[]{"level.dat", "level.dat_mcr", "level.dat_old", "session.lock", "uid.dat"}
        ) {
            File f = new File(Bukkit.getWorldContainer(), world + "/" + filename);
            if (f.exists()) {
                if (!f.delete()) {
                    LOGGER.logger.warning("无法删除世界 " + f.getPath());
                }
            }
        }
    }

    public static void createWorld(String name) {
        WorldCreator worldCreator = new WorldCreator(name);
        worldCreator.generateStructures(false);
        worldCreator.generator(new VoidChunkGenerator());

        final World world = worldCreator.createWorld();
        if(world == null){
            throw new IllegalStateException("World is null");
        }
        world.setPVP(true);
        world.setAutoSave(false);
        world.setDifficulty(Difficulty.NORMAL);
        world.setSpawnFlags(false, false);
        world.setKeepSpawnInMemory(true);
    }

    /**
     * 重置世界
     *
     * @param worldName 世界名称
     * @param zipName 压缩世界名称
     */
    public static void restoreWorld(String worldName,String zipName) {
        // 备份的世界, 原世界
        File zipWorld = new File(backupFolder, zipName + ".zip"),
                originWorld = new File(Bukkit.getWorldContainer(), worldName);
        if (zipWorld.exists()) {
            // 删除
            FileUtil.delete(originWorld);
        }
        if (!zipWorld.exists()) {
            throw new IllegalStateException("世界不存在");
        } else {
            // 解压并还原世界
            try {
                ZipFileUtil.unzipFileIntoDirectory(zipWorld, new File(Bukkit.getWorldContainer(), worldName));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        deleteWorldTrash(worldName);

        // 异步生成世界
        Bukkit.getScheduler().runTask(SkyIsles.plugin, () -> createWorld(worldName));
    }

}
