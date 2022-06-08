package com.ruaridh.demo.chess;

import com.ruaridh.demo.entity.Move;

import java.util.HashSet;
import java.util.Set;

public class Knight extends Piece {

    private static final int[][] OFFSETS = {
            {-2, 1},
            {-2, -1},
            {-1, 2},
            {1,2},
            {2, 1},
            {2, -1},
            {1, -2},
            {-1, -2}
    };

    public Knight(final Colour colour) {
        super(colour);
        _pgnSymbol = _colour == Colour.WHITE ? "N" : "n";
    }

    @Override
    public Set<Move> getLegalMoves(Board board, int row, int column) {
        HashSet<Move> moves = new HashSet<Move>();

        for (int[] offset : OFFSETS) {
            if (row + offset[0] < 8 && row + offset[0] >= 0 && column + offset[1] < 8 && column + offset[1] >= 0) {
                Piece targetSquarePiece = board.getPiece(row + offset[0], column + offset[1]);
                if (targetSquarePiece == null || targetSquarePiece.getColour() != this.getColour()) {
                    moves.add(new Move(board.getSquare(row, column), board.getSquare(row + offset[0], column + offset[1]), _pgnSymbol));
                }
            }
        }
        return moves;
    }
}
