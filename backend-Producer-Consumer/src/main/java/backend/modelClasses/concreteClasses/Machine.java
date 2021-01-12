package backend.modelClasses.concreteClasses;

import java.util.ArrayList;
import java.util.List;

import backend.modelClasses.interfaces.IMachine;

public class Machine implements Runnable {
    private Point location;
    private int serviceTime;
    private List<Integer> inputQues;
    private int outputQue;
    private int currProduct;
    private int color;

    public Machine(int out) {
        serviceTime = ((int) Math.random() % 15 + 2) * Global.unitTime;
        outputQue = out;
        currProduct = -1;
        inputQues = new ArrayList<>();
    }

    public int getCurrProduct() {
        return currProduct;
    }

    public void setCurrProduct(int curr) {
        currProduct = curr;
    }

    public int getOutputQue() {
        return outputQue;
    }

    public void setinputQues(List<Integer> in) {
        inputQues = in;
    }

    public List<Integer> getinputQue() {
        return inputQues;
    }

    public int getServiceTime() {
        return serviceTime;
    }

    private Point center;
    private String colour;
    private double rate;
    private double id;

    public Machine(Point center, String colour, double rate, double id) {
        this.center = center;
        this.colour = colour;
        this.rate = rate;
        this.id = id;
    }

    public Point getCenter() {
        return center;
    }

    public void setCenter(Point center) {
        this.center = center;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public double getId() {
        return id;
    }

    public void setId(double id) {
        this.id = id;
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point p) {
        location = p;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int colour) {
        this.color = colour;
    }

    @Override
    public void run() {
        Diagram diagram = Diagram.getInstance();
        try {
            for (int q : inputQues) {
                Queue input = diagram.getQueues().get(q);
                if (input.productsSize() > 0) {
                    currProduct = input.getItem();
                    color = diagram.getProductsList().get(currProduct).getColor();
                    Thread.sleep(serviceTime);
                    Queue output = diagram.getQueues().get(outputQue);
                    output.addItem(currProduct);
                    if (output.productsSize() == 1) {
                        // observer
                        // open new thread
                    }
                }
            }
        } catch (Exception e) {
        }
        currProduct = -1;
        color = Global.defualtColor;
    }
}
