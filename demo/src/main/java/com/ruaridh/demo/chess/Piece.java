package com.ruaridh.demo.chess;

import com.ruaridh.demo.entity.Move;

import java.util.Set;

public abstract class Piece {

    protected String _pgnSymbol;
    protected Colour _colour;

    public Piece(final Colour colour) {
        _colour = colour;
    }

    public abstract Set<Move> getLegalMoves(final Board board, int row, int column);

    public Colour getColour() {
        return _colour;
    }

    public enum Colour {
        WHITE, BLACK
    }

    public String getPgnSymbol() {
        return _pgnSymbol;
    }
}
