package Console_Ui;

import dataLogic.GameManager;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UIManager {

    int noOfPlayers;
    int dimension;
    GameManager gameManager;

    public UIManager()
    {

    }

     public void setUpGame() throws Exception {

         System.out.println(" Welcome to The Tic tac Toe Game !!");
         this.noOfPlayers = 2;
         int dim = getDimensions();
         if (dim == 0) {
             setUpGame();
         } else {
             this.dimension = dim;
             gameManager = new GameManager(dimension, noOfPlayers);
             for (int i = 0; i < noOfPlayers; i++) {
                 Player player = buildFromUser();
                 gameManager.addPlayerToTheGame(player.name, player.mark);

             }
         }
     }

    public void conductGame() {


            while(isGameCanContinue()){
                Scanner Scan = new Scanner(System.in);
                printBoard();
                System.out.println(" Enter the Cell Positon X, Y");
                int x = Scan.nextInt();
                int y = Scan.nextInt();
                gameManager.playASlot(x, y);

            }
               printBoard();
                printResult();

        }


    //HELPER METHODS..........


    private Player buildFromUser() throws Exception
    {
        Scanner Scan = new Scanner(System.in);
        System.out.println(" Enter the Name of the Player");
        String name = Scan.nextLine();
        List<String> availableMarks = Arrays.asList(gameManager.getAvailableMarksInBoard().split(","));

        String mark;
        do {
            System.out.println(" Choose your Mark: Available Marks.." + gameManager.getAvailableMarksInBoard());
            mark = Scan.nextLine();
        } while (!availableMarks.contains(mark));

        return new Player(name, mark);
    }


    private boolean isGameCanContinue() {
        String gameStatus = gameManager.getGameStatus();
        return gameStatus.equals("not_began") || gameStatus.equals("in_progress");
    }

    private void printResult()
    {
        String gameStatus = gameManager.getGameStatus();
        if(gameStatus.equals("match_draw"))
        {
            System.out.println(" \n \n               No one Wins ..... The Match has Draw !!        \n \n     ");
        }
        else if(gameStatus.equals("player_won"))
        {
            System.out.println( "\n \n                    Congrats!!" + gameManager.getGameWinningPlayerName() + " wins !!!!!!             \n \n    ");
        }
        else
        {
            Logger.getLogger(UIManager.class.getName()).log(Level.SEVERE, "There is some mishap! Current game status is. " + gameStatus);
        }

    }

    private void printBoard()
    {
        for( int x=0;x<dimension;x++)        // checking win in columns
        {

                       for (int y = 0; y < dimension; y++) {
                System.out.printf(" %3s ||", gameManager.getCellValueAt(x, y));  // for single digit num
            }

            System.out.print("\n\n");
        }

    }

    private static int getDimensions()
    {
        Scanner Scan=new Scanner(System.in);
        System.out.println(" Enter The Dimension for The Board");
        String scan =Scan.next();
        if(checkIfNum(scan)){
            return Character.getNumericValue(scan.charAt(0));
        }
        else
        {System.out.println("\n \n Enter a Valid Dimension below 10 \n \n");}
        return 0;
    }
   static private boolean checkIfNum(String element) {
        for (int i = 1; i < 10; i++) {
            if (String.valueOf(i).equals(element)) {
                return true;
            }
        }
        return false;
    }

}
