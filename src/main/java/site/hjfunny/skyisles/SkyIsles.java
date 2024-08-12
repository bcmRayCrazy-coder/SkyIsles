package site.hjfunny.skyisles;

import org.bukkit.*;
import org.bukkit.plugin.java.JavaPlugin;
import org.codehaus.plexus.util.FileUtils;
import site.hjfunny.skyisles.game.GameMain;
import site.hjfunny.skyisles.map.world.WorldManager;

import java.io.File;
import java.io.IOException;

import static site.hjfunny.skyisles.map.world.WorldManager.createWorld;

public final class SkyIsles extends JavaPlugin {
    public static SkyIsles plugin = null;

    @Override
    public void onEnable() {
        LOGGER.logger = getLogger();
        plugin = this;

        if (!WorldManager.backupFolder.exists()) {
            try {
                FileUtils.forceMkdir(WorldManager.backupFolder);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        File worldFile = new File(getServer().getWorldContainer().getPath() + "/game");
        if (!worldFile.exists()) {
            createWorld("game");
        }
        new GameMain(this);

    }

    @Override
    public void onDisable() {
        Bukkit.getScheduler().cancelTasks(this);
    }
}
