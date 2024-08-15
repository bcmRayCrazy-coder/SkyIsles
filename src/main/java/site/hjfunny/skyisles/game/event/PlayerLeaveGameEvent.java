package site.hjfunny.skyisles.game.event;

import org.bukkit.entity.Player;

public class PlayerLeaveGameEvent extends GameEventBase {
    private final Player player;

    public PlayerLeaveGameEvent(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }
}
