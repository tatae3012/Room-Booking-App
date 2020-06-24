package com.aryan.android.oyo;

public class Room {
    private int id;
    private String name;
    private String location;
    private int rate;
    private int cost;
    private int discount;
    private double rating;
    private String description;
    private int ratings;


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

    public String getLocation() { return location; }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getRate() { return rate; }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public int getCost() { return cost; }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public double getRating(){return rating; }

    public void setRating(int rating){this.rating=rating;}

    public String getDescription(){return description; }

    public void setDescription(String description){this.description=description;}

    public int getDiscount(){return discount; }

    public void setDiscount(int discount){this.discount=discount;}

    public int getRatings(){return ratings; }

    public void setRatings(int ratings){this.ratings=ratings;}

}
