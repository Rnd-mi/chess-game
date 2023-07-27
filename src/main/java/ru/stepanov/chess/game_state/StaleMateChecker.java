package ru.stepanov.chess.game_state;

import ru.stepanov.chess.Color;
import ru.stepanov.chess.board.Board;
import ru.stepanov.chess.pieces.Piece;

import java.util.List;

public class StaleMateChecker extends GameStateChecker {
    public GameState check(Board board, Color color) {
        List<Piece> pieces = board.getPiecesByColor(color);

        for (Piece piece : pieces) {
            if (!piece.getAvailableMoves(board).isEmpty()) {
                return GameState.ONGOING;
            }
        }
        return GameState.STALEMATE;
    }
}
