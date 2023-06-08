package com.revature.DAO;

import com.revature.models.Donuts;

import java.util.ArrayList;

public interface DonutsDAOInterface {
    Donuts getDonutById(int donut_id);

    ArrayList<Donuts> getFullMenu();

    boolean buildCustomDonut(String coating, String topping, String filling);


}
