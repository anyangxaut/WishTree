package controllers;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import models.dao.TaskDao;
import models.entity.Task;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.List;

public class TaskController extends Controller {

    @Inject
    TaskDao taskDao;

    @BodyParser.Of(BodyParser.Json.class)
    @Transactional
    public Result createTask() {
        JsonNode jsonParse = request().body().asJson();
        Task task = Json.fromJson(jsonParse, Task.class);

        Object number = taskDao.createTask(task);

        JsonNode jsonNode = Json.toJson(number);

        return ok(jsonNode);
    }

    @Transactional
    public Result deleteTask(Integer id) {
        Object number = taskDao.deleteTask(id);

        JsonNode jsonNode = Json.toJson(number);

        return ok(jsonNode);
    }

    @Transactional(readOnly = true)
    public Result getTaskWithId(Integer id) {
        Object resultObject = taskDao.getTaskWithId(id);

        JsonNode jsonNode = Json.toJson(resultObject);

        return ok(jsonNode);
    }

    @Transactional(readOnly = true)
    public Result getAllTasks() {
        List<Task> resultList = taskDao.getAllTasks();

        ObjectMapper mapper = new ObjectMapper();
        ArrayNode arrayNode = mapper.createArrayNode();

        for (Task task : resultList) {
            JsonNode jsonNode = Json.toJson(task);
            arrayNode.add(jsonNode);
        }
        return ok(arrayNode);
    }

    @BodyParser.Of(BodyParser.Json.class)
    @Transactional
    public Result updateTask() {
        JsonNode jsonParse = request().body().asJson();
        Task task = Json.fromJson(jsonParse, Task.class);

        Object number = taskDao.updateTask(task);

        JsonNode jsonNode = Json.toJson(number);

        return ok(jsonNode);
    }
}
