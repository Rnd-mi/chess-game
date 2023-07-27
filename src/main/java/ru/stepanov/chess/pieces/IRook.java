package ru.stepanov.chess.pieces;

import java.util.HashSet;
import java.util.Set;

public interface IRook {
    default Set<CoordinatesShift> getRookMoves() {
        Set<CoordinatesShift> moves = new HashSet<>();
        //  left to right
        for (int i = -7; i <= 7; i++) {
            if (i != 0) {
                moves.add(new CoordinatesShift(i, 0));
            }
        }

        // bottom to top
        for (int i = -7; i <= 7; i++) {
            if (i != 0) {
                moves.add(new CoordinatesShift(0, i));
            }
        }
        return moves;
    }
}
