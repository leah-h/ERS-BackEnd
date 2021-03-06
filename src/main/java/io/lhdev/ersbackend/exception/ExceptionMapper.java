package io.lhdev.ersbackend.exception;

import io.javalin.Javalin;
import io.javalin.http.ExceptionHandler;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.lhdev.ersbackend.controller.Controller;

public class ExceptionMapper implements Controller {

    private Logger logger = LoggerFactory.getLogger(ExceptionMapper.class);

    private ExceptionHandler<BadParameterException> badParameterException = (e, ctx) -> {

        logger.warn(e.getMessage());
        ctx.status(400);
    };

    private ExceptionHandler<LoginException> loginException = (e, ctx) -> {
        logger.warn(e.getMessage());
        ctx.status(401);
    };


    @Override
    public void mapEndpoints(Javalin app) {
        app.exception(BadParameterException.class, badParameterException);
        app.exception(LoginException.class, loginException);
    }
}
