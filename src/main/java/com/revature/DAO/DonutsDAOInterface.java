package com.revature.DAO;

import com.revature.models.Donuts;

import java.util.ArrayList;

public interface DonutsDAOInterface {
    Donuts getDonutById(int donut_id);

    ArrayList<Donuts> getAllDonuts();

    boolean updateCustomDonut(String coating, String topping, String filling);


}
