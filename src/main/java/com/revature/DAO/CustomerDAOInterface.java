package com.revature.DAO;

import com.revature.models.Customer;

import java.util.ArrayList;

public interface CustomerDAOInterface {

    Customer getCustomerByNumber(int order_number);

    Customer getCustomerByName(String first_name, String last_name);
//    Customer addCustomerOrder(String first_name, String last_name,int order_size,int donut_id_fk);

    Customer insertCustomer(Customer order);

    ArrayList<Customer> getAllCustomerOrders();

    boolean updateCustomer(String first_name,int order_number);
//    boolean updateCustomer(String first_name, String last_name, int order_number);

    boolean updateDonutId(int order_number, int donut_id);


    void deleteCustomerByNumber(int order_number);





}
