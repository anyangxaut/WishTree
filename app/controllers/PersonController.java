package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import models.dao.PersonDao;
import models.entity.Person;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.List;


public class PersonController extends Controller {

    @Inject
    PersonDao personDao;

    @BodyParser.Of(BodyParser.Json.class)
    @Transactional
    public Result createPerson() {
        JsonNode jsonParse = request().body().asJson();
        Person person = Json.fromJson(jsonParse, Person.class);

        Object number = personDao.createPerson(person);

        JsonNode jsonNode = Json.toJson(number);

        return ok(jsonNode);
    }

    @Transactional
    public Result deletePerson(Integer id) {
        Object number = personDao.deletePerson(id);

        JsonNode jsonNode = Json.toJson(number);

        return ok(jsonNode);
    }

    @Transactional(readOnly = true)
    public Result getPersonWithId(Integer id) {
        Object resultObject = personDao.getPersonWithId(id);

        JsonNode jsonNode = Json.toJson(resultObject);

        return ok(jsonNode);
    }

    @Transactional(readOnly = true)
    public Result getAllPersons() {
        List<Person> resultList = personDao.getAllPersons();

        ObjectMapper mapper = new ObjectMapper();
        ArrayNode arrayNode = mapper.createArrayNode();

        for (Person person : resultList) {
            JsonNode jsonNode = Json.toJson(person);
            arrayNode.add(jsonNode);
        }
        return ok(arrayNode);
    }

    @BodyParser.Of(BodyParser.Json.class)
    @Transactional
    public Result updatePerson() {
        JsonNode jsonParse = request().body().asJson();
        Person person = Json.fromJson(jsonParse, Person.class);

        Object number = personDao.updatePerson(person);

        JsonNode jsonNode = Json.toJson(number);

        return ok(jsonNode);
    }
}
