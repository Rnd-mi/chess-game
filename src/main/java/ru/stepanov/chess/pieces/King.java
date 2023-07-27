package ru.stepanov.chess.pieces;

import ru.stepanov.chess.Color;
import ru.stepanov.chess.Coordinates;
import ru.stepanov.chess.board.Board;

import java.util.HashSet;
import java.util.Set;

public class King extends Piece {
    public King(Color color) {
        super(color);
    }

    @Override
    protected Set<CoordinatesShift> getPieceMoves() {
        Set<CoordinatesShift> moves = new HashSet<>();

        for (int fileShift = -1; fileShift <= 1; fileShift++) {
            for (int rankShift = -1; rankShift <= 1; rankShift++) {
                if (fileShift == 0 && rankShift == 0) {
                    continue;
                }
                moves.add(new CoordinatesShift(fileShift, rankShift));
            }
        }
        return moves;
    }

    @Override
    protected boolean isSquareAvailableForMove(Coordinates coordinates, Board board) {
        boolean result = super.isSquareAvailableForMove(coordinates, board);

        if (result) {
            // check if square is under attack, if so return false
            return !board.isSquareUnderAttack(coordinates, color.opposite());
        }
        return false;
    }
}
