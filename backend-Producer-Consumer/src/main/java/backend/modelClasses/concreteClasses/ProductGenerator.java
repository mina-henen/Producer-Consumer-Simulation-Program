package backend.modelClasses.concreteClasses;

public class ProductGenerator implements Runnable {

    @Override
    public void run() {
        Diagram diagram = Diagram.getInstance();
        try {
            int i = 0;
            for (Product item : diagram.getProductsList()) {
                Thread.sleep(item.getstartTime());
                diagram.getQueues().get(0).addItem(i);
                if (diagram.getQueues().get(0).productsSize() == 1) {
                    // observer
                    // open new thread
                }
                i++;
            }
        } catch (Exception e) {
        }
    }

}
