package backend.modelClasses.concreteClasses;

public class ProductGenerator implements Runnable {

    @Override
    public void run() {
        Diagram diagram = Diagram.getInstance();
        try {
            for (Product item : diagram.getProductsList()) {
                Thread.sleep(item.getstartTime());
                diagram.getQueues().get(0).addItem(item.getColor());
            }
        } catch (Exception e) {
        }
    }

}
