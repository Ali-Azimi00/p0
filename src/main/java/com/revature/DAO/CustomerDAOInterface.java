package com.revature.DAO;

import com.revature.models.Customer;

import java.util.ArrayList;

public interface CustomerDAOInterface {

    Customer getCustomerByNumber(int order_number);

    Customer getCustomerByName(String first_name, String last_name);
//    Customer addCustomerOrder(String first_name, String last_name,int order_size,int donut_id_fk);

    Customer insertCustomer(Customer order);

    ArrayList<Customer> getAllCustomerOrders();

    void deleteCustomerByNumber(int order_number);





}
