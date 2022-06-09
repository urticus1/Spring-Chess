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

        if (checkSquare(row + 1, column, board)) {
            moves.add(new Move(new Square(row, column), new Square(row + 1, column), _pgnSymbol));
        }
        if (checkSquare(row + 1, column + 1, board)) {
            moves.add(new Move(new Square(row, column), new Square(row + 1, column + 1), _pgnSymbol));
        }
        if (checkSquare(row, column + 1, board)) {
            moves.add(new Move(new Square(row, column), new Square(row, column + 1), _pgnSymbol));
        }
        if (checkSquare(row - 1, column + 1, board)) {
            moves.add(new Move(new Square(row, column), new Square(row - 1, column + 1), _pgnSymbol));
        }
        if (checkSquare(row - 1, column, board)) {
            moves.add(new Move(new Square(row, column), new Square(row - 1, column), _pgnSymbol));
        }
        if (checkSquare(row - 1, column - 1, board)) {
            moves.add(new Move(new Square(row, column), new Square(row - 1, column - 1), _pgnSymbol));
        }
        if (checkSquare(row, column - 1, board)) {
            moves.add(new Move(new Square(row, column), new Square(row, column - 1), _pgnSymbol));
        }
        if (checkSquare(row + 1, column - 1, board)) {
            moves.add(new Move(new Square(row, column), new Square(row + 1, column - 1), _pgnSymbol));
        }
        return moves;
    }

    private boolean checkSquare(int row, int column, Board board) {
        if (row > 7 || row < 0 || column > 7 || column < 0) {
            return false;
        }
        return board.getPiece(row, column) == null || board.getPiece(row, column).getColour() != _colour;
    }
}
