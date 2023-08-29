package com.scaler.tictactoe.strategies.gameWinningStragey;

import com.scaler.tictactoe.models.Board;
import com.scaler.tictactoe.models.Cell;
import com.scaler.tictactoe.models.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrderOneWinningStrategy implements GameWinningStragey{
    private List<HashMap<Character, Integer>> rowSymbolCounts = new ArrayList<>();
    private List<HashMap<Character, Integer>> colSymbolCounts = new ArrayList<>();
    HashMap<Character, Integer> topLeftDiagonal = new HashMap<>();
    HashMap<Character, Integer> topRightDiagonal = new HashMap<>();

    public OrderOneWinningStrategy(int dimension)
    {
        for(int i=0;i<dimension;i++) {
            rowSymbolCounts.add(new HashMap<>());
            colSymbolCounts.add(new HashMap<>());
        }
    }

    private boolean isCellOnTopLeftDiagonal(int row, int col)
    {
        //check if the row is equal to column

        return row == col;
    }

    private boolean isCellOnTopLRightDiagonal(int row, int col, int dimension)
    {
        return row+col == dimension-1;
    }


    @Override
    public boolean checkWinner(Board board, Player player, Cell moveCell) {
        char symbol =   player.getSymbol();
        int row = moveCell.getRow();
        int col = moveCell.getCol();
        int dimension = board.getBoard().size();

        //increment the symbol count of row, column and diagonal if applicable
        //incrementing row
        if(!rowSymbolCounts.get(row).containsKey(symbol))
        {
            rowSymbolCounts.get(row).put(symbol, 0);
        }
        rowSymbolCounts.get(row).put(symbol, rowSymbolCounts.get(row).get(symbol)+1);
        //rowSymbolCounts.get(row).put(symbol, rowSymbolCounts.get(row).getOrDefault(symbol, 0)+1);

        //incrementing columns
        if(!colSymbolCounts.get(col).containsKey(symbol))
        {
            colSymbolCounts.get(col).put(symbol, 0);
        }
        colSymbolCounts.get(col).put(symbol, colSymbolCounts.get(col).get(symbol)+1);
        //colSymbolCounts.get(col).put(symbol, colSymbolCounts.get(col).getOrDefault(symbol, 0)+1);

        //check if the current ele is on diagonal
        if(isCellOnTopLeftDiagonal(row, col))
        {
            topLeftDiagonal.put(symbol, topLeftDiagonal.getOrDefault(symbol, 0)+1);
        }

        if(isCellOnTopLRightDiagonal(row, col,dimension))
        {
            topRightDiagonal.put(symbol, topRightDiagonal.getOrDefault(symbol, 0)+1);
        }

        //checking if the current player has won the game
        if(rowSymbolCounts.get(row).get(symbol) == dimension ||
           colSymbolCounts.get(col).get(symbol) == dimension)
            return true;

        if(isCellOnTopLeftDiagonal(row, col))
        {
            return topLeftDiagonal.get(symbol) == dimension;
        }

        if(isCellOnTopLRightDiagonal(row, col, dimension))
        {
            return topRightDiagonal.get(symbol) == dimension;
        }

        return false;
    }
}
