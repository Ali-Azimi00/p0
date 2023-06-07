package com.revature;

import com.revature.DAO.CustomerDAO;
import com.revature.DAO.DonutsDAO;
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
//
        System.out.println(currentCustomer.getCustomerByNumber(1));
        System.out.println(currentDonut.getDonutById(1));


//


    }


}
