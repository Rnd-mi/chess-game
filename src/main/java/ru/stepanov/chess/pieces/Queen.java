package ru.stepanov.chess.pieces;

import ru.stepanov.chess.Color;

import java.util.Set;

public class Queen extends LongRangePiece implements IBishop, IRook {
    public Queen(Color color) {
        super(color);
    }

    @Override
    protected Set<CoordinatesShift> getPieceMoves() {
        Set<CoordinatesShift> moves = getBishopMoves();
        moves.addAll(getRookMoves());
        return moves;
    }
}
