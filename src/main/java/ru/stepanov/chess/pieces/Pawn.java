package ru.stepanov.chess.pieces;

import ru.stepanov.chess.Color;
import ru.stepanov.chess.Coordinates;
import ru.stepanov.chess.board.Board;
import ru.stepanov.chess.board.BoardUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Pawn extends Piece {
    public Pawn(Color color) {
        super(color);
    }

    @Override
    protected Set<CoordinatesShift> getPieceMoves() {
        Set<CoordinatesShift> moves = new HashSet<>();

        if (color == Color.WHITE) {
            moves.add(new CoordinatesShift(0, 1));

            if (coordinates.rank == 2) {
                moves.add(new CoordinatesShift(0, 2));
            }
            moves.add(new CoordinatesShift(1, 1));
            moves.add(new CoordinatesShift(-1, 1));
        } else {
            moves.add(new CoordinatesShift(0, -1));

            if (coordinates.rank == 7) {
                moves.add(new CoordinatesShift(0, -2));
            }
            moves.add(new CoordinatesShift(1, -1));
            moves.add(new CoordinatesShift(-1, -1));
        }
        return moves;
    }

    @Override
    protected boolean isSquareAvailableForMove(Coordinates coordinates, Board board) {
        if (this.coordinates.file == coordinates.file) {
            int rankShift = Math.abs(this.coordinates.rank - coordinates.rank);
            if (rankShift == 2) {
                List<Coordinates> between = BoardUtils.getVerticalSquaresBetween(this.coordinates, coordinates);
                return board.isSquareEmpty(between.get(0)) && board.isSquareEmpty(coordinates);
            } else {
                return board.isSquareEmpty(coordinates);
            }
        } else if (!board.isSquareEmpty(coordinates)) {
            return color != board.getPiece(coordinates).color;
        }
        return false;
    }

    @Override
    protected Set<CoordinatesShift> getPieceAttacks() {
        Set<CoordinatesShift> attacks = new HashSet<>();

        if (color == Color.WHITE) {
            attacks.add(new CoordinatesShift(1, 1));
            attacks.add(new CoordinatesShift(-1, 1));
        } else {
            attacks.add(new CoordinatesShift(1, -1));
            attacks.add(new CoordinatesShift(-1, -1));
        }
        return attacks;
    }
}