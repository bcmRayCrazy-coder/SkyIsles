package site.hjfunny.skyisles.game;

public enum GameState {
    /**
     * 等待玩家加入
     */
    WAITING,

    /**
     * 开始倒计时
     */
    STARTING,

    /**
     * 玩家分散
     */
    RUNNING,

    /**
     * 游戏进行
     */
    PLAYING,

    /**
     * 游戏结束
     */
    ENDING
}
