package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import models.dao.CardDao;
import models.entity.Card;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.List;


public class CardController extends Controller {

    @Inject
    CardDao cardDao;

    @BodyParser.Of(BodyParser.Json.class)
    @Transactional
    public Result createCard() {
        JsonNode jsonParse = request().body().asJson();
        Card card = Json.fromJson(jsonParse, Card.class);

        Object number = cardDao.createCard(card);

        JsonNode jsonNode = Json.toJson(number);

        return ok(jsonNode);
    }

    @Transactional
    public Result deleteCard(Integer id) {
        Object number = cardDao.deleteCard(id);

        JsonNode jsonNode = Json.toJson(number);

        return ok(jsonNode);
    }

    @Transactional(readOnly = true)
    public Result getCardWithId(Integer id) {
        Object reultObject = cardDao.getCardWithId(id);

        JsonNode jsonNode = Json.toJson(reultObject);

        return ok(jsonNode);
    }

    @Transactional(readOnly = true)
    public Result getAllCards() {
        List<Card> reultList = cardDao.getAllCards();

        ObjectMapper mapper = new ObjectMapper();
        ArrayNode arrayNode = mapper.createArrayNode();

        for (Card card : reultList) {
            JsonNode jsonNode = Json.toJson(card);
            arrayNode.add(jsonNode);
        }
        return ok(arrayNode);
    }

    @BodyParser.Of(BodyParser.Json.class)
    @Transactional
    public Result updateCard() {
        JsonNode jsonParse = request().body().asJson();
        Card card = Json.fromJson(jsonParse, Card.class);

        Object number = cardDao.updateCard(card);

        JsonNode jsonNode = Json.toJson(number);

        return ok(jsonNode);
    }
}
