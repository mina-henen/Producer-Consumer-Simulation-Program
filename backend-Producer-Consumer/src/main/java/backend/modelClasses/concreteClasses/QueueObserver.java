package backend.modelClasses.concreteClasses;

public class QueueObserver {
    public void observer(Queue target) {
        for (Machine m : target.getMachinesList()) {
            if (m.getCurrProduct() < 0) {
                Thread thread = new Thread(m);
                thread.start();
                break;
            }
        }
    }
}
