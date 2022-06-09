package com.ruaridh.demo.chess;

import com.ruaridh.demo.entity.Move;

import java.util.HashSet;
import java.util.Set;

public class Rook extends Queen {

    public Rook(final Colour colour) {
        super(colour);
        _pgnSymbol = _colour == Colour.WHITE ? "R" : "r";
    }

    @Override
    public Set<Move> getLegalMoves(Board board, int row, int column) {
        return getHorizontals(board, row, column);
    }
}
