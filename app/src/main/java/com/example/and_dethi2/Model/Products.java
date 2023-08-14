package com.example.and_dethi2.Model;

public class Products {
    private int ID;
    private String Name;
    private int Price;
    private int Amount;
    private String Avatar;

    public Products(int ID, String name, int price, int amount, String Avatar) {
        this.ID = ID;
        Name = name;
        Price = price;
        Amount = amount;
        this.Avatar = Avatar;
    }

    public Products() {
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public int getAmount() {
        return Amount;
    }

    public void setAmount(int amount) {
        Amount = amount;
    }

    public String getAvatar() {
        return Avatar;
    }

    public void setAvatar(String Avatar) {
        this.Avatar = Avatar;
    }
}
