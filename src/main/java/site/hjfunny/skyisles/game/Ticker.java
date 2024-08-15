package site.hjfunny.skyisles.game;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;
import site.hjfunny.skyisles.game.event.GameTickEvent;

public class Ticker extends BukkitRunnable {
    private final GameManager gameManager;

    public Ticker(GameManager gameManager){
        this.gameManager = gameManager;
    }

    public void start(){
        this.runTaskTimer(gameManager.plugin,0,2L);
    }

    @Override
    public void run() {
        if(gameManager.ticking) {
            int tick = gameManager.getTick();
            Bukkit.getPluginManager().callEvent(new GameTickEvent(tick));
            gameManager.setTick(tick);
        }
    }
}
