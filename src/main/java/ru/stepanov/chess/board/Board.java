package ru.stepanov.chess.board;

import ru.stepanov.chess.Color;
import ru.stepanov.chess.Coordinates;
import ru.stepanov.chess.Move;
import ru.stepanov.chess.pieces.Piece;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {
    public final String startingFen;
    private final Map<Coordinates, Piece> board = new HashMap<>();

    /**
     * List containing all moves that have been done in the game
     */
    public final List<Move> moves = new ArrayList<>();

    public Board(String startingFen) {
        this.startingFen = startingFen;
    }

    public void setPiece(Coordinates coordinates, Piece piece) {
        piece.coordinates = coordinates;
        board.put(coordinates, piece);
    }

    public void removePiece(Coordinates coordinates) {
        board.remove(coordinates);
    }

    public void makeMove(Move move) {
        setPiece(move.to, board.get(move.from));
        removePiece(move.from);
        moves.add(move);
    }

    public static boolean isSquareDark(Coordinates coordinates) {
        return (((coordinates.file.ordinal() + 1) + coordinates.rank) % 2) == 0;
    }

    public boolean isSquareEmpty(Coordinates coordinates) {
        return !board.containsKey(coordinates);
    }

    public Piece getPiece(Coordinates coordinates) {
        return board.get(coordinates);
    }

    public boolean isSquareUnderAttack(Coordinates coordinates, Color color) {
        List<Piece> pieces = getPiecesByColor(color);

        for (Piece piece : pieces) {
            if (piece.getSquaresUnderAttack(this).contains(coordinates)) {
                return true;
            }
        }
        return false;
    }

    public List<Piece> getPiecesByColor(Color color) {
        List<Piece> result = new ArrayList<>();

        for (Piece piece : board.values()) {
            if (piece.color == color) {
                result.add(piece);
            }
        }
        return result;
    }

    /**
     * This method's purpose is to find King on current board.
     * @param name
     * @param color
     * @return piece that contains coordinates
     */
    public Piece findPiece(String name, Color color) {
        List<Piece> piecesOfColor = getPiecesByColor(color);

        for (Piece piece : piecesOfColor) {
            if (piece.getClass().getSimpleName().equals(name)) {
                return piece;
            }
        }
        throw new RuntimeException();
    }

    public void undoMove(Move move) {
        setPiece(move.from, board.get(move.to));
        removePiece(move.to);
        moves.remove(moves.size() - 1);
    }
}
