package ru.stepanov.chess;

import ru.stepanov.chess.pieces.*;

import static ru.stepanov.chess.Color.BLACK;
import static ru.stepanov.chess.Color.WHITE;

public class PieceFactory {

    public Piece getPieceFromChar(char c) {
        Color color;
        if (Character.isUpperCase(c)) {
            color = WHITE;
        } else {
            color = BLACK;
        }
        c = Character.toLowerCase(c);

        switch (c) {
            case 'p':
                return new Pawn(color);
            case 'r':
                return new Rook(color);
            case 'n':
                return new Knight(color);
            case 'b':
                return new Bishop(color);
            case 'q':
                return new Queen(color);
            default:
                return new King(color);
        }
    }
}
