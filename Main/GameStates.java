package Main;

public enum GameStates {
    PLAYING,
    TITLE,
    SETTINGS;

    public static GameStates gamestate = TITLE;

    public static void setGameState(GameStates state) {
        gamestate = state;
    }
}
