package com.example.calculator.Hepler;

import android.content.Context;
import android.widget.Toast;

import com.example.calculator.Interface.ChangeNumberItemsListener;
import com.example.calculator.Object.Product;

import java.util.ArrayList;

public class ManagementCart {
    private Context context;
    private TinyDB tinyDB;

    public ManagementCart(Context context){
        this.context = context;
        this.tinyDB = new TinyDB(context);
    }

    public void insertFood(Product item){
        ArrayList<Product> listFood = getListCart();
        boolean existAlready = false;
        int n = 0 ;
        for (int i=0; i<listFood.size(); i++){
            if(listFood.get(i).getName().equals(item.getName())){
                existAlready = true;
                n = i;
                break;
            }

        }
        if(existAlready){
            listFood.get(n).setNumberInCart(item.getNumberInCart());

        }else{
            listFood.add(item);
        }
        tinyDB.putListObject("CardList", listFood);
        Toast.makeText(context, "Added To Your Cart", Toast.LENGTH_SHORT).show();
    }


    public ArrayList<Product> getListCart(){
        return tinyDB.getListObject("CardList");
    }
    public ArrayList<Product> deleteListCart(){
        tinyDB.clear();
        return tinyDB.getListObject("CardList");
    }
    public void plusNumberFoood(ArrayList<Product> listFood, int position, ChangeNumberItemsListener changeNumberItemsListener){
        listFood.get(position).setNumberInCart(listFood.get(position).getNumberInCart() + 1);
        tinyDB.putListObject("CartList", listFood);
        changeNumberItemsListener.changed();
    }

    public void minusNumberFood(ArrayList<Product> listFood, int position, ChangeNumberItemsListener changeNumberItemsListener){
        if(listFood.get(position).getNumberInCart()==1){
            listFood.remove(position);
        }else{
            listFood.get(position).setNumberInCart(listFood.get(position).getNumberInCart() -1);

        }
        tinyDB.putListObject("CartList", listFood);
        changeNumberItemsListener.changed();
    }

    public Double getTotalFee(){
        ArrayList<Product> listFood = getListCart();
        double fee = 0;
        for(int i=0; i <listFood.size(); i++){
            fee = fee + (listFood.get(i).getPrice() * listFood.get(i).getNumberInCart());

        }
       return fee;
    }
}
