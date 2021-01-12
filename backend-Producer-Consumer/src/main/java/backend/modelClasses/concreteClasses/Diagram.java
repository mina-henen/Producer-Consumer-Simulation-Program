package backend.modelClasses.concreteClasses;

import backend.modelClasses.interfaces.IDiagram;

import java.util.List;

//Class for storing all the canvas elements in the frontend
public class Diagram implements IDiagram {
    private List<Queue> queues;
    private List<Machine> machines;
    private List<Product> productsList;
    // private List<Connector> connectors;

    /*
     * public Diagram(List<Queue> queues, List<Machine> machines, List<Connector>
     * connectors) { this.queues = queues; this.machines = machines; this.connectors
     * = connectors; }
     */

    private static Diagram diagram = null;

    private Diagram() {
    }

    public static Diagram getInstance() {
        if (diagram == null) {
            diagram = new Diagram();
        }
        return diagram;
    }

    @Override
    public List<Queue> getQueues() {
        return queues;
    }

    @Override
    public void setQueues(List<Queue> queues) {
        this.queues = queues;
    }

    @Override
    public List<Machine> getMachines() {
        return machines;
    }

    @Override
    public void setMachines(List<Machine> machines) {
        this.machines = machines;
    }

    public List<Product> getProductsList() {
        return productsList;
    }

    public void setProductsList(List<Product> p) {
        productsList = p;
    }

    @Override
    public List<Connector> getConnectors() {
        // return connectors;
        return null;
    }

    @Override
    public void setConnectors(List<Connector> connectors) {
        // this.connectors = connectors;
    }
}
