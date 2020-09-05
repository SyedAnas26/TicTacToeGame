package runner;

import Console_Ui.UIManager;

public class TicTacToe {
    public static void main(String[] args) throws Exception {
        UIManager uiManager = new UIManager();
        uiManager.setUpGame();
        uiManager.conductGame();
    }
}
