package backend.modelClasses.concreteClasses;

import java.util.ArrayList;
import java.util.List;

public class Queue {
    private Point location;
    private List<Integer> products;
    private List<Machine> machines;
    private long ID;
    private int productsnumber;

    public Queue(Point location) {
        this.location = location;
        ID = System.currentTimeMillis();
        machines = new ArrayList<>();
        products = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Queue{" + "location=" + location.toString() + '}';
    }

    public long getID() {
        return ID;
    }

    public void addItem(int item) {
        products.add(item);
        productsnumber++;
        QueueObserver queueObserver = new QueueObserver();
        queueObserver.observer(this);
    }

    public int getItem() {
        if (productsSize() > 0) {
            int temp = products.remove(0);
            productsnumber--;
            return temp;
        }
        return -1;
    }

    public int productsSize() {
        return productsnumber;
    }

    public List<Machine> getMachinesList() {
        return machines;
    }

    public void setMachinesList(List<Machine> m) {
        machines = m;
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point p) {
        location = p;
    }

    public void setProductsList(List<Integer> p) {
        products = p;
    }
}
