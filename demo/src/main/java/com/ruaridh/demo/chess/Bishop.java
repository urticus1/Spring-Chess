package com.ruaridh.demo.chess;

import com.ruaridh.demo.entity.Move;

import java.util.HashSet;
import java.util.Set;

public class Bishop extends Queen {

    public Bishop(final Colour colour) {
        super(colour);
        _pgnSymbol = _colour == Colour.WHITE ? "B" : "b";

    }

    @Override
    public Set<Move> getLegalMoves(Board board, int row, int column) {
        return getDiagonals(board, row, column);
    }

    private Move checkSquare(int row, int column, int newRow, int newCol, Board board) {
        if (board.getPiece(newRow, newCol) == null) {
            return new Move(new Square(row, column), new Square(newRow, newCol), _pgnSymbol);
        }
        else  {
            if (board.getPiece(newRow, newCol) .getColour() != _colour) {
                return new Move(new Square(row, column), new Square(newRow, newCol), _pgnSymbol);
            }
        }
        return null;
    }
}
