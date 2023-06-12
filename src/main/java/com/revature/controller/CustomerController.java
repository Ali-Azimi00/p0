package com.revature.controller;

import com.revature.DAO.CustomerDAO;
import com.revature.DAO.DonutsDAO;
import com.revature.models.Customer;
import com.revature.models.Donuts;
import com.revature.service.CustomerService;
import io.javalin.http.Context;
import com.revature.utils.JavalinAppConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;


public class CustomerController {

    private static final CustomerService customerService = new CustomerService();

    private static final Logger logger = LoggerFactory.getLogger(JavalinAppConfig.class);


    public static void handleGetByNumber(Context ctx){
        int id;

        try{
            id = Integer.parseInt(ctx.pathParam("id"));

        }catch(NumberFormatException e){
            ctx.status(400);
           logger.warn("unable to parse id = " + ctx.pathParam("id"));
            return;
        }

        Customer customer = customerService.checkOrderByNumber(id);

        if(customer != null){
            ctx.status(200);
//            ctx.json(customer);
            ctx.result(customer.getFirst_name()+ " ordered the " + customer.getDonut().getDonut_name());
            logger.info("The following role was obtained from db: " + customer.toString()
            );
        }else{
            ctx.status(404);
            ctx.result("Couldn't find order number " + id);
            logger.warn("No resource was found at id = " + id + " from ip: " + ctx.ip());
        }
    }

    public static void handleGetAllOrders(Context ctx){
        ArrayList<Customer> customers = customerService.getAllCustomerOrders();
        ArrayList<String> statement = new ArrayList<>();

        ctx.status(200);

        for (Customer c : customers) {
           statement.add("Order #"+c.getOrder_number() + " is " +
                   c.getFirst_name() + " " +c.getLast_name()+ " | order of " +
                   c.getOrder_size() + " X " + c.getDonut().getDonut_name()
           );
        }
        ctx.json(statement);
        logger.info("Customer orders successfully retrieved" + statement);
    }

    public static void handleCreateOrder(Context ctx){
        Customer customer = ctx.bodyAsClass(Customer.class);

        Customer returnedCustomer = customerService.createNewCustomerOrder(customer);
        DonutsDAO donutsDAO = new DonutsDAO();

        String fName = returnedCustomer.getFirst_name();
        String lName = returnedCustomer.getLast_name();
        int orderNum = returnedCustomer.getOrder_number();
        int orderSize = returnedCustomer.getOrder_size();
        int donutNum= returnedCustomer.getDonut_id_fk();
        String donutName = donutsDAO.getDonutById(donutNum).getDonut_name();
       // System.out.println(returnedCustomer.getDonut().getDonut_name());

//        if(donutNum == 5){
//            donutsDAO.updateCustomDonut(null,null,null);
//            System.out.println("Order 5 recognized");
//        }

        if(returnedCustomer != null){
            ctx.status(201);
            ctx.result("New Order Submitted : " + fName + " " + lName + " made "
                    + orderSize + " order(s) of "
                    +" #" + donutNum + " : "
                    +  donutName
            );
            logger.info("The following employee was created: " + returnedCustomer.toString());

        }else{
            ctx.status(400);
            logger.warn("Order creation failed");
        }
    }

    public static void handleUpdateDonutOrder(Context ctx){
        int id;
        try{
            id = Integer.parseInt(ctx.pathParam("id"));

        }catch(NumberFormatException e){
            ctx.status(400);
            logger.warn("unable to parse id = " + ctx.pathParam("id"));
            return;
        }
        Customer submittedDonut = ctx.bodyAsClass(Customer.class);
        boolean updateSuccessful = customerService.updateDonutOrder(
                id,
                submittedDonut.getDonut_id_fk()
        );
        if (updateSuccessful){
            ctx.status(200);
            ctx.result("Successfully updated order #" + id +" to menu item #" + submittedDonut.getDonut_id_fk());
//                    + "'s donut to " + submittedDonut.getDonut_id_fk() + " | " + submittedDonut.getDonut().getDonut_name()
//                    );
            logger.info("Successfully updated order #" + id +" to Menu Item #" + submittedDonut.getDonut_id_fk());
        }
        else{
            ctx.status(404);
            ctx.result("Failed do update order");
            logger.warn("Failed to update donut Order #"+ id + " to Menu Item #" + submittedDonut.getDonut_id_fk());
        }
    }

    public static void handleUpdateCustomer(Context ctx){

        Customer submittedCustomer = ctx.bodyAsClass(Customer.class);

        boolean updateSuccessful = customerService.updateCustomer(submittedCustomer.getFirst_name(),
                                                             submittedCustomer.getOrder_number());

        if(updateSuccessful){
            ctx.status(200);
            ctx.result("Order #"+submittedCustomer.getOrder_number()+"'s first name has been updated to: " + submittedCustomer.getFirst_name());
            logger.info("Order #"+submittedCustomer.getOrder_number()+"'s first name has been updated to: " + submittedCustomer.getFirst_name());
        }
        else{
            ctx.status(400);
            ctx.result("Failed to update order" + submittedCustomer.getOrder_number()+"'s first name");
            logger.warn("Failed to update order #" + submittedCustomer.getOrder_number()+"'s first name from " + submittedCustomer.getFirst_name() );

        }
    }

    public static void handleDeleteOrder(Context ctx){
        int id;
        try{
            id = Integer.parseInt(ctx.pathParam("id"));

        }catch(NumberFormatException e){
            ctx.status(400);
            logger.warn("unable to parse id = " + ctx.pathParam("id"));
            return;
        }

        boolean orderDeleted = customerService.deleteByOrderNumber(id);

        if(orderDeleted){
            ctx.status(200);
            ctx.result("Successfully deleted order #" + id);
            logger.info("Successfully deleted order #" + id);
        }
        else{
            ctx.status(400);
            ctx.result("Order #" + id +" does not exist");
            logger.warn("Failed to deleted order #" + id);

        }

    }
}
