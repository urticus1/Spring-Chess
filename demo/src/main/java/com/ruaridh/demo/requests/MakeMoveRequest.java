package com.ruaridh.demo.requests;

import com.ruaridh.demo.entity.Move;

import java.io.Serializable;

public class MakeMoveRequest implements Serializable {

    private Long _gameId;
    private String _token;
    private Move _move;

    public Long getGameId() {
        return _gameId;
    }

    public String getToken() {
        return _token;
    }

    public Move getMove() {
        return _move;
    }

    public void setGameId(Long id) {
        _gameId = id;
    }

    public void setToken(String token) {
        _token = token;
    }

    public void setMove(final Move move) {
        _move = move;
    }
}
