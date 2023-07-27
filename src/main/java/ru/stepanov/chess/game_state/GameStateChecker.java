package ru.stepanov.chess.game_state;

import ru.stepanov.chess.Color;
import ru.stepanov.chess.board.Board;

public abstract class GameStateChecker {
    public abstract GameState check(Board board, Color color);
}
