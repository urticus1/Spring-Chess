package com.ruaridh.demo.chess;

import com.ruaridh.demo.entity.Move;

import java.util.HashSet;
import java.util.Set;

public class Queen extends Piece {

    public Queen(final Colour colour) {
        super(colour);
        _pgnSymbol = _colour == Colour.WHITE ? "Q" : "q";
    }

    @Override
    public Set<Move> getLegalMoves(Board board, int row, int column) {
        HashSet<Move> moves = new HashSet<Move>();
        moves.addAll(getDiagonals(board, row, column));
        moves.addAll(getHorizontals(board, row, column));
        return moves;
    }

    protected Set<Move> getDiagonals(Board board, int row, int column) {
        HashSet<Move> moves = new HashSet<Move>();

        for (int i = 1; i < 8; i++) {
            int newRow = row + i;
            int newCol = column + i;
            if (newRow > 7 || newCol > 7) {
                break;
            }
            if (board.getPiece(newRow, newCol) == null) {
                moves.add(new Move(new Square(row, column), new Square(newRow, newCol), _pgnSymbol));
            }
            else  {
                if (board.getPiece(newRow, newCol) .getColour() != _colour) {
                    moves.add(new Move(new Square(row, column), new Square(newRow, newCol), _pgnSymbol));
                }
                break;
            }
        }
        for (int i = 1; i < 8; i++) {
            int newRow = row + i;
            int newCol = column - i;
            if (newRow > 7 || newCol < 0) {
                break;
            }
            if (board.getPiece(newRow, newCol) == null) {
                moves.add(new Move(new Square(row, column), new Square(newRow, newCol), _pgnSymbol));
            }
            else  {
                if (board.getPiece(newRow, newCol) .getColour() != _colour) {
                    moves.add(new Move(new Square(row, column), new Square(newRow, newCol), _pgnSymbol));
                }
                break;
            }
        }
        for (int i = 1; i < 8; i++) {
            int newRow = row - i;
            int newCol = column + i;
            if (newRow < 0 || column + i > 7) {
                break;
            }
            if (board.getPiece(newRow, newCol) == null) {
                moves.add(new Move(new Square(row, column), new Square(newRow, newCol), _pgnSymbol));
            }
            else  {
                if (board.getPiece(newRow, newCol) .getColour() != _colour) {
                    moves.add(new Move(new Square(row, column), new Square(newRow, newCol), _pgnSymbol));
                }
                break;
            }
        }
        for (int i = 1; i < 8; i++) {
            int newRow = row - i;
            int newCol = column - i;
            if (newRow < 0 || newCol  < 0) {
                break;
            }
            if (board.getPiece(newRow, newCol) == null) {
                moves.add(new Move(new Square(row, column), new Square(newRow, newCol), _pgnSymbol));
            }
            else  {
                if (board.getPiece(newRow, newCol) .getColour() != _colour) {
                    moves.add(new Move(new Square(row, column), new Square(newRow, newCol), _pgnSymbol));
                }
                break;
            }
        }
        return moves;
    }

    protected Set<Move> getHorizontals(Board board, int row, int column) {
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
