package com.ruaridh.demo.controllers;

import com.ruaridh.demo.entity.Player;
import com.ruaridh.demo.requests.LoginRequest;
import com.ruaridh.demo.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthenticationController {

    @Autowired
    private JwtUtils _jwtParser;

    @Autowired
    private PlayerService _playerService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> createLoginToken(@RequestBody LoginRequest data) {
      Player player = _playerService.findPlayer(data.getUsername());
      if (player == null) {
          return ResponseEntity.badRequest().build();
      }
      if (data.getPassword().equals(player.getPassword())) {
          String token = _jwtParser.generateToken(player);
          return ResponseEntity.accepted().body(Optional.of(token));
      }
      else {
          return ResponseEntity.badRequest().build();
      }
    }
}
