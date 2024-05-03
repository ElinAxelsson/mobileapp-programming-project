package com.example.project;

public class SpelBuilder {
    private String id;
    private String login;
    private String name;
    private String genre;
    private int cost;
    private String category;

    public SpelBuilder setId(String id) {
        this.id = id;
        return this;
    }

    public SpelBuilder setLogin(String login) {
        this.login = login;
        return this;
    }

    public SpelBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public SpelBuilder setGenre(String genre) {
        this.genre = genre;
        return this;
    }

    public SpelBuilder setCost(int cost) {
        this.cost = cost;
        return this;
    }

    public SpelBuilder setID(String id) {
        this.id = id;
        return this;
    }

    public SpelBuilder setCategory(String category) {
        this.category = category;
        return this;
    }

    public Spel createSpel() {
        return new Spel(id, login, name, genre, cost);
    }
}