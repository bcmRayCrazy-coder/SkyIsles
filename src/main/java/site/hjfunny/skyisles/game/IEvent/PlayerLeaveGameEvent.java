package site.hjfunny.skyisles.game.IEvent;

import org.bukkit.entity.Player;

public class PlayerLeaveGameEvent extends IEvent{
    private final Player player;

    public PlayerLeaveGameEvent(Player player){
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }
}
