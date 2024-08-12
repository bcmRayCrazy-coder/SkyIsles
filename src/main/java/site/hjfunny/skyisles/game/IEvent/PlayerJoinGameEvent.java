package site.hjfunny.skyisles.game.IEvent;

import org.bukkit.entity.Player;

public class PlayerJoinGameEvent extends IEvent{
    private final Player player;

    public PlayerJoinGameEvent(Player player){
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }
}
