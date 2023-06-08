package com.revature.service;

import com.revature.DAO.DonutsDAO;
import com.revature.DAO.DonutsDAOInterface;
import com.revature.models.Donuts;

public class DonutsService {

    private final DonutsDAOInterface donutsDAO = new DonutsDAO();

    public Donuts getDonutById(int id){
        if(id<0){
            return donutsDAO.getDonutById(id);
        }
        else{
            return null;
        }
    }

    //TODO getAll

}
