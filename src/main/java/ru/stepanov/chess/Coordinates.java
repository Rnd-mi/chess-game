package ru.stepanov.chess;

import ru.stepanov.chess.pieces.CoordinatesShift;

import java.util.Objects;

public class Coordinates {
    public final File file;
    public final Integer rank;

    public Coordinates(File file, Integer rank) {
        this.file = file;
        this.rank = rank;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return file == that.file && rank.equals(that.rank);
    }

    @Override
    public int hashCode() {
        return Objects.hash(file, rank);
    }

    public boolean canShift(CoordinatesShift shift) {
        int f = file.ordinal() + shift.fileShift;
        int r = rank + shift.rankShift;

        if (f < 0 || f > 7) return false;
        if (r < 1 || r > 8) return false;

        return true;
    }

    public Coordinates shift(CoordinatesShift shift) {
        return new Coordinates(File.values()[file.ordinal() + shift.fileShift], rank + shift.rankShift);
    }

    @Override
    public String toString() {
        return file + "" + rank;
    }
}
