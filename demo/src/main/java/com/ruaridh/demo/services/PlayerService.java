package com.ruaridh.demo.services;

import com.ruaridh.demo.entity.Game;
import com.ruaridh.demo.entity.Player;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;


@Service
public class PlayerService {

    EntityManager _entityManager = Persistence.createEntityManagerFactory("Chess").createEntityManager();

    public void addNewPlayer(Player player) {
        _entityManager.getTransaction().begin();
        _entityManager.persist(player);
        _entityManager.getTransaction().commit();
    }

    public Player findPlayer(String username) {
        return _entityManager.find(Player.class, username);
    }

    public boolean playerExists(String username) {
        return _entityManager.find(Player.class, username) != null;
    }
}
