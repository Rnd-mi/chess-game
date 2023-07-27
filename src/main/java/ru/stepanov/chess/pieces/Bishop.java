package ru.stepanov.chess.pieces;

import ru.stepanov.chess.Color;

import java.util.Set;

public class Bishop extends LongRangePiece implements IBishop {
    public Bishop(Color color) {
        super(color);
    }

    @Override
    protected Set<CoordinatesShift> getPieceMoves() {
        return getBishopMoves();
    }
}
