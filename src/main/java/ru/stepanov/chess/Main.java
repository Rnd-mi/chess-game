package ru.stepanov.chess;

import ru.stepanov.chess.board.Board;
import ru.stepanov.chess.board.BoardFactory;

public class Main {
    public static void main(String[] args) {
        Board board = new BoardFactory().getBoardFromFEN(
                "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1"
        );

        Game game = new Game(board);
        game.gameLoop();
    }
}
