package backend.modelClasses.concreteClasses;

import backend.modelClasses.interfaces.IQueue;

import java.util.List;

public class Queue implements IQueue {

    private Point center;
    private List<Product> products;
    private String colour;

    public Queue(Point center, List<Product> products, String colour) {
        this.center = center;
        this.products = products;
        this.colour = colour;
    }

    public Point getCenter() {
        return center;
    }

    public void setCenter(Point center) {
        this.center = center;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }
}
