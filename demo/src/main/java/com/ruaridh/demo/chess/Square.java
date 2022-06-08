package com.ruaridh.demo.chess;

import java.util.Objects;

public class Square {

    private Piece _piece;
    private int _row;
    private int _column;


    public Square(int row, int column) {
        _row = row;
        _column = column;
    }

    public Piece getPiece() {
        return _piece;
    }

    public void setPiece(final Piece piece) {
        _piece = piece;
    }

    public boolean isEmpty() {
        return _piece == null;
    }

    public int getRow() {
        return _row;
    }

    public int getColumn() {
        return _column;
    }

    public void setRow(final int row) {
        _row = row;
    }

    public void setColumn(final int col) {
        _column = col;
    }

    public String getPGNString() {
        return (char) (_column + 97) + String.valueOf(_row + 1);
    }

    @Override
    public String toString() {
        return _row + " || " + _column;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Square square = (Square) o;
        return _row == square._row && _column == square._column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(_row, _column);
    }
}
