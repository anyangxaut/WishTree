package models.dao;


import models.entity.Card;
import play.db.jpa.JPAApi;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public class CardDao {

    JPAApi api;

    @Inject
    public CardDao(JPAApi api) {
        this.api = api;
    }

    public List<Card> getAllCards() {
        EntityManager entityManager = api.em();
        List<Card> resultList = entityManager
                .createQuery("SELECT c FROM Card c")
                .getResultList();
        return resultList;
    }

    public Object getCardWithId(Integer id) {
        EntityManager entityManager = api.em();
        Object resultObject = entityManager
                .createQuery("SELECT c FROM Card c WHERE c.id = :customId")
                .setParameter("customId", id)
                .getSingleResult();
        return resultObject;
    }

    public Object createCard(Card card) {
        EntityManager entityManager = api.em();
        Object number = entityManager
                .createNativeQuery("INSERT INTO card (name, summary, thumbnail, type, score) VALUES (?, ?, ?, ?, ?)")
                .setParameter(1, card.getName())
                .setParameter(2, card.getSummary())
                .setParameter(3, card.getThumbnail())
                .setParameter(4, card.getType())
                .setParameter(5, card.getScore())
                .executeUpdate();
        return number;
    }

    public Object deleteCard(Integer id) {
        EntityManager entityManager = api.em();
        Object number = entityManager
                .createQuery("DELETE From Card c WHERE c.id = :customId")
                .setParameter("customId", id)
                .executeUpdate();
        return number;
    }

    public Object updateCard(Card card) {
        EntityManager entityManager = api.em();
        Object number = entityManager
                .createNativeQuery("UPDATE card SET name = ?, summary = ?, thumbnail = ?, type = ?, " +
                        "score = ? WHERE id = ?")
                .setParameter(1, card.getName())
                .setParameter(2, card.getSummary())
                .setParameter(3, card.getThumbnail())
                .setParameter(4, card.getType())
                .setParameter(5, card.getScore())
                .setParameter(6, card.getId())
                .executeUpdate();
        return number;
    }
}
