package com.ruaridh.demo.chess;

import com.ruaridh.demo.entity.Move;

import java.util.HashSet;
import java.util.Set;

public class Bishop extends Piece {

    public Bishop(final Colour colour) {
        super(colour);
        _pgnSymbol = _colour == Colour.WHITE ? "B" : "b";

    }

    @Override
    public Set<Move> getLegalMoves(Board board, int row, int column) {
        HashSet<Move> moves = new HashSet<Move>();

        int i = row + 1;
        int j = column + 1;
        while (i < 8 && j < 8) {
            if (board.getPiece(i, j) == null) {
                moves.add(new Move(new Square(row, column), new Square(i, j), _pgnSymbol));
                i++;
                j++;
            }
            else if (board.getPiece(i, j) .getColour() != _colour) {
                moves.add(new Move(new Square(row, column), new Square(i, j), _pgnSymbol));
                break;
            }
        }

        i = row + 1;
        j = column - 1;
        while (i < 8 && j >= 0) {
            if (board.getPiece(i, j) == null) {
                moves.add(new Move(new Square(row, column), new Square(i, j), _pgnSymbol));
                i++;
                j--;
            }
            else if (board.getPiece(i, j) .getColour() != _colour) {
                moves.add(new Move(new Square(row, column), new Square(i, j), _pgnSymbol));
                break;
            }
        }

        i = row - 1;
        j = column + 1;
        while (i >= 0 && j < 8) {
            if (board.getPiece(i, j) == null) {
                moves.add(new Move(new Square(row, column), new Square(i, j), _pgnSymbol));
                i--;
                j++;
            }
            else if (board.getPiece(i, j) .getColour() != _colour) {
                moves.add(new Move(new Square(row, column), new Square(i, j), _pgnSymbol));
                break;
            }
        }

        i = row - 1;
        j = column - 1;
        while (i >= 0 && j >= 8) {
            if (board.getPiece(i, j) == null) {
                moves.add(new Move(new Square(row, column), new Square(i, j), _pgnSymbol));
                i--;
                j--;
            }
            else if (board.getPiece(i, j) .getColour() != _colour) {
                moves.add(new Move(new Square(row, column), new Square(i, j), _pgnSymbol));
                break;
            }
        }

        return moves;
    }
}
