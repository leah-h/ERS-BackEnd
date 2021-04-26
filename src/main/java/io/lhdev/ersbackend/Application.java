package io.lhdev.ersbackend;

import io.javalin.Javalin;
import io.lhdev.ersbackend.controller.Controller;
import io.lhdev.ersbackend.controller.LoginController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Application {

    private static Javalin app;

    private static Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {

        Javalin app = Javalin.create((config) -> {
            config.enableCorsForAllOrigins();
        });

        app.before((ctx) -> {
            String URI = ctx.req.getRequestURI();
            String httpMethod = ctx.req.getMethod();
            logger.info(httpMethod + " request to endpoint " + URI + " received");
        });

        mapControllers(app, new LoginController());

        app.start(7000);
    }

    public static void mapControllers(Javalin app, Controller...controllers){
        for (Controller c: controllers){
            c.mapEndpoints(app);
        }
    }
}
