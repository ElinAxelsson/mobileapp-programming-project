package com.example.project;

import com.google.gson.annotations.SerializedName;

public class Spel {
    @SerializedName("ID")
    private String id;
    @SerializedName("type")
    private String login;
    private String name;
    @SerializedName("category")
    private String category;
    private int cost;


    public Spel () {
        id="Saknar ID";
        login="Login saknas";
        name="Namn saknas";
        category="Saknar kategori";
        cost=1;
    }

    public Spel(String ID, String Login, String Name, String Category, int Cost) {
        id = ID;
        login = Login;
        name = Name;
        category = Category;
        cost = Cost;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Namn: " + name + "\n" +
                "Kategori: " + category + "\n" +
                "Pris: " + cost + ":-" + "\n";
    }
}
