package models.dao;

import models.entity.Task;
import play.db.jpa.JPAApi;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;


public class TaskDao {

    JPAApi api;

    @Inject
    public TaskDao(JPAApi api) {
        this.api = api;
    }

    public List<Task> getAllTasks() {
        EntityManager entityManager = api.em();
        List<Task> resultList = entityManager
                .createQuery("SELECT t FROM Task t")
                .getResultList();
        return resultList;
    }

    public Object getTaskWithId(Integer id) {
        EntityManager entityManager = api.em();
        Object resultObject = entityManager
                .createQuery("SELECT t FROM Task t WHERE t.id = :customId")
                .setParameter("customId", id)
                .getSingleResult();
        return resultObject;
    }

    public Object createTask(Task task) {
        EntityManager entityManager = api.em();
        Object number = entityManager
                .createNativeQuery("INSERT INTO task (wishId, ownerId, assign) VALUES (?, ?, ?)")
                .setParameter(1, task.getWishId())
                .setParameter(2, task.getOwnerId())
                .setParameter(3, task.getAssign())
                .executeUpdate();
        return number;
    }

    public Object deleteTask(Integer id) {
        EntityManager entityManager = api.em();
        Object number = entityManager
                .createQuery("DELETE From Task t WHERE t.id = :customId")
                .setParameter("customId", id)
                .executeUpdate();
        return number;
    }

    public Object updateTask(Task task) {
        EntityManager entityManager = api.em();
        Object number = entityManager
                .createNativeQuery("UPDATE task SET wishId = ?, ownerId = ?, assign = ? WHERE id = ?")
                .setParameter(1, task.getWishId())
                .setParameter(2, task.getOwnerId())
                .setParameter(3, task.getAssign())
                .setParameter(4, task.getId())
                .executeUpdate();
        return number;
    }
}
