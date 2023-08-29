package com.scaler.tictactoe.strategies.gameWinningStragey;

import com.scaler.tictactoe.models.Board;
import com.scaler.tictactoe.models.Cell;
import com.scaler.tictactoe.models.Player;

public interface GameWinningStragey {
    //checking commits
    boolean checkWinner(Board board, Player player, Cell moveCell);

}
