package dataLogic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Board {

    int dimension;
    int currentTurn=0;
    int noOfPlayers;
    List<Player> players;

    GameStatus gameStatus;
    Player winningPlayer;
    Cell cells[][];
    GameManager gameManager;

    Board(int dimension, int noOfPlayers) {
        currentTurn = 0;
        gameStatus = GameStatus.NOT_BEGAN;
        this.dimension = dimension;
        this.noOfPlayers = noOfPlayers;
        players = new ArrayList<>();
        cells = new Cell[dimension][dimension];

        for (int i = 0; i < dimension; i++)
            for (int j = 0; j < dimension; j++)
                cells[i][j] = new Cell(i, j, null);
    }


    List<Mark> getAvailableMarksInBoard() {
        List listOfSelectedMarks = players.stream().map(x -> x.mark).collect(Collectors.toList());
        return Arrays.stream(Mark.values()).filter(x -> !listOfSelectedMarks.contains(x)).collect(Collectors.toList());
    }

    void playASlot(int x, int y) {
        if(x>=dimension || y>=dimension){
            System.out.println(" \n \n Enter Valid Coordinates \n \n ");
        }
        else if(cells[x][y].mark == null)
         {
             Player currentTurnPlayer = players.get(currentTurn % players.size());
             if (isGameCanContinue()) {
                 play(x, y, currentTurnPlayer.mark);
                 updateGameStatus(currentTurnPlayer);
                 currentTurn++;
             }

        }
        else if(cells[x][y].mark!=null)
        {
            System.out.println(" \n \n \t The Place is already Occupied \t \n \n ");
        }
}

    private boolean isGameCanContinue() {
        return currentTurn < dimension * dimension || gameStatus == GameStatus.NOT_BEGAN || gameStatus == GameStatus.IN_PROGRESS;
    }

    private void updateGameStatus(Player currentPlayer) {

          int place=0;

        for( int x=0;x<dimension;x++)
        {
            for(int y=0;y<dimension;y++)                           // checking win in rows
            {
                if(cells[x][y].mark!=currentPlayer.mark)
                {
                    break;
                }
                if(y==dimension-1 )
                {
                    gameStatus = GameStatus.PLAYER_WON;
                    winningPlayer = currentPlayer;
                    return;
                }

            }
        }
        for( int x=0;x<dimension;x++)        // checking win in columns
        {
            for(int y=0;y<dimension;y++)
            {
                if(cells[y][x].mark!=currentPlayer.mark)
                {
                    break;
                }
                if(y==dimension-1 )
                {
                    gameStatus = GameStatus.PLAYER_WON;
                    winningPlayer = currentPlayer;
                    return;
                }

            }
        }
        for(int y=0;y<dimension;y++)          // checking win in diagonal
        {
            if(cells[y][y].mark!= currentPlayer.mark)
            {
                break;
            }
            if(y==dimension-1 )
            {
                gameStatus = GameStatus.PLAYER_WON;
                winningPlayer = currentPlayer;
                return;
            }
        }
        int q=dimension-1;
        for( int x=0;x<dimension;x++,q--)        // checking win in anti diagonal
        {
            if(cells[x][q].mark!=currentPlayer.mark)
            {
                break;
            }

            if(x==dimension-1)
            {
                gameStatus = GameStatus.PLAYER_WON;
                winningPlayer = currentPlayer;
                return;
            }
        }
        for( int x=0;x<dimension;x++) {
            for (int y = 0; y < dimension; y++)
            {
                if (cells[x][y].mark!=null) {
                    place += 1;
                }
            }
        }
        if (place ==dimension*dimension ) {
            gameStatus = GameStatus.MATCH_DRAW;
            return;
        }



        gameStatus = GameStatus.IN_PROGRESS;
    }

    private void play(int x, int y, Mark mark) {
        cells[x][y].mark = mark;
    }

}
