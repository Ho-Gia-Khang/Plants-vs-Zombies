package Main;

public enum GameStates {
    PLAYING,
    TITLE,
    SETTINGS;

    public static GameStates gamestate = TITLE;

    public static void SetGameState(GameStates state) {
        gamestate = state;
    }
}
