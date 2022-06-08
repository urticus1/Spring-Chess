package com.ruaridh.demo.chess;

import com.ruaridh.demo.entity.Move;

import java.util.HashSet;
import java.util.Set;

public class King extends Piece {

    public King(final Colour colour) {
        super(colour);
        _pgnSymbol = _colour == Colour.WHITE ? "K" : "k";

    }

    @Override
    public Set<Move> getLegalMoves(Board board, int row, int column) {
        HashSet<Move> moves = new HashSet<Move>();
        return moves;
    }
}
