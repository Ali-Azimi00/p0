package com.revature.DAO;

import com.revature.models.Customer;
import com.revature.models.Donuts;
import com.revature.utils.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;

public class CustomerDAO implements CustomerDAOInterface {

    @Override
    public Customer getCustomerByNumber(int order_number) {

        try (Connection conn = ConnectionUtil.getConnection()) {
            String sql = "SELECT * FROM customer WHERE order_number = ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, order_number);

            ResultSet rs = ps.executeQuery();

            DonutsDAO donutsDAO = new DonutsDAO();


            if (rs.next()) {
                Customer customer = new Customer(
                        rs.getInt("order_number"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getInt("order_size"),
                        donutsDAO.getDonutById((rs.getInt("donut_id_fk")))

                );

                return customer;
            }

        } catch (SQLException e) {
            System.out.println("Failed to get customer order number");
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Customer getCustomerByName(String first_name, String last_name) {

        try (Connection conn = ConnectionUtil.getConnection()) {
            String sql = "SELECT * FROM customer WHERE first_name = ? AND last_name = ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, first_name);
            ps.setString(2, last_name);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Customer customer = new Customer(
                        rs.getInt("order_number"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getInt("order_size"),
                        rs.getInt("donut_id_fk")
                );
                return customer;
            }

        } catch (SQLException e) {
            System.out.println("Failed to retrieve customer name");
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Customer insertCustomer(Customer order) {

        try(Connection conn = ConnectionUtil.getConnection()) {
            String sql ="INSERT INTO customer(first_name, last_name, order_size, donut_id_fk) VALUES (?,?,?,?)";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, order.getFirst_name());
            ps.setString(2,order.getLast_name());
            ps.setInt(3,order.getOrder_size());
            ps.setInt(4,order.getDonut_id_fk());

            ps.executeUpdate();

            return order;


        }catch(SQLException e){
            System.out.println("Failed to add customer order");
            e.printStackTrace();

        }

        return null;
    }

    @Override
    public ArrayList<Customer> getAllCustomerOrders() {
        try(Connection conn = ConnectionUtil.getConnection()){
            String sql = "SELECT * FROM customer";

            Statement s = conn.createStatement();

            ResultSet rs = s.executeQuery(sql);

            ArrayList<Customer> ordersList = new ArrayList<>();

            DonutsDAO donutsDAO = new DonutsDAO();

            while(rs.next()){
                Customer customer = new Customer(
                        rs.getInt("order_number"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getInt("order_size"),
                        donutsDAO.getDonutById((rs.getInt("donut_id_fk")))

                );

                 ordersList.add(customer);
            }

            return ordersList;

        }catch(SQLException e){
            System.out.println("Failed to retreive all orders list");
            e.printStackTrace();
        }

        return null;
    };




    @Override
    public void deleteCustomerByNumber(int order_number) {

        try (Connection conn = ConnectionUtil.getConnection()) {

            String sql ="DELETE FROM customer WHERE order_number = ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1,order_number);

            ps.executeUpdate();

            System.out.println("now deleting order number: " + order_number);


        } catch (SQLException e) {
            System.out.println("Failed to delete order number: " + order_number);
            e.printStackTrace();
        }
    }


}
