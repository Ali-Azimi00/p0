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
            logger.info("The following role was obtained from db: " + customer.toString());
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
            String isCustom = "";
            if(c.getDonut().getDonut_id() == 5){
                isCustom = "("
                        + c.getDonut().getCoating()+ " coating" + " , "
                        + c.getDonut().getFilling() + " filling"+ " , "
                        + c.getDonut().getTopping()+ " topping" + " , "
                        + ")";
            }

           statement.add("Order #"+c.getOrder_number() + " is " +
                   c.getFirst_name() + " " +c.getLast_name()+ " | order of " +
                   c.getOrder_size() + " X " + c.getDonut().getDonut_name() +
                   isCustom
           );
        }
        ctx.json(statement);
//
//        System.out.println(customers.get(0).getOrder_number());
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
            logger.warn("Creation failed");
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
            logger.warn("Failed to update order" + submittedCustomer.getOrder_number()+"'s first name from " + submittedCustomer.getFirst_name() );

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
            ctx.result("Successfully deleted order: " + id);
        }
        else{
            ctx.status(400);
            ctx.result("Number " + id +" does not exist");
        }

    }
}
