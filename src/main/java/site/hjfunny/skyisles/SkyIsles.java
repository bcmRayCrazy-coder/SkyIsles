package site.hjfunny.skyisles;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.codehaus.plexus.util.FileUtils;
import site.hjfunny.skyisles.command.JoinCommand;
import site.hjfunny.skyisles.command.QuitCommand;
import site.hjfunny.skyisles.event.PlayerChangeWorld;
import site.hjfunny.skyisles.game.GameManager;
import site.hjfunny.skyisles.map.world.WorldManager;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

import static site.hjfunny.skyisles.map.world.WorldManager.createWorld;

public final class SkyIsles extends JavaPlugin {
    public static SkyIsles plugin = null;

    @Override
    public void onEnable() {
        LOGGER.logger = getLogger();
        plugin = this;

        if (!WorldManager.backupFolder.exists()) {
            saveDefaultConfig();
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

        getServer().getPluginManager().registerEvents(new PlayerChangeWorld(), this);

        Objects.requireNonNull(getCommand("join")).setExecutor(new JoinCommand());
        Objects.requireNonNull(getCommand("quit")).setExecutor(new QuitCommand());

        new GameManager(this);
    }

    @Override
    public void onDisable() {
        Bukkit.getScheduler().cancelTasks(this);
        saveConfig();
    }
}
