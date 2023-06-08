package com.revature;

import com.revature.DAO.CustomerDAO;
import com.revature.DAO.DonutsDAO;
import com.revature.models.Customer;
import com.revature.utils.ConnectionUtil;

import java.sql.Connection;
import java.sql.SQLException;



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

        Customer marge = new Customer("Marge", "Simpson", 3,2);

//        currentCustomer.deleteCustomerByNumber(1);

        currentCustomer.addCustomerOrder(marge);


        System.out.println(currentCustomer.getCustomerByNumber(2));
        System.out.println(currentDonut.getDonutById(1));
        System.out.println(currentCustomer.getCustomerByName("Marge","Simpson"));

    }


}
