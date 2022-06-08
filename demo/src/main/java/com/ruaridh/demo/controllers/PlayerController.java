package com.ruaridh.demo.controllers;

import com.ruaridh.demo.entity.Player;
import com.ruaridh.demo.requests.LoginRequest;
import com.ruaridh.demo.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.function.Predicate;

@RestController
@RequestMapping(value = "/users")
@CrossOrigin(origins = "http://localhost:4200")
public class PlayerController {
    @Autowired
    private PlayerService _playerService;

    @RequestMapping(value = "/newUser", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> createUser(@RequestBody LoginRequest data) {
        System.out.println(data.getUsername());
        if (passwordInvalid(data.getPassword()))
        {
            return ResponseEntity.badRequest().body(Optional.of("Invalid password"));
        }
        if (_playerService.playerExists(data.getUsername())) {
            return ResponseEntity.badRequest().body(Optional.of("This user already exists"));
        }
        Player player = new Player(data.getUsername(), data.getPassword());
        _playerService.addNewPlayer(player);
        return ResponseEntity.ok().build();
    }

    private boolean passwordInvalid(String password) {
        return password == null || "".equals(password);
    }
}
