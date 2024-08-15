package site.hjfunny.skyisles.game.event;

import org.bukkit.entity.Player;

public class PlayerJoinGameEvent extends GameEventBase {
    private final Player player;

    public PlayerJoinGameEvent(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }
}
