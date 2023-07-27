package ru.stepanov.chess;

import ru.stepanov.chess.board.Board;
import ru.stepanov.chess.board.BoardConsoleRender;
import ru.stepanov.chess.board.BoardFactory;
import ru.stepanov.chess.pieces.Piece;

import java.util.Scanner;
import java.util.Set;

public class InputCoordinates {

    private static final Scanner scanner = new Scanner(System.in);

    public static Coordinates input() {
        while (true) {
            System.out.println("Choose a piece");
            String input = scanner.nextLine();

            if (input.length() > 2) {
                sendMessage();
                continue;
            }

            char fileChar = input.charAt(0);
            char rankChar = input.charAt(1);

            if (!Character.isLetter(fileChar)) {
                sendMessage();
                continue;
            }

            if (!Character.isDigit(rankChar)) {
                sendMessage();
                continue;
            }
            int rank = Character.getNumericValue(rankChar);

            if (rank < 1 || rank > 8) {
                sendMessage();
                continue;
            }

            File file = File.fromChar(fileChar);
            if (file == null) {
                sendMessage();
            }
            return new Coordinates(file, rank);
        }
    }

    public static Coordinates inputCoordinatesAccordingToPiece(Color color, Board board) {
        while (true) {
            Coordinates coordinates = input();

            if (board.isSquareEmpty(coordinates)) {
                System.out.println("There is no piece in this square");
                continue;
            }

            Piece piece = board.getPiece(coordinates);
            if (!(piece.color == color)) {
                System.out.println("Wrong color");
                continue;
            }

            Set<Coordinates> availableMoves = piece.getAvailableMoves(board);

            if (availableMoves.size() == 0) {
                System.out.println("No available moves");
                continue;
            }
            System.out.println("Enter square to move the chosen piece to");
            return coordinates;
        }
    }

    public static Move inputMove(Board board,  Color color, BoardConsoleRender renderer) {

        while (true) {
            // input
            Coordinates source = InputCoordinates.inputCoordinatesAccordingToPiece(color, board);
            Piece piece = board.getPiece(source);
            renderer.render(board, piece);

            Set<Coordinates> availableMoves = piece.getAvailableMoves(board);
            Coordinates dest = InputCoordinates.getAvailableMoves(availableMoves);

            Move move = new Move(source, dest);

            if (checkIfKingUnderAttackAfterMove(board, color, move)) {
                System.out.println("Your king is under attack");
                continue;
            }

            return move;
        }
    }

    private static boolean checkIfKingUnderAttackAfterMove(Board board, Color color, Move move) {
        Board copy = new BoardFactory().copy(board);
        copy.makeMove(move);

        Piece king = copy.findPiece("King", color);
        return copy.isSquareUnderAttack(king.coordinates, color.opposite());
    }

    public static Coordinates getAvailableMoves(Set<Coordinates> moves) {
        while (true) {
            Coordinates coordinates = input();

            if (!moves.contains(coordinates)) {
                System.out.println("You cannot go there");
                continue;
            }
            return coordinates;
        }
    }

    private static void sendMessage() {
        System.out.println("Invalid format");
    }
}
