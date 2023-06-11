package com.revature.service;

import com.revature.DAO.DonutsDAO;
import com.revature.DAO.DonutsDAOInterface;
import com.revature.models.Donuts;

import java.util.ArrayList;

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

    public ArrayList<Donuts> getAllDonuts(){
        return donutsDAO.getAllDonuts();
    };




    //-------------------------------------------------------------
    public boolean buildCustomDonut(String coating, String topping, String filling){
        if(coating.trim().equals(" ")){
            coating = null;
        }
        if(topping.trim().equals(" ")){
            topping = null;
        }
        if(coating.trim().equals(" ")){
            topping = null;
        }
//        Coating Formatting
        char[] coatingArray = coating.toLowerCase().toCharArray();
        String formattedCoating ="";
        formattedCoating += Character.toUpperCase(coatingArray[0]);
        for(int i= 1; i<coatingArray.length; i++){
            if(coatingArray[i-1] == ' '){
                formattedCoating += Character.toUpperCase(coatingArray[i]);
            }
            else{
                formattedCoating += coatingArray[i];
            }
        }
//        Toppings Formatting
        char[] toppingArray = topping.toLowerCase().toCharArray();
        String formattedTopping ="";
        formattedTopping += Character.toUpperCase(toppingArray[0]);
        for(int i= 1; i<toppingArray.length; i++){
            if(toppingArray[i-1] == ' '){
                formattedTopping += Character.toUpperCase(toppingArray[i]);
            }
            else{
                formattedTopping += toppingArray[i];
            }
        }
//        Filling Formatting
        char[] fillingeArray = filling.toLowerCase().toCharArray();
        String formattedFilling ="";
        formattedFilling += Character.toUpperCase(fillingeArray[0]);
        for(int i= 1; i<fillingeArray.length; i++){
            if(fillingeArray[i-1] == ' '){
                formattedFilling += Character.toUpperCase(fillingeArray[i]);
            }
            else{
                formattedFilling += fillingeArray[i];
            }
        }


        return donutsDAO.updateCustomDonut(formattedCoating,formattedTopping,formattedFilling);
    };

    //TODO getAll

}
