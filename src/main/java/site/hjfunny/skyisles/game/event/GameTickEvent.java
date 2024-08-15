package site.hjfunny.skyisles.game.event;

/**
 * 游戏tick事件
 * 每100ms触发一次
 */
public class GameTickEvent extends GameEventBase {
    private final int tick;

    public GameTickEvent(int tick) {
        this.tick = tick;
    }

    public int getTick() {
        return tick;
    }
}
