package com.ruaridh.demo.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ruaridh.demo.GameFactory;
import com.ruaridh.demo.GameNotFoundException;
import com.ruaridh.demo.chess.Piece;
import com.ruaridh.demo.chess.Square;
import com.ruaridh.demo.entity.Game;
import com.ruaridh.demo.entity.Move;
import com.ruaridh.demo.requests.MakeMoveRequest;
import io.jsonwebtoken.ClaimJwtException;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping(value = "/move")
@CrossOrigin(origins = "http://localhost:4200")
public class MoveController {

    @Autowired
    private GameFactory _gameFactory;

    @Autowired
    private JwtUtils _jwtParser;

    @RequestMapping(value = "/makeMove", method = RequestMethod.POST)
    public ResponseEntity<?> makeMove(@RequestBody MakeMoveRequest body) {
        try {
            Long id = body.getGameId();
            String token = body.getToken();
            final Move move = body.getMove();
            Game game = _gameFactory.getGame(id);
            String tokenUser = _jwtParser.getPlayerName(token);
            if (tokenUser == null || !game.hasPlayer(tokenUser) || !game.isPlayersTurn(tokenUser)) {
                return ResponseEntity.of(Optional.of(false));
            }
            int row = move.getStartSquare().getRow();
            int col = move.getStartSquare().getColumn();
            if (game.getLegalMoves(move.getStartSquare()).contains(move) && game.getTurn().equals(game.getBoard().getPiece(row, col).getColour())) {
                game.makeMove(move);
                _gameFactory.saveGame(game);
                return ResponseEntity.of(Optional.of(true));
            }
            return ResponseEntity.of(Optional.of(false));
        }
        catch (GameNotFoundException | ClaimJwtException e) {
            return ResponseEntity.of(Optional.of(false));
        }
    }

    @RequestMapping(value = "/legalMoves", method = RequestMethod.GET)
    public Set<Move> getLegalMoves(@RequestParam String id, Square square) {
        try {
            Game game = _gameFactory.getGame(Long.valueOf(id));
            Piece.Colour turn = game.getTurn();
            Piece piece = game.getBoard().getPiece(square.getRow(),square.getColumn());
            if (piece != null && turn.equals(piece.getColour())) {
                return _gameFactory.getGame(Long.valueOf(id)).getLegalMoves(square);
            }
            else {
                return new HashSet<>();
            }
        }
        catch (GameNotFoundException e) {
            return null;
        }
    }

    private boolean correctTurn(Game game, Piece.Colour colour) {
        return game.getTurn().equals(colour);
    }
}
