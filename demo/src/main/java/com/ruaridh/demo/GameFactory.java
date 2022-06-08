package com.ruaridh.demo;

import com.ruaridh.demo.chess.*;
import com.ruaridh.demo.entity.Game;
import com.ruaridh.demo.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class GameFactory {

    @Autowired
    private GameService _gameRepository;
    private static final Map<Long, Game> _activeGames = new HashMap<>();
    private static String STANDARD_FEN = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR";

    private GameFactory() {

    }

    public List<Game> getAllGames() {
        return  _gameRepository.findAll();
    }

    public Game createGame(final String fen) throws InvalidFENException {

        Board board = fen == null ? loadFromFEN(STANDARD_FEN) : loadFromFEN(fen);
        Game game = new Game(board);
        _gameRepository.save(game);
        _activeGames.put(game.getId(), game);
        return game;
    }

    private static Piece createPiece(char symbol) throws IllegalArgumentException {
        switch (symbol) {
            case 'p':
                return new Pawn(Piece.Colour.WHITE);
            case 'P':
                return new Pawn(Piece.Colour.BLACK);
            case 'r':
                return new Rook(Piece.Colour.WHITE);
            case 'R':
                return new Rook(Piece.Colour.BLACK);
            case 'n':
                return new Knight(Piece.Colour.WHITE);
            case 'N':
                return new Knight(Piece.Colour.BLACK);
            case 'b':
                return new Bishop(Piece.Colour.WHITE);
            case 'B':
                return new Bishop(Piece.Colour.BLACK);
            case 'q':
                return new Queen(Piece.Colour.WHITE);
            case 'Q':
                return new Queen(Piece.Colour.BLACK);
            case 'k':
                return new King(Piece.Colour.WHITE);
            case 'K':
                return new King(Piece.Colour.BLACK);
            default:
                throw new IllegalArgumentException("invalid FEN character encountered");
        }
    }

    private static Board loadFromFEN(final byte[] fenBytes) throws InvalidFENException {
        Square[][] board = new Square[8][8];
        int i = 0;
        int j = 0;
        try {
            for (byte byteValue : fenBytes) {
                if (j > 7) {
                    i++;
                    j = 0;
                }
                char symbol = (char) byteValue;
                if (symbol == '/') {
                    continue;
                }
                if (Character.isDigit(symbol)) {
                    int digit = Integer.valueOf(String.valueOf(symbol));
                    int numberToSkip = j + digit;
                    while (j < numberToSkip) {
                        board[7-i][j] = new Square(7-i, j);
                        j++;
                    }
                }
                else {
                    board[7-i][j] = new Square(7-i, j);
                    board[7-i][j].setPiece(createPiece(symbol));
                    j++;
                }
            }
        }
        catch (IndexOutOfBoundsException | IllegalArgumentException e) {
            throw new InvalidFENException();
        }
        return new Board(board);
    }

    public Game getGame(final Long id) throws GameNotFoundException {
        if (_activeGames.containsKey(id)) {
            return _activeGames.get(id);
        }
        else {
            Game game = _gameRepository.findGame(id);
            _activeGames.put(game.getId(), game);
            return game;
        }
    }

    public static Board loadFromFEN(String fen) throws InvalidFENException {
        System.out.println("loading from " + fen);
        return loadFromFEN(fen.trim().getBytes(StandardCharsets.UTF_8));
    }

    public void saveGame(Game game) {
        _gameRepository.save(game);
    }
}
