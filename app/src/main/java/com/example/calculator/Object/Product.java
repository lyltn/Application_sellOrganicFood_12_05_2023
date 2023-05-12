package com.example.calculator.Object;

import java.io.Serializable;

public class Product implements Serializable {
    int id;
    String name;
    double price;
    String descrip;
    String pic;
    int quantity;
    int idCate;
    private int numberInCart;

    public Product() {
    }
    public Product(int id, String name, double price, String descrip, String pic, int quantity, int idCate) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.descrip = descrip;
        this.pic = pic;
        this.quantity = quantity;
        this.idCate = idCate;
    }
    public Product(int id, String name, double price, String descrip, String pic, int quantity, int idCate, int num) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.descrip = descrip;
        this.pic = pic;
        this.quantity = quantity;
        this.idCate = idCate;
        this.numberInCart = num;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getIdCate() {
        return idCate;
    }

    public void setIdCate(int idCate) {
        this.idCate = idCate;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", descrip='" + descrip + '\'' +
                ", pic='" + pic + '\'' +
                ", quantity=" + quantity +
                ", idCate=" + idCate +
                '}';
    }
    public int getNumberInCart(){
        return numberInCart;
    }

    public void setNumberInCart(int numberOrder) {
        this.numberInCart = numberOrder;
    }
}
