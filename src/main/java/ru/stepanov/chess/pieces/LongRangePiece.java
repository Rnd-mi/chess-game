package ru.stepanov.chess.pieces;

import ru.stepanov.chess.Color;
import ru.stepanov.chess.Coordinates;
import ru.stepanov.chess.board.Board;
import ru.stepanov.chess.board.BoardUtils;

import java.util.List;

public abstract class LongRangePiece extends Piece {

    public LongRangePiece(Color color) {
        super(color);
    }

    @Override
    protected boolean isSquareAvailableForMove(Coordinates coordinates, Board board) {
        boolean result = super.isSquareAvailableForMove(coordinates, board);

        if (result) {
            return isSquareAvailableForAttack(coordinates, board);
        } else {
            return false;
        }
    }

    @Override
    protected boolean isSquareAvailableForAttack(Coordinates coordinates, Board board) {
            List<Coordinates> squaresBetween;

            if (this.coordinates.file == coordinates.file) {
                squaresBetween = BoardUtils.getVerticalSquaresBetween(this.coordinates, coordinates);
            } else if (this.coordinates.rank.equals(coordinates.rank)){
                squaresBetween = BoardUtils.getHorizontalSquaresBetween(this.coordinates, coordinates);
            } else {
                squaresBetween = BoardUtils.getDiagonalSquaresBetween(this.coordinates, coordinates);
            }

            for (Coordinates c : squaresBetween) {
                if (!board.isSquareEmpty(c)) {
                    return false;
                }
            }
            return true;
    }
}
