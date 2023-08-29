package com.scaler.tictactoe.models;

import com.scaler.tictactoe.strategies.botPlayingStrategy.BotPlayingStrategy;
import com.scaler.tictactoe.strategies.botPlayingStrategy.RandomBotPlayingStrategy;

public class Bot extends Player {
    private BotDifficultyLevel botDifficultyLevel;
    private BotPlayingStrategy botPlayingStrategy;

    public Bot(String name, char symbol, PlayerType playerType, BotDifficultyLevel difficultyLevel) {
        super(name, symbol, playerType);
        this.botDifficultyLevel = difficultyLevel;
        this.botPlayingStrategy = new RandomBotPlayingStrategy();
    }

    public BotDifficultyLevel getBotDifficultyLevel() {
        return botDifficultyLevel;
    }

    public void setBotDifficultyLevel(BotDifficultyLevel botDifficultyLevel) {
        this.botDifficultyLevel = botDifficultyLevel;
    }

    @Override
    Move decideMove(Board board)
    {
        System.out.println("BOT " + this.getName()+ " is deciding");
        return botPlayingStrategy.decideMove(this, board);
    }
}
