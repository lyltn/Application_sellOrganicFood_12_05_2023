package com.example.calculator.Object;

public class Category {
    int id;
    String name;
    String pic;
    String background;

    public Category() {
    }

    public Category(int id, String name, String pic, String background) {
        this.id = id;
        this.name = name;
        this.pic = pic;
        this.background = background;
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

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }
}
