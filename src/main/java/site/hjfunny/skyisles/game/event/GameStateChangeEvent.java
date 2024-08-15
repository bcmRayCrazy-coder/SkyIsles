package site.hjfunny.skyisles.game.event;

import site.hjfunny.skyisles.game.GameState;

/**
 * 游戏tick事件
 * 每100ms触发一次
 */
public class GameStateChangeEvent extends GameEventBase {
    private final GameState oldGameState;
    private final GameState newGameState;

    public GameStateChangeEvent(GameState oldGameState, GameState newGameState) {
        this.oldGameState = oldGameState;
        this.newGameState = newGameState;
    }

    public GameState getNewGameState() {
        return newGameState;
    }

    public GameState getOldGameState() {
        return oldGameState;
    }

}
