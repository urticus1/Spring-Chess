package com.ruaridh.demo.chess;

import com.ruaridh.demo.entity.Move;

import java.util.HashSet;
import java.util.Set;

public class Rook extends Piece {

    public Rook(final Colour colour) {
        super(colour);
        _pgnSymbol = _colour == Colour.WHITE ? "R" : "r";
    }

    @Override
    public Set<Move> getLegalMoves(Board board, int row, int column) {
        HashSet<Move> moves = new HashSet<Move>();

        //vertical column up
        for (int i = row + 1; i < 8; i++) {
            if (board.getSquare(i, column).isEmpty()) {
                moves.add(new Move(new Square(row, column), new Square(i, column), _pgnSymbol));
                continue;
            }
            if (board.getPiece(i, column).getColour() != _colour) {
                moves.add(new Move(new Square(row, column), new Square(i, column), _pgnSymbol));
                break;
            }
            break;
        }

        //vertical column down
        for (int i = row - 1; i >= 0; i--) {
            if (board.getSquare(i, column).isEmpty()) {
                moves.add(new Move(new Square(row, column), new Square(i, column), _pgnSymbol));
                continue;
            }
            if (board.getPiece(i, column).getColour() != _colour) {
                moves.add(new Move(new Square(row, column), new Square(i, column), _pgnSymbol));
                break;
            }
            break;
        }

        //horizontal row right
        for (int i = column + 1; i < 8; i++) {
            if (board.getSquare(row, i).isEmpty()) {
                moves.add(new Move(new Square(row, column), new Square(row, i), _pgnSymbol));
                continue;
            }
            if (board.getPiece(row, i).getColour() != _colour) {
                moves.add(new Move(new Square(row, column), new Square(row, i), _pgnSymbol));
                break;
            }
            break;
        }

        //horizontal row left
        for (int i = column - 1; i >= 0; i--) {
            if (board.getSquare(row, i).isEmpty()) {
                moves.add(new Move(new Square(row, column), new Square(row, i), _pgnSymbol));
                continue;
            }
            if (board.getPiece(row, i).getColour() != _colour) {
                moves.add(new Move(new Square(row, column), new Square(row, i), _pgnSymbol));
                break;
            }
            break;
        }
        return moves;
    }
}
