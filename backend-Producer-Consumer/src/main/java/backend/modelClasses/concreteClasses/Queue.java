package backend.modelClasses.concreteClasses;

import java.util.ArrayList;
import java.util.List;

public class Queue {
    private Point location;
    private List<Integer> products;
    private List<Machine> machines;
    private long ID;
    private int productsnumber;

    public List<Integer> getProducts() {
        return products;
    }

    public List<Machine> getMachines() {
        return machines;
    }

    public int getProductsnumber() {
        return productsnumber;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public void setProductsnumber(int productsnumber) {
        this.productsnumber = productsnumber;
    }

    public Queue() {
    }

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
        /*
         * if (productsnumber == 1) { System.out.println("operating");
         * System.out.println(ID); System.out.println(products.toString()); } if
         * (productsnumber == 10) { System.out.println("Finished");
         * System.out.println(ID); System.out.println(products.toString()); Diagram
         * diagram = Diagram.getInstance();
         * System.out.println(diagram.getMachinesInL().size()); }
         */
        System.out.println("ADD: queueId: " + this.ID % 100 + " have " + this.productsnumber % 100 + "products");

        QueueObserver queueObserver = new QueueObserver();
        queueObserver.observer(this);
    }

    public int getItem() {
        if (productsSize() > 0) {
            int temp = products.remove(0);
            productsnumber--;
            System.out
                    .println("REmove : queueId: " + this.ID % 100 + " have " + this.productsnumber % 100 + "products");
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
