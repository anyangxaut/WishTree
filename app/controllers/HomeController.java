package controllers;

import controllers.*;
import controllers.routes;
import play.mvc.*;

import views.html.*;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {

    public Result index() {

        return redirect(controllers.routes.CardController.getAllCards());
    }

}
