package com.example.calculator.Object;

public class Order {
    int id;
    String userName;
    String nameRe;
    String phone;
    String address;
    String note;
    String date;
    double total;
    int status;

    public Order(int id, String userName, String nameRe, String phone, String address, String note, String date, double total, int status) {
        this.id = id;
        this.userName = userName;
        this.nameRe = nameRe;
        this.phone = phone;
        this.address = address;
        this.note = note;
        this.date = date;
        this.total = total;
        this.status = status;
    }

    public Order() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNameRe() {
        return nameRe;
    }

    public void setNameRe(String nameRe) {
        this.nameRe = nameRe;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
