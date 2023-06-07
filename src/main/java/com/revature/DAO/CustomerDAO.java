package com.revature.DAO;

import com.revature.models.Customer;
import com.revature.utils.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDAO implements CustomerInterface {

    @Override
    public Customer getCustomerByNumber(int order_number) {

        try(Connection conn = ConnectionUtil.getConnection()){
            String sql = "SELECT * FROM customer WHERE order_number = ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, order_number);

            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                Customer customer= new Customer(
                        rs.getInt("order_number"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getInt("order_size"),
                        rs.getInt("donut_id_fk")
                );

                return customer;
            }

        }catch(SQLException e){
            System.out.println("Failed to get customer order number");
            e.printStackTrace();
        }

        return null;
    }



}
