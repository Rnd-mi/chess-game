package ru.stepanov.chess.pieces;

import ru.stepanov.chess.Color;

import java.util.HashSet;
import java.util.Set;

public class Knight extends Piece {
    public Knight(Color color) {
        super(color);
    }

    @Override
    protected Set<CoordinatesShift> getPieceMoves() {
        return new HashSet<>() {{
            add(new CoordinatesShift(2, 1));
            add(new CoordinatesShift(1, 2));

            add(new CoordinatesShift(2, -1));
            add(new CoordinatesShift(1, -2));

            add(new CoordinatesShift(-2, -1));
            add(new CoordinatesShift(-1, -2));

            add(new CoordinatesShift(-2, 1));
            add(new CoordinatesShift(-1, 2));
        }};
    }
}
