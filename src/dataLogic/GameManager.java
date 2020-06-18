package dataLogic;



import java.util.Optional;
import java.util.stream.Collectors;

public class GameManager {

    Board board;

    public GameManager(int dimensions, int noOfPlayers) throws Exception {
        board = new Board(dimensions, noOfPlayers);
    }

    public String getAvailableMarksInBoard() {
        return board.getAvailableMarksInBoard().stream().map(x -> x.markStringFormat).collect(Collectors.joining(","));
    }

    public void addPlayerToTheGame(String name, String mark) throws Exception {
        board.players.add(new Player(name, Mark.getMark(mark)));
    }

    public void playASlot(int x, int y)
    {
        board.playASlot(x, y);
    }

    public String getGameStatus()
    {
        return board.gameStatus.getUiName();
    }

    public String getGameWinningPlayerName()
    {
        return board.winningPlayer.name;
    }

    public String getCellValueAt(int x, int y) {
        return Optional.ofNullable(board.cells[x][y].mark).map(k -> k.markStringFormat).orElse("#");
    }
}
