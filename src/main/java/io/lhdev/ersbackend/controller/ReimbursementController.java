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
        String id = ctx.pathParam("id");

        List<Reimbursement> userReimbursements = reimbursementService.getReimbursementByUserId(Integer.parseInt(id));

        if(!userReimbursements.isEmpty()) {
            ctx.json(userReimbursements);
        } else {
            logger.info("User with id: " + Integer.parseInt(id) + " does not exist.");
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

    private Handler filterReimbursementsByStatusId = ctx -> {

        String id = ctx.pathParam("id");

        List<Reimbursement> reimbursementsByStatus = reimbursementService.
                filterReimbursementsByStatusId(Integer.parseInt(id));

        if(!reimbursementsByStatus.isEmpty()) {
            logger.info("where's my reimbursements?!");
            ctx.json(reimbursementsByStatus);
        } else {
            logger.info("Reimbursements with statusId: " + Integer.parseInt(id) + " does not exist.");
            ctx.result("No reimbursements exist.");
        }
    };

    private Handler approveReimbursementById = ctx -> {

        String id = ctx.pathParam("id");

        Reimbursement reimbursement = ctx.bodyAsClass(Reimbursement.class);

        Reimbursement approvedReimbursement = reimbursementService.approveReimbursementById(Integer.parseInt(id),
                reimbursement);

        if (approvedReimbursement.getId() != 0) {
            ctx.json(approvedReimbursement);
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
        app.get("/reimbursements/filtered/:id", filterReimbursementsByStatusId);
        app.put("/reimbursements/:id", approveReimbursementById);

    }

}
