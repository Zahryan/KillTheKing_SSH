package net.Zahryan.KillTheKing.game;

/**
 * Created by USER on 17/09/2016.
 */

@SuppressWarnings("ALL")
public enum GameState {

    EN_ATTENTE, EN_JEU, FIN;

    private static GameState etatActuel;

    public static void setState(GameState state) {
        GameState.etatActuel = state;
    }

    public static boolean isState(GameState state) {
        return etatActuel == state;
    }
}
