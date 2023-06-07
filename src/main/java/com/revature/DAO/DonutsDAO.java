package com.revature.DAO;

import com.revature.models.Donuts;
import com.revature.utils.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DonutsDAO implements DonutsDAOInterface{


    @Override
    public Donuts getDonutById(int donut_id) {

        try(Connection conn = ConnectionUtil.getConnection()){

            String sql ="SELECT * FROM donuts WHERE donut_id = ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1,donut_id);

            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
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
}
