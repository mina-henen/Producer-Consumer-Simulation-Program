package backend.modelClasses.concreteClasses;

import java.util.List;

public class SystemSingletone {

    List<Machine> machineslist;
    List<Queue> queuesList;
    List<Product> productsList;

    private static SystemSingletone systemSingletone = null;

    private SystemSingletone() {
    }

    public static SystemSingletone getInstance() {
        if (systemSingletone == null) {
            systemSingletone = new SystemSingletone();
            return systemSingletone;
        }
        return systemSingletone;
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
