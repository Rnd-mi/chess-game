package ru.stepanov.chess;

import ru.stepanov.chess.board.Board;
import ru.stepanov.chess.board.BoardConsoleRender;
import ru.stepanov.chess.game_state.CheckMateChecker;
import ru.stepanov.chess.game_state.GameState;
import ru.stepanov.chess.game_state.GameStateChecker;
import ru.stepanov.chess.game_state.StaleMateChecker;

import java.util.List;

import static ru.stepanov.chess.Color.*;

public class Game {
    private final Board board;
    private final BoardConsoleRender renderer;
    private final List<GameStateChecker> stateCheckers = List.of(
            new StaleMateChecker(), new CheckMateChecker()
    );

    public Game(Board board) {
        this.board = board;
        renderer = new BoardConsoleRender();
    }

    public void gameLoop() {
        Color colorToMove = WHITE;
        GameState state = defineGameState(board, colorToMove);

        while (state == GameState.ONGOING) {
            // render
            renderer.render(board);

            if (colorToMove == WHITE) {
                System.out.println("White to move");
            } else {
                System.out.println("Black to move");
            }
            // input move
            Move move = InputCoordinates.inputMove(board, colorToMove , renderer);

            // make move
            board.makeMove(move);

            // pass move
            colorToMove = colorToMove.opposite();

            // check if game is over
            state = defineGameState(board, colorToMove);
        }
        renderer.render(board);
        System.out.println("Game was ended with " + state);
    }

    private GameState defineGameState(Board board, Color color) {
        for (GameStateChecker stateChecker : stateCheckers) {
            GameState state = stateChecker.check(board, color);
            if (state != GameState.ONGOING) {
                return state;
            }
        }
        return GameState.ONGOING;
    }
}
