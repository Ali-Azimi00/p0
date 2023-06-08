package com.revature;

import com.revature.DAO.CustomerDAO;
import com.revature.DAO.DonutsDAO;
import com.revature.models.Customer;
import com.revature.models.Donuts;
import com.revature.utils.ConnectionUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;


public class Launcher {

    public static void main(String[] args) {

        //Testing Connection ---------------------------------
        try (Connection conn = ConnectionUtil.getConnection()) {
            System.out.println("Connect successful");
        } catch (SQLException e) {
            System.out.println("failed to connect");
        }

        CustomerDAO currentCustomer = new CustomerDAO();
        DonutsDAO currentDonut = new DonutsDAO();

//        Customer marge = new Customer("Marge", "Simpson", 3,2);
//        currentCustomer.addCustomerOrder(marge);

//        currentCustomer.deleteCustomerByNumber(1);


        currentDonut.buildCustomDonut("dbl frosting", "dbls prinkles", "jelly");

        System.out.println("Your custom donut is: " + currentDonut.getDonutById(5));
        System.out.println(currentCustomer.getCustomerByNumber(1));
        System.out.println(currentDonut.getDonutById(1));
        System.out.println(currentCustomer.getCustomerByName("Marge", "Simpson"));


        System.out.println("(ˆڡˆ)◞\uD83C\uDF69");

        currentDonut.buildCustomDonut(null, null, null);

        ArrayList<Donuts> menu = currentDonut.getFullMenu();
        for (Donuts d : menu) {
            System.out.println(d);
        }

        ArrayList<Customer> orders = currentCustomer.getAllCustomerOrders();
        for (Customer c : orders) {
            System.out.println(c.toString());
        }
    }


}
