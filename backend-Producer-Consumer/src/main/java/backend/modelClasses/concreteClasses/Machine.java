package backend.modelClasses.concreteClasses;

import java.util.ArrayList;
import java.util.List;

public class Machine implements Runnable {
    private Point location;
    private int serviceTime;
    private List<Queue> inputQues;
    private Queue outputQue;
    private int currProduct;
    private long ID;

    public Machine() {
        location = new Point();
        inputQues = new ArrayList<>();
        outputQue = new Queue();
    }

    public void setServiceTime(int serviceTime) {
        this.serviceTime = serviceTime;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public Machine(Point location) {
        this.location = location;
        ID = System.currentTimeMillis();
        int random = (int) ((Math.random() * (10)) + 3);
        serviceTime = (random * Global.unitTime);
        outputQue = null;
        currProduct = -1;
        inputQues = new ArrayList<>();
    }

    public long getID() {
        return ID;
    }

    public int getCurrProduct() {
        return currProduct;
    }

    public void setCurrProduct(int curr) {
        currProduct = curr;
    }

    public Queue getOutputQue() {
        return outputQue;
    }

    public void setOutputQue(Queue q) {
        outputQue = q;
    }

    public void setInputQues(List<Queue> in) {
        inputQues = in;
    }

    public List<Queue> getinputQue() {
        return inputQues;
    }

    public int getServiceTime() {
        return serviceTime;
    }

    @Override
    public String toString() {
        return "Machine{" + "location=" + location.toString() + '}';
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point p) {
        location = p;
    }

    @Override
    public void run() {
        try {
            boolean flag = true;
            while (true) {
                flag = true;
                for (Queue input : inputQues) {
                    while (input.productsSize() > 0) {
                        flag = false;
                        currProduct = input.getItem();
                        System.out.println("MAchineId: " + this.ID % 100 + " have " + this.currProduct % 100 + "color");
                        /*
                         * System.out.println(m.getID()); System.out.println(this.ID);
                         * System.out.println(m.getCurrProduct()); System.out.println("this.currProduct"
                         * + this.currProduct);
                         */
                        Thread.sleep(serviceTime);
                        outputQue.addItem(currProduct);
                    }
                }
                currProduct = -1;
                if (flag)
                    break;
            }
        } catch (Exception e) {
        }
    }
}
