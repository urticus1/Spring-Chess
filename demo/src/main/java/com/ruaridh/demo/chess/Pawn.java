package com.ruaridh.demo.chess;

import com.ruaridh.demo.entity.Move;

import java.util.HashSet;
import java.util.Set;

public class Pawn extends Piece {


    public Pawn(final Colour colour) {
        super(colour);
        _pgnSymbol = _colour == Colour.WHITE ? "P" : "p";

    }

    @Override
    public Set<Move> getLegalMoves(Board board, int row, int column) {
        HashSet<Move> moves = new HashSet<Move>();
        moves.addAll(getVerticalMoves(board, row, column));
        moves.addAll(getCaptures(board, row, column));
        return moves;
    }

    private Set<Move> getVerticalMoves(Board board, int row, int column) {
        boolean doubleMove = (_colour == Colour.WHITE && row == 6) || (_colour == Colour.BLACK && row == 1);
        int newRow = row + (_colour == Colour.WHITE ? -1 : 1);
        HashSet<Move> moves = new HashSet<Move>();
        if (newRow < 8 && newRow >= 0) {
            if (board.getSquare(newRow, column).isEmpty()) {
                moves.add(new Move(board.getSquare(row, column), board.getSquare(newRow, column), _pgnSymbol));
                if (doubleMove && board.getSquare(newRow + (_colour == Colour.WHITE ? -1 : 1), column).isEmpty()) {
                    moves.add(new Move(board.getSquare(row, column), board.getSquare(newRow + (_colour == Colour.WHITE ? -1 : 1), column), _pgnSymbol));
                }
            }
        }
        return moves;
    }

    private Set<Move> getCaptures(Board board, int row, int column) {
        int newRow = row + (_colour == Colour.WHITE ? -1 : 1);
        HashSet<Move> moves = new HashSet<Move>();
        if (newRow < 8 && newRow >= 0) {
            if (column + 1 < 8 && !board.getSquare(newRow, column + 1).isEmpty()) {
                if (board.getPiece(newRow, column + 1)._colour != _colour) {
                    moves.add(new Move(board.getSquare(row, column), board.getSquare(newRow, column + 1), _pgnSymbol));
                }
            }
            if (column - 1 >= 0 && !board.getSquare(newRow, column - 1).isEmpty()) {
                if (board.getPiece(newRow, column - 1)._colour != _colour) {
                    moves.add(new Move(board.getSquare(row, column), board.getSquare(newRow, column - 1), _pgnSymbol));
                }
            }
        }
        return moves;
    }
}
