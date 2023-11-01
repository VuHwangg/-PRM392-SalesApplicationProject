package com.example.prm392_finalproject.Task;

import android.os.AsyncTask;
import android.widget.TextView;

import com.example.prm392_finalproject.DTOModels.Cart_Product_DTO;

import java.util.List;

public class CalculatorTask extends AsyncTask<List<Cart_Product_DTO>,Void,Double> {

    @Override
    protected Double doInBackground(List<Cart_Product_DTO>... lists) {
        return calculateTotalCost(lists[0]);
    }
    private double calculateTotalCost(List<Cart_Product_DTO> productList) {
        double totalCost = 0.0;
        for (Cart_Product_DTO product : productList) {
            totalCost += product.getPrice() * product.getQuantity();
        }
        return totalCost;
    }
}
