package site.hjfunny.skyisles.game.event;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import site.hjfunny.skyisles.game.GameManager;
import site.hjfunny.skyisles.game.IEvent.PlayerJoinGameEvent;

public class JoinGame extends GameEventBase {
    public JoinGame(GameManager gameManager) {
        super(gameManager);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinGameEvent event){
        switch (gameManager.gameState){
            case WAITING, STARTING -> joinGame(event.getPlayer());
            case PLAYING, ENDING -> spectateGame(event.getPlayer());
        }
    }

    private void joinGame(Player event){}
    private void spectateGame(Player event){}
}
