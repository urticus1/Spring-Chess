package com.ruaridh.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "players")
public class Player {

    @Id
    private String _name;

    @Column(name = "password", nullable = false)
    private String _password;

    public Player(final String name, final String password) {
        _name = name;
        _password = password;
    }

    public Player() {

    }

    public String getName() {
        return _name;
    }

    public String getPassword() {
        return _password;
    }
}
