package ru.stepanov.chess.game_state;

import ru.stepanov.chess.Color;
import ru.stepanov.chess.Coordinates;
import ru.stepanov.chess.Move;
import ru.stepanov.chess.board.Board;
import ru.stepanov.chess.board.BoardFactory;
import ru.stepanov.chess.pieces.Piece;

import java.util.List;
import java.util.Set;

public class CheckMateChecker extends GameStateChecker {

    @Override
    public GameState check(Board board, Color color) {
        // check if king is under attack
        Piece king = board.findPiece("King", color);
        if (!board.isSquareUnderAttack(king.coordinates, color.opposite())) {
            return GameState.ONGOING;
        }

        // check if there is no move to undo the check
        List<Piece> pieces = board.getPiecesByColor(color);
        Board copy = new BoardFactory().copy(board);
        for (Piece piece : pieces) {

            Set<Coordinates> availableMoves = piece.getAvailableMoves(board);
            for (Coordinates coordinates : availableMoves) {
                Move move = new Move(piece.coordinates, coordinates);
                copy.makeMove(move);
                Piece clonedKing = copy.findPiece("King", color);

                if (!copy.isSquareUnderAttack(clonedKing.coordinates, color.opposite())) {
                    return GameState.ONGOING;
                }
                copy.undoMove(move);
            }
        }
        if (color == Color.WHITE) {
            return GameState.CHECKMATE_TO_WHITE;
        }
        return GameState.CHECKMATE_TO_BLACK;
    }
}
