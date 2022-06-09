package com.ruaridh.demo.entity;

import com.ruaridh.demo.GameFactory;
import com.ruaridh.demo.chess.Board;
import com.ruaridh.demo.chess.Piece;
import com.ruaridh.demo.chess.Square;
import com.ruaridh.demo.db.converters.BoardConverter;
import com.ruaridh.demo.db.converters.ColourConverter;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "games")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long _id;

    @Column(name="whitePlayer")
    private String _playerWhite;

    @Column(name="blackPlayer")
    private String _playerBlack;

    @Convert(converter = BoardConverter.class)
    private Board _board;

    @Column(name = "turn")
    @Convert(converter = ColourConverter.class)
    private Piece.Colour _turn = Piece.Colour.WHITE;

    public Game() {

    }

    public Game(Board board) {
        _board = board;
    }

    public Board getBoard() {
        return _board;
    }

    public Long getId() {
        return _id;
    }

    public Piece.Colour getTurn() {
        return _turn;
    }

    public boolean addPlayer(Player player, Piece.Colour colour) {
        if (colour == Piece.Colour.BLACK && _playerBlack == null) {
            _playerBlack = player.getName();
            return true;
        }
        else if (colour == Piece.Colour.WHITE && _playerWhite == null) {
            _playerWhite = player.getName();
            return true;
        }
        return false;
    }

    public String getPlayerWhite() {
        return _playerWhite;
    }

    public String getPlayerBlack() {
        return _playerBlack;
    }

    public Set<Move> getLegalMoves(final Square square) {
        Square selectedSquare = _board.getSquare(square.getRow(), square.getColumn());
        if (selectedSquare.isEmpty()) {
            return new HashSet<>();
        }
        return selectedSquare.getPiece().getLegalMoves(_board, square.getRow(), square.getColumn());
    }

    public void makeMove(final Move move) {
        Square start = move.getStartSquare();
        Square end = move.getEndSquare();
        _board.getSquare(end.getRow(), end.getColumn()).setPiece(_board.getSquare(start.getRow(), start.getColumn()).getPiece());
        _board.getSquare(start.getRow(), start.getColumn()).setPiece(null);
        _turn = _turn == Piece.Colour.WHITE ? Piece.Colour.BLACK : Piece.Colour.WHITE;
    }

    public boolean hasPlayer(String playerName) {
        return playerName.equals(_playerWhite) || playerName.equals(_playerBlack);
    }

    public boolean isPlayersTurn(String playerName) {
        return _turn == Piece.Colour.WHITE ? playerName.equals(_playerWhite) : playerName.equals(_playerBlack);
    }

    public boolean canJoin(String playerName, boolean white) {
        return !hasPlayer(playerName) && white ? _playerWhite == null : _playerBlack == null;
    }
}
