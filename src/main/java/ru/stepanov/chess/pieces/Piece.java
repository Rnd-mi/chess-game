package ru.stepanov.chess.pieces;

import ru.stepanov.chess.board.Board;
import ru.stepanov.chess.Color;
import ru.stepanov.chess.Coordinates;

import java.util.HashSet;
import java.util.Set;

public abstract class Piece {
    public final Color color;
    public Coordinates coordinates;

    public Piece(Color color, Coordinates coordinates) {
        this.color = color;
        this.coordinates = coordinates;
    }

    public Piece(Color color) {
        this.color = color;
    }

    public Set<Coordinates> getAvailableMoves(Board board) {
        Set<Coordinates> result = new HashSet<>();
        // getting all possible moves
        for (CoordinatesShift shift : getPieceMoves()) {
            // check if shift is inside the board
            if (coordinates.canShift(shift)) {
                Coordinates newCoordinates = coordinates.shift(shift);
                // check if move is valid
                if (isSquareAvailableForMove(newCoordinates, board)) {
                    result.add(newCoordinates);
                }
            }
        }
        return result;
    }

    protected boolean isSquareAvailableForMove(Coordinates coordinates, Board board) {
        return board.isSquareEmpty(coordinates) || board.getPiece(coordinates).color != color;
    }

    protected abstract Set<CoordinatesShift> getPieceMoves();
    protected Set<CoordinatesShift> getPieceAttacks() {
        return getPieceMoves();
    }

    public Set<Coordinates> getSquaresUnderAttack(Board board) {
        Set<Coordinates> result = new HashSet<>();
        // getting all possible moves
        for (CoordinatesShift shift : getPieceAttacks()) {
            // check if shift is inside the board
            if (coordinates.canShift(shift)) {
                Coordinates newCoordinates = coordinates.shift(shift);
                // check if square can be attacked
                if (isSquareAvailableForAttack(newCoordinates, board)) {
                    result.add(newCoordinates);
                }
            }
        }
        return result;
    }

    protected boolean isSquareAvailableForAttack(Coordinates coordinates, Board board) {
        return true;
    }
}
