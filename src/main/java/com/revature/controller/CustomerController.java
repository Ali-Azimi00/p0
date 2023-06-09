package com.revature.controller;

import com.revature.models.Customer;
import com.revature.service.CustomerService;
import io.javalin.http.Context;
import com.revature.utils.JavalinAppConfig;

import java.util.ArrayList;


public class CustomerController {

    private static final CustomerService customerService = new CustomerService();

    //TODO static final logger

//    public static void handleGetByNumber(Context ctx){
//        int id;
//
//        try{
//            id = Integer.parseInt(ctx.pathParam("id"));
//
//        }catch(NumberFormatException e){
//            ctx.status(400);
////            logger.warn("unable to parse id = " + ctx.pathParam("id"));
//            return;
//        }
//
//        Customer customer = customerService.checkOrderByNumber(id);
//
//        if(customer != null){
//            ctx.status(200);
//        }else{
//            ctx.status(404);
//        }
//
//    }

    public static void handleGetAllOrders(Context ctx){
        ArrayList<Customer> customers = customerService.getAllCustomerOrders();

        ctx.status(200);
        ctx.json(customers);
    }
}
