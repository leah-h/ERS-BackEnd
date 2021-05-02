package io.lhdev.ersbackend.controller;

import io.javalin.Javalin;
import io.javalin.http.Handler;
import io.lhdev.ersbackend.model.Reimbursement;
import io.lhdev.ersbackend.service.ReimbursementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ReimbursementController implements Controller {

    private Logger logger = LoggerFactory.getLogger(ReimbursementController.class);

    private ReimbursementService reimbursementService;

    public ReimbursementController() {
        this.reimbursementService = new ReimbursementService();
    }

    private Handler getAllReimbursements = (ctx) -> {

        List<Reimbursement> reimbursementList = reimbursementService.getAllReimbursements();

        logger.info("i'm here...");
        ctx.json(reimbursementList);
    };

    private Handler getReimbursementsByUserId = (ctx) -> {
        String userId = ctx.pathParam("id");

        List<Reimbursement> userReimbursements = reimbursementService.getReimbursementByUserId(Integer.parseInt(userId));

        if(!userReimbursements.isEmpty()) {
            ctx.json(userReimbursements);
        } else {
            logger.info("User with id: " + Integer.parseInt(userId) + " does not exist.");
            ctx.result("User does not exist.");
        }

    };

    private Handler addReimbursement = ctx -> {
        Reimbursement reimbursement = ctx.bodyAsClass(Reimbursement.class);

        Reimbursement insertedReimbursement = reimbursementService.addReimbursement(reimbursement);

        if (insertedReimbursement.getId() != 0) {
            ctx.json(insertedReimbursement);
        } else {
            logger.info("Something went wrong...try again");
            ctx.result("Something went wrong...try again");
        }

    };


    @Override
    public void mapEndpoints(Javalin app) {

        app.get("/reimbursements", getAllReimbursements);
        app.post("/reimbursements", addReimbursement);
        app.get("/reimbursements/:id", getReimbursementsByUserId);

    }

}