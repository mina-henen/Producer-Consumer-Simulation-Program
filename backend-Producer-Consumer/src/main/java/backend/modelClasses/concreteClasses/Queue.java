package backend.modelClasses.concreteClasses;

import java.util.ArrayList;
import java.util.List;

public class Queue {
    private Point location;
    private List<Integer> products;
    private List<Integer> machines;

    public Queue(List<Integer> m) {
        machines = m;
        products = new ArrayList<>();
    }

    public void addItem(int item) {
        products.add(item);
        /// observer
    }

    public int getItem() {
        if (productsSize() > 0) {
            return products.remove(0);
        }
        return -1;
    }

    public int productsSize() {
        return products.size();
    }

    public List<Integer> getMachinesList() {
        return machines;
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point p) {
        location = p;
    }
}
