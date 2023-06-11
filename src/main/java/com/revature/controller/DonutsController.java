package com.revature.controller;

import com.revature.DAO.DonutsDAO;
import com.revature.models.Donuts;
import com.revature.service.CustomerService;
import com.revature.service.DonutsService;
import com.revature.utils.JavalinAppConfig;
import io.javalin.http.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

public class DonutsController {

    private static final DonutsService donutsService = new DonutsService();

    private static final Logger logger = LoggerFactory.getLogger(JavalinAppConfig.class);


    public static void handleGetMenu(Context ctx){
        ArrayList<Donuts> donuts = donutsService.getAllDonuts();
        ArrayList<String> statement = new ArrayList<>();

        ctx.status(200);

        for(Donuts d : donuts){
            String coating = (d.getCoating() != null ? d.getCoating() : "no");
            String topping = (d.getTopping() != null? d.getTopping() : "no");
            String filling = (d.getFilling() != null? d.getFilling() : "no");

            statement.add(d.getDonut_id()+ " | "+ d.getDonut_name() + " has "
                    + coating+ " coating, " + topping + " topping, "
                    + filling + " filling"
            );
        }
        ctx.json(statement);
    }

    public static void handleGetById(Context ctx){
        int id;

        try{
            id = Integer.parseInt(ctx.pathParam("id"));
        }catch (NumberFormatException e){
            ctx.status(400);
            logger.warn("unable to parse id = " + ctx.pathParam("id"));
            return;
        }

        Donuts donut = donutsService.getDonutById(id);

        String c = donut.getCoating() + " Coating |";
        String t = donut.getTopping() + " Topping |";
        String f = donut.getFilling()+ " Filling |";

        String coating= donut.getCoating() != null ? c : "";
        String topping= donut.getTopping() != null ? t : "";
        String filling= donut.getFilling() != null ? f : "";

        if(donut != null){
            ctx.status(200);
            ctx.result("Menu item #" + donut.getDonut_id() + " | "
                    + donut.getDonut_name() + " | "
                    + coating
                    + topping
                    + filling
            );
        }
    }

    //-------------------------------------------------------------
    public static void handleBuildDonut (Context ctx){
        Donuts submittedDonut = ctx.bodyAsClass(Donuts.class);

//        DonutsDAO donutsDAO = new DonutsDAO();

        boolean buildSuccessful = donutsService.buildCustomDonut(
                submittedDonut.getCoating(),
                submittedDonut.getTopping(),
                submittedDonut.getFilling()
        );

        if(buildSuccessful){
            ctx.status(200);
            ctx.result("build Succesful");

//            donutsDAO.updateCustomDonut(null,null,null);
        }
        else{
            ctx.status(400);
            ctx.result("Failed to build donut");
        }

    }










}
