package ru.stepanov.chess.board;

import ru.stepanov.chess.Coordinates;
import ru.stepanov.chess.File;
import ru.stepanov.chess.Move;
import ru.stepanov.chess.PieceFactory;

public class BoardFactory {
    private final PieceFactory pieceFactory = new PieceFactory();

    public Board getBoardFromFEN(String fen) {
        // rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1
        Board board = new Board(fen);

        String[] parts = fen.split(" ");
        String piecePositions = parts[0];
        String[] rows = piecePositions.split("/");

        for (int i = 0; i < rows.length; i++) {
            int rank = 8 - i;
            char[] row = rows[i].toCharArray();
            int fileIndex = 0;

            for (int j = 0; j < row.length; j++) {

                if (Character.isDigit(row[j])) {
                    fileIndex += Character.getNumericValue(row[j]);
                } else {
                    File file = File.values()[fileIndex];
                    Coordinates coordinates = new Coordinates(file, rank);
                    board.setPiece(coordinates, pieceFactory.getPieceFromChar(row[j]));
                    fileIndex++;
                }
            }
        }
        return board;
    }

    public Board copy(Board board) {
        Board copy = getBoardFromFEN(board.startingFen);

        for (Move move : board.moves) {
            copy.makeMove(move);
        }
        return copy;
    }
}
