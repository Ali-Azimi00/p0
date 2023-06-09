package com.revature.service;

import com.revature.DAO.CustomerDAO;
import com.revature.DAO.CustomerDAOInterface;
import com.revature.models.Customer;
import com.revature.utils.JavalinAppConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

public class CustomerService {
//Will be used once we set up the controller
    private static final Logger logger = LoggerFactory.getLogger(JavalinAppConfig.class);

    private final CustomerDAOInterface customerDAO = new CustomerDAO();
    public Customer checkOrderByNumber(int number){

        if(number > 0 && customerDAO.getCustomerByNumber(number) != null) {
            return customerDAO.getCustomerByNumber(number);
        }
        else{
            return null;
        }

    }

    public Customer getCustomerByName(String first_name, String last_name){

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

    public boolean updateCustomer(String first_name, int order_number){
        if(first_name == null || first_name.trim().equals(" ") ){
            return false;
        }
        char[] nameArray = first_name.toLowerCase().toCharArray();
        String formattedName ="";
        formattedName += Character.toUpperCase(nameArray[0]);
        for(int i= 1; i<nameArray.length; i++){
            if(nameArray[i-1] == ' '){
                formattedName += Character.toUpperCase(nameArray[i]);
            }
            else{
                formattedName += nameArray[i];
            }
        }

//---------------------------------------------------
        ArrayList<Customer> customerList = customerDAO.getAllCustomerOrders();
        ArrayList<Integer> orderNums = new ArrayList<>();

        for(Customer c : customerList){
            orderNums.add(c.getOrder_number());
        }

        boolean containsNumber = false;

        for (Integer c: orderNums){
            if(c == order_number){
                containsNumber = true;
            }
        }

// -----------------------------------------------------

        if(order_number > 0 &&  containsNumber){
            return customerDAO.updateCustomer(formattedName, order_number);
        }else{
            logger.warn("Order number not found");
        }
        return false;
    }

    public boolean updateDonutOrder(int order_number, int donut_id){

        ArrayList<Customer> customerList = customerDAO.getAllCustomerOrders();
        ArrayList<Integer> orderNums = new ArrayList<>();

        for(Customer c : customerList){
            orderNums.add(c.getOrder_number());
        }
        boolean containsNumber = false;

        for (Integer c: orderNums){
            if(c == order_number){
                containsNumber = true;
            }
        }

        if ( order_number < 1 || !containsNumber){
            logger.warn("Order number " + order_number + " does not exist");
            return false;
        }
        if (donut_id < 1 || donut_id > 4){
            logger.warn("Menu items are restricted to #1-4");
            return false;
        }

        System.out.println("service returns" + customerDAO.updateDonutId(order_number,donut_id));

        return customerDAO.updateDonutId(order_number,donut_id);
    };

    public ArrayList<Customer> getAllCustomerOrders(){
        return customerDAO.getAllCustomerOrders();
    };

    public boolean deleteByOrderNumber(int number){

        ArrayList<Customer> customerList = customerDAO.getAllCustomerOrders();
        ArrayList<Integer> orderNums = new ArrayList<>();

        for(Customer c : customerList){
            orderNums.add(c.getOrder_number());
        }

        boolean containsNumber = false;

        for (Integer c: orderNums){
            if(c == number){
                containsNumber = true;
            }
        }

        if( number > 0 && containsNumber){
            customerDAO.deleteCustomerByNumber(number);
            return true;
        }
        else{
            logger.warn("Order #" + number + " not found");
        }

        System.out.println("Failed to delete order number " +number);
        return containsNumber;
    };





    //TODO update customer
    //TODO getAll







}
