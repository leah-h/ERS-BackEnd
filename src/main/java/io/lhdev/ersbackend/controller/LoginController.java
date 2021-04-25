package io.lhdev.ersbackend.controller;

import io.javalin.Javalin;
import io.javalin.http.Handler;

import io.lhdev.ersbackend.DTO.LoginDTO;
import io.lhdev.ersbackend.DTO.MessageDTO;
import io.lhdev.ersbackend.model.User;
import io.lhdev.ersbackend.service.LoginService;


public class LoginController implements Controller {

    private LoginService loginService;

    public LoginController(){
        this.loginService = new LoginService();
    }

    private Handler loginHandler = (ctx) -> {

        LoginDTO loginDTO = ctx.bodyAsClass(LoginDTO.class);

        User user = loginService.login(loginDTO);

        ctx.sessionAttribute("currentlyLoggedInUser: ", user);
        ctx.json(user);
    };

    private Handler currentUserHandler = (ctx) -> {

        User user = (User) ctx.sessionAttribute("currentlyLoggedInUser");

        if (user == null) {
            MessageDTO messageDTO = new MessageDTO();
            messageDTO.setMessage("User is not currently logged in");
            ctx.json(messageDTO);
            ctx.status(400);
        } else {
            ctx.json(user);
        }
    };

    private Handler logoutHandler = (ctx) -> {
        ctx.req.getSession().invalidate();
    };

    @Override
    public void mapEndpoints(Javalin app) {

        app.post("/login", loginHandler);
        app.get("/current_user", currentUserHandler);
        app.post("/logout", logoutHandler);
    }
}
