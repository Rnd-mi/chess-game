package ru.stepanov.chess.board;

import ru.stepanov.chess.Color;
import ru.stepanov.chess.Coordinates;
import ru.stepanov.chess.File;
import ru.stepanov.chess.pieces.Piece;

import java.util.Set;

import static java.util.Collections.emptySet;
import static ru.stepanov.chess.board.Board.isSquareDark;

public class BoardConsoleRender {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_WHITE_PIECE_COLOR = "\u001B[97m";
    public static final String ANSI_BLACK_PIECE_COLOR = "\u001B[30m";

    public static final String ANSI_WHITE_SQUARE_BACKGROUND = "\u001B[47m";

    public static final String ANSI_BLACK_SQUARE_BACKGROUND = "\u001B[0;100m";

    public static final String ANSI_HIGHLIGHTED_SQUARE_BACKGROUND = "\u001B[45m";

    public void render(Board board, Piece pieceToMove) {
        Set<Coordinates> availableMoves = emptySet();
        if (pieceToMove != null) {
            availableMoves = pieceToMove.getAvailableMoves(board);
        }

        for (int i = 8; i >= 1; i--) {
            StringBuilder builder = new StringBuilder();

            for (File file : File.values()) {
                Coordinates curr = new Coordinates(file, i);
                boolean isHighlighted = availableMoves.contains(curr);

                if (board.isSquareEmpty(curr)) {
                    builder.append(getSpriteForEmptySquare(curr, isHighlighted));
                } else {
                    builder.append(getPieceSprite(board.getPiece(curr), isHighlighted));
                }
            }
            builder.append(ANSI_RESET);
            System.out.println(builder);
        }
    }

    public void render(Board board) {
        render(board, null);
    }

    private String colorizeSprite(String sprite, Color pieceColor, boolean isSquareDark, boolean isHighlighted) {
        // format = background color + font color + text
        String result = sprite;

        if (pieceColor == Color.WHITE) {
            result = ANSI_WHITE_PIECE_COLOR + result;
        } else {
            result = ANSI_BLACK_PIECE_COLOR + result;
        }

        if (isHighlighted) {
            result = ANSI_HIGHLIGHTED_SQUARE_BACKGROUND + result;
        } else if (isSquareDark) {
            result = ANSI_BLACK_SQUARE_BACKGROUND + result;
        } else {
            result = ANSI_WHITE_SQUARE_BACKGROUND + result;
        }
        return result;
    }

    private String getSpriteForEmptySquare(Coordinates coordinates, boolean isHighlighted) {
        return colorizeSprite(" \t", Color.WHITE, isSquareDark(coordinates), isHighlighted);
    }

    private String selectPieceSprite(Piece piece) {
        switch (piece.getClass().getSimpleName()) {
            case "Pawn":
                return "♙";
            case "Knight":
                return "♘";
            case "King":
                return "♔";
            case "Rook":
                return "♖";
            case "Queen":
                return "♕";
            default:
                return "♗";
        }
    }

    private String getPieceSprite(Piece piece, boolean isHighlighted) {
        return colorizeSprite(" " + selectPieceSprite(piece) + "\t", piece.color, isSquareDark(piece.coordinates), isHighlighted);
    }
}
