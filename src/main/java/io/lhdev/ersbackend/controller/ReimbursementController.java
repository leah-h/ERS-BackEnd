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


    @Override
    public void mapEndpoints(Javalin app) {

        app.get("/reimbursements", getAllReimbursements);
    }

}
