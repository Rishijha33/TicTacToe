package com.scaler.tictactoe;

import com.scaler.tictactoe.controllers.GameController;
import com.scaler.tictactoe.models.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TicTacToeMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GameController gameController = new GameController();

        System.out.println("What should be the dimension of the game");
        int dimension = scanner.nextInt();

        System.out.println("Will there be any bot in the game");

        String isBot = scanner.next();
        int numberOfplayers = dimension-1;
        int numberOfHumanPlayer = numberOfplayers;

        //List of players
        List<Player> players = new ArrayList<>();
        if(isBot.charAt(0) == 'y')
        {
            numberOfHumanPlayer -=1;
            System.out.println("Enter the Bot name");
            String botName = scanner.next();

            System.out.println("Enter the symbol of the bot");
            String symbol = scanner.next();

            players.add(new Bot(botName, symbol.charAt(0), PlayerType.BOT, BotDifficultyLevel.EASY));
        }


        //asking for the symbol for the each players
        for(int i = 0;i < numberOfHumanPlayer; i++)
        {
            System.out.println("Enter the name of the player");
            String name = scanner.next();


            System.out.println("Enter the player symbol");
            String symbol = scanner.next();

            players.add(new Player(name, symbol.charAt(0), PlayerType.HUMAN));
        }

        Game game = gameController.createGame(dimension, players);

        //Start Playing game

        while(gameController.getGameStatus(game).equals(GameStatus.IN_PROGRESS))
        {
            //play till the game status is in progress

            // Display the board to the current player to make the move
            System.out.println("This is the current board");
            gameController.displayBoard(game);

            System.out.println("Do you want to undo ? y/n");
            String undo = scanner.next();
            if(undo.charAt(0) == 'y')
            {
                gameController.undo(game);
            }
            else
            {
                gameController.executeNextMove(game);
            }

        }

        //if we are here that means game has been won by someone or is a draw

        if(gameController.getGameStatus(game).equals(GameStatus.ENDED))
        {
            //this means someone won
            System.out.println("Winner is "+ gameController.getWinner(game).getName());
        }
        else
        {
            System.out.println("Game Draw");
        }

    }
}