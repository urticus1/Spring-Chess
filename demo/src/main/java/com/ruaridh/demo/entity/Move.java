package com.ruaridh.demo.entity;

import com.ruaridh.demo.chess.Square;

import java.util.Map;
import java.util.Objects;


public class Move {

    private Square _startSquare;
    private Square _endSquare;
    private String _piece;

    public Move (final Square startSquare, final Square endSquare, final String piece) {
        _startSquare = startSquare;
        _endSquare = endSquare;
        _piece = piece;
    }

    public Square getStartSquare() {
        return _startSquare;
    }

    public Square getEndSquare() {
        return _endSquare;
    }

    public String getPiece() {
        return _piece;
    }

    public void setStartSquare(final Square start) {
        _startSquare = start;
    }

    public void setEndSquare(final Square end) {
        _endSquare = end;
    }

    public void setPiece(final String piece) {
        _piece = piece;
    }

    @Override
    public String toString() {
        return _startSquare.toString() + " :: " + _endSquare.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Move move = (Move) o;
        return _startSquare.equals(move._startSquare) && _endSquare.equals(move._endSquare);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_startSquare, _endSquare);
    }

    public String getPGNString() {
        return _piece.toUpperCase().equals("P") ? "" : _piece.toUpperCase() + _endSquare.getPGNString();
    }

}

