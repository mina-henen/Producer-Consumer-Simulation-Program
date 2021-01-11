package backend.modelClasses.concreteClasses;

public class Product implements backend.modelClasses.interfaces.IProduct {

    private double id;
    private String colour;

    public Product(double id, String colour) {
        this.id = id;
        this.colour = colour;
    }

    @Override
    public double getId() {
        return id;
    }

    @Override
    public void setId(double id) {
        this.id = id;
    }

    @Override
    public String getColour() {
        return colour;
    }

    @Override
    public void setColour(String colour) {
        this.colour = colour;
    }
}
