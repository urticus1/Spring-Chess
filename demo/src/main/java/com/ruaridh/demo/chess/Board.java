package com.ruaridh.demo.chess;

public class Board {

    private Square[][] _squares;

    public Board (final Square[][] squares) {
        _squares = squares;
    }

    public String toFEN() {
        StringBuilder builder = new StringBuilder();
        int skip = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (_squares[i][j].getPiece() == null) {
                    skip++;
                }
                else {
                    builder.append((skip > 0 ? String.valueOf(skip) : "") + _squares[i][j].getPiece().getPgnSymbol());
                    skip = 0;
                }
            }
            builder.append((skip > 0 ? String.valueOf(skip) : "") + "/");
            skip = 0;
        }
        return builder.toString();
    }

    public Square[][] getSquares() {
        return _squares;
    }

    public Square getSquare(int row, int column) {
        return _squares[row][column];
    }

    public Piece getPiece(int row, int column) {
        return _squares[row][column].getPiece();
    }
}
