package ru.stepanov.chess.pieces;

import ru.stepanov.chess.Color;

import java.util.Set;

public class Rook extends LongRangePiece implements IRook {

    public Rook(Color color) {
        super(color);
    }

    @Override
    protected Set<CoordinatesShift> getPieceMoves() {
        return getRookMoves();
    }
}
