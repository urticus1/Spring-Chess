package com.ruaridh.demo.services;

import com.ruaridh.demo.entity.Game;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.util.List;


@Service
public class GameService {

    EntityManager _entityManager = Persistence.createEntityManagerFactory("Chess").createEntityManager();

    public void save(Game game) {
        _entityManager.getTransaction().begin();
        _entityManager.persist(game);
        _entityManager.getTransaction().commit();
    }

    public List<Game> findAll() {
        Session s = _entityManager.unwrap(org.hibernate.Session.class);
        Query query = s.createQuery("SELECT a FROM Game a", Game.class);
        List<Game> games = query.getResultList();
        return query.getResultList();
    }

    public Game findGame(Long id) {
        return _entityManager.find(Game.class, id);
    }
}
