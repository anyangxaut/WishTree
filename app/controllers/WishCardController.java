package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import models.WishCard;
import play.db.jpa.JPAApi;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by anyang on 2016/7/13.
 */
public class WishCardController extends Controller {

    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    public WishCardController(JPAApi api) {
        entityManager = api.em();
    }

    @BodyParser.Of(BodyParser.Json.class)
    @Transactional
    public Result createWishCard() {
        JsonNode jsonParse = request().body().asJson();
        WishCard wishCard = Json.fromJson(jsonParse, WishCard.class);


        JsonNode jsonCompose = Json.toJson(wishCard);
        Result jsonResult = ok(jsonCompose);
        return TODO;
    }

    @Transactional
    public Result deleteWishCard(Integer id) {
        return TODO;
    }

    @Transactional(readOnly = true)
    public Result readWishCard() {
//        entityManager = Persistence.createEntityManagerFactory("defaultPersistenceUnit")
//                .createEntityManager();
        Query query = entityManager.createQuery("select WishCard from wishcard");
        List<WishCard> wishCards = query.getResultList();
        return ok(Json.toJson(wishCards));
    }

    @Transactional
    public Result updateWishCard(Integer id) {

        return TODO;
    }

}
