package com.ruaridh.demo.controllers;

import com.ruaridh.demo.GameNotFoundException;
import com.ruaridh.demo.chess.InvalidFENException;
import com.ruaridh.demo.chess.Piece;
import com.ruaridh.demo.entity.Game;
import com.ruaridh.demo.GameFactory;
import com.ruaridh.demo.chess.Square;
import com.ruaridh.demo.entity.Player;
import com.ruaridh.demo.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(value = "/games")
@CrossOrigin(origins = "http://localhost:4200")
public class GameController {

    @Autowired
    private GameFactory _gameFactory;

    @Autowired
    private PlayerService _playerService;

    @Autowired
    private JwtUtils _jwtParser;


    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<Game> getGames() {
        return _gameFactory.getAllGames();
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public boolean create(@RequestBody(required = false) String fen) {
        try {
            _gameFactory.createGame(fen);
            return true;
        }
        catch (InvalidFENException e) {
            return false;
        }
    }

    @RequestMapping(value = "/game", method = RequestMethod.GET)
    @ResponseBody
    public Game getGame(@RequestParam String id) {
        try {
            Game game = _gameFactory.getGame(Long.valueOf(id));
            if (game != null) {
                return game;
            }
            else {
                throw new RuntimeException();
            }
        }
        catch (GameNotFoundException e) {
            return null;
        }
    }

    @RequestMapping(value = "/join", method = RequestMethod.POST)
    @ResponseBody
    public boolean joinGame(@RequestBody Map<String, Object> data) {
        try {
            Long gameId = Long.valueOf((Integer) data.get("gameId"));
            String token = (String) data.get("token");
            boolean isWhite = (boolean) data.get("isWhite");
            Game game = _gameFactory.getGame(Long.valueOf(gameId));
            String playerName = _jwtParser.getPlayerName(token);
            Player player = _playerService.findPlayer(playerName);
            if (player == null) {
                return false;
            }
            if (game.canJoin(playerName, isWhite)) {
                game.addPlayer(player, isWhite ? Piece.Colour.WHITE : Piece.Colour.BLACK);
                _gameFactory.saveGame(game);
                return true;
            }
            else {
                return false;
            }
        }
        catch (GameNotFoundException e) {
            return false;
        }
    }

}