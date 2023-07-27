package ru.stepanov.chess.board;

import ru.stepanov.chess.Coordinates;
import ru.stepanov.chess.File;

import java.util.ArrayList;
import java.util.List;

public class BoardUtils {

    public static List<Coordinates> getDiagonalSquaresBetween(Coordinates source, Coordinates dest) {
        // assumption - source and dest are on the same diagonal;
        List<Coordinates> result = new ArrayList<>();

        int fileShift = dest.file.ordinal() > source.file.ordinal() ? 1 : -1;
        int rankShift = dest.rank > source.rank ? 1 : -1;

        for (
                int fileIndex = source.file.ordinal() + fileShift,
                rank = source.rank + rankShift;
                fileIndex != dest.file.ordinal() && rank != dest.rank;
                fileIndex += fileShift, rank += rankShift
        ) {
            result.add(new Coordinates(File.values()[fileIndex], rank));
        }
        return result;
    }

    public static List<Coordinates> getVerticalSquaresBetween(Coordinates source, Coordinates dest) {
        // assumption - source and dest are on the same vertical;
        List<Coordinates> result = new ArrayList<>();

        int rankShift = dest.rank > source.rank ? 1 : -1;
        File file = source.file;

        for (int rank = source.rank + rankShift; rank != dest.rank; rank += rankShift) {
            result.add(new Coordinates(file, rank));
        }
        return result;
    }

    public static List<Coordinates> getHorizontalSquaresBetween(Coordinates source, Coordinates dest) {
        // assumption - source and dest are on the same horizontal;
        List<Coordinates> result = new ArrayList<>();

        int fileShift = dest.file.ordinal() > source.file.ordinal() ? 1 : -1;
        int rank = source.rank;

        for (int fileIndex = source.file.ordinal() + fileShift; fileIndex != dest.file.ordinal(); fileIndex += fileShift) {
            result.add(new Coordinates(File.values()[fileIndex], rank));
        }
        return result;
    }
}
