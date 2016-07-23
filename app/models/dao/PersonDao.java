package models.dao;

import models.entity.Person;
import play.db.jpa.JPAApi;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public class PersonDao {

    JPAApi api;

    @Inject
    public PersonDao(JPAApi api) {
        this.api = api;
    }

    public List<Person> getAllPersons() {
        EntityManager entityManager = api.em();
        List<Person> resultList = entityManager
                .createQuery("SELECT p FROM Person p")
                .getResultList();
        return resultList;
    }

    public Object getPersonWithId(Integer id) {
        EntityManager entityManager = api.em();
        Object resultObject = entityManager
                .createQuery("SELECT p FROM Person p WHERE p.id = :customId")
                .setParameter("customId", id)
                .getSingleResult();
        return resultObject;
    }

    public Object createPerson(Person person) {
        EntityManager entityManager = api.em();
        Object number = entityManager
                .createNativeQuery("INSERT INTO person (account, password, level, avatar) VALUES (?, ?, ?, ?)")
                .setParameter(1, person.getAccount())
                .setParameter(2, person.getPassword())
                .setParameter(3, person.getLevel())
                .setParameter(4, person.getAvatar())
                .executeUpdate();
        return number;
    }

    public Object deletePerson(Integer id) {
        EntityManager entityManager = api.em();
        Object number = entityManager
                .createQuery("DELETE From Person p WHERE p.id = :customId")
                .setParameter("customId", id)
                .executeUpdate();
        return number;
    }

    public Object updatePerson(Person person) {
        EntityManager entityManager = api.em();
        Object number = entityManager
                .createNativeQuery("UPDATE person SET account = ?, password = ?, level = ?, avatar = ? WHERE id = ?")
                .setParameter(1, person.getAccount())
                .setParameter(2, person.getPassword())
                .setParameter(3, person.getLevel())
                .setParameter(4, person.getAvatar())
                .setParameter(5, person.getId())
                .executeUpdate();
        return number;
    }
}
