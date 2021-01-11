package backend.modelClasses.concreteClasses;

import java.util.List;

public class SystemSingltone {

    List<Machine> machineslist;
    List<Queue> queuesList;
    List<Product> productsList;

    private static SystemSingltone systemSingltone = null;

    private SystemSingltone() {
    }

    public static SystemSingltone getInstance() {
        if (systemSingltone == null) {
            systemSingltone = new SystemSingltone();
            return systemSingltone;
        }
        return systemSingltone;
    }

    public List<Machine> getMachinesList() {
        return machineslist;
    }

    public List<Queue> getQueuesList() {
        return queuesList;
    }

    public List<Product> getProductsList() {
        return productsList;
    }

    public void setMachinesList(List<Machine> m) {
        machineslist = m;
    }

    public void setQueuesList(List<Queue> q) {
        queuesList = q;
    }

    public void setProductsList(List<Product> p) {
        productsList = p;
    }

}
