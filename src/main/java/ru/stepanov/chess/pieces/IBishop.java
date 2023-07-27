package ru.stepanov.chess.pieces;

import java.util.HashSet;
import java.util.Set;

public interface IBishop {
    default Set<CoordinatesShift> getBishopMoves() {
        Set<CoordinatesShift> moves = new HashSet<>();
        // bottom-left to top-right
        for (int i = -7; i <= 7; i++) {
            if (i != 0) {
                moves.add(new CoordinatesShift(i, i));
            }
        }

        // top-left to bottom-right
        for (int i = -7; i <= 7; i++) {
            if (i != 0) {
                moves.add(new CoordinatesShift(i, -i));
            }
        }
        return moves;
    }
}
