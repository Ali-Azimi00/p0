package com.revature.service;

import com.revature.DAO.DonutsDAO;
import com.revature.DAO.DonutsDAOInterface;
import com.revature.models.Donuts;

public class DonutsService {

    private final DonutsDAOInterface donutsDAO = new DonutsDAO();

    public Donuts getDonutById(int id){

        if(id>0 && id < 6){
            return donutsDAO.getDonutById(id);
        }
        else{
            System.out.println("Order number is not an option, choose bewtween Donut order 1-6 ");
            return null;
        }
    }

    //TODO getAll

}
