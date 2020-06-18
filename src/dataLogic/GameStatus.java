package dataLogic;

enum GameStatus {
    NOT_BEGAN("not_began"),
    IN_PROGRESS("in_progress"),
    PLAYER_WON("player_won"),
    MATCH_DRAW("match_draw");

    String uiName;
    GameStatus(String uiName)
    {
        this.uiName = uiName;
    }

    String getUiName() {
        return uiName;
    }
}
