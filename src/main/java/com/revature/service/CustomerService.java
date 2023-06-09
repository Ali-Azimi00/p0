package com.revature.service;

import com.revature.DAO.CustomerDAO;
import com.revature.DAO.CustomerDAOInterface;
import com.revature.models.Customer;

import java.util.ArrayList;

public class CustomerService {
//Will be used once we set up the controller
    private final CustomerDAOInterface customerDAO = new CustomerDAO();

    public Customer checkOrderByNumber(int number){

        if(number > 0){
            return customerDAO.getCustomerByNumber(number);
        }
        else{
            return null;
        }

    }

    public boolean deleteByOrderNumber(int number){

        if( number > 0){
            customerDAO.deleteCustomerByNumber(number);
            return true;
        }

        System.out.println("Failed to delete order number " +number);
        return false;
    };

    public Customer getCustomerByName(String first_name, String last_name){
;
        if(first_name != null && last_name != null){
            return customerDAO.getCustomerByName(first_name,last_name);
        }

        return null;
    }


    public Customer createNewCustomerOrder(Customer order){
        String first_name = order.getFirst_name();
        String last_name = order.getLast_name();
        int order_size = order.getOrder_size();
        int donut_id = order.getDonut_id_fk();

        boolean name = (first_name != null || last_name != null);
        boolean orderSize = (order_size > 0);
        boolean donutId =(donut_id > 0 && donut_id < 6);


        if( name && orderSize && donutId){
            return customerDAO.insertCustomer(order);
        }
        else{
            System.out.println("Required fields are not met");
            System.out.println("First or Last name are required");
            System.out.println("Positive integer for order size is required");
            System.out.println("Choose a donut option between 1-5");
            return null;
        }
    }

    public ArrayList<Customer> getAllCustomerOrders(){
        return customerDAO.getAllCustomerOrders();
    };





    //TODO update customer
    //TODO getAll







}
