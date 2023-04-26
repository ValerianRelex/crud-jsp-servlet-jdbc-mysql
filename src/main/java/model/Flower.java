package model;

/**
 * This is a simple POJO class - model class represents a Flower entity
 *
 */

public class Flower {
    private int id;
    private String variety;
    private String alias;
    private int height;
    private int price;

    public Flower() {
    }

    public Flower(int id, String variety, String alias, int height, int price) {
        this.id = id;
        this.variety = variety;
        this.alias = alias;
        this.height = height;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVariety() {
        return variety;
    }

    public void setVariety(String variety) {
        this.variety = variety;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
