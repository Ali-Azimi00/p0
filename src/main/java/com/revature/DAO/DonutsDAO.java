package com.revature.DAO;

import com.revature.models.Donuts;
import com.revature.utils.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;

public class DonutsDAO implements DonutsDAOInterface {


    @Override
    public Donuts getDonutById(int donut_id) {

        try (Connection conn = ConnectionUtil.getConnection()) {

            String sql = "SELECT * FROM donuts WHERE donut_id = ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, donut_id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Donuts donut = new Donuts(
                        rs.getInt("donut_id"),
                        rs.getString("donut_name"),
                        rs.getString("coating"),
                        rs.getString("topping"),
                        rs.getString("filling")
                );

                return donut;
            }

        } catch (SQLException e) {
            System.out.println("Failed to get Donut by Id");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Donuts> getAllDonuts() {
        try (Connection conn = ConnectionUtil.getConnection()) {
            String sql = "Select * FROM donuts";

            Statement s = conn.createStatement();

            ResultSet rs = s.executeQuery(sql);

            ArrayList<Donuts> menu = new ArrayList<>();

            while (rs.next()) {

                Donuts donut = new Donuts(
                        rs.getInt("donut_id"),
                        rs.getString("donut_name"),
                        rs.getString("coating"),
                        rs.getString("topping"),
                        rs.getString("filling")
                );

                menu.add(donut);

            }

            return menu;

        } catch (SQLException e) {
            System.out.println("Failed to retrieve full menu");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean updateCustomDonut(String coating, String topping, String filling) {

        try(Connection conn = ConnectionUtil.getConnection()){
            String sql = "UPDATE donuts SET coating= ? ,topping= ?,filling= ? WHERE donut_name= 'Custom'";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1,coating);
            ps.setString(2,topping);
            ps.setString(3,filling);

            ps.executeUpdate();

            return true;

        }catch(SQLException e){
            System.out.println("Failed to build a custom donut");
            e.printStackTrace();
        }
        return false;
    }

}
