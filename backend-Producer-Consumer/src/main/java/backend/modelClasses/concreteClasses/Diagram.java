package backend.modelClasses.concreteClasses;

import backend.modelClasses.interfaces.IDiagram;

import java.util.ArrayList;
import java.util.List;

//Class for storing all the canvas elements in the frontend
public class Diagram implements IDiagram {
    private List<Queue> queues = new ArrayList<>();
    private List<Machine> machines = new ArrayList<>();
    private List<Product> productsList;
    private List<Connection> machinesIn = new ArrayList<>();
    private List<Connection> machinesOut = new ArrayList<>();

    public List<Connection> getMachinesIn() {
        return machinesIn;
    }

    public List<Connection> getMachinesOut() {
        return machinesOut;
    }
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
    public void addMachine(Machine m) {
        machines.add(m);
    }

    @Override
    public void addQueue(Queue q) {
        queues.add(q);
    }

    @Override
    public void removeMachine(long m) {
        for (Connection c : machinesOut) {
            if (c.getID1() == m) {
                machinesOut.remove(c);
                break;
            }
        }
        for (Connection c : machinesIn) {
            c.getID2().remove(m);
        }
        for (Machine mach : machines) {
            if (m == mach.getID()) {
                machines.remove(mach);
                break;
            }
        }
    }

    @Override
    public void removeQueue(long q) {
        for (Connection c : machinesOut) {
            c.getID2().remove(q);
        }
        for (Connection c : machinesIn) {
            if (c.getID1() == q) {
                machinesOut.remove(c);
            }
        }
        for (Queue qu : queues) {
            if (q == qu.getID()) {
                machines.remove(qu);
                break;
            }
        }
    }

    @Override
    public void connectMtoQ(long m, long q) {
        for (Connection c : machinesOut) {
            if (c.getID1() == m) {
                return;
            }
        }
        Connection con = new Connection(m, q);
        machinesOut.add(con);
    }

    @Override
    public void connectQtoM(long q, long m) {
        for (Connection c : machinesIn) {
            if (c.getID1() == q) {
                for (long target : c.getID2()) {
                    if (m == target)
                        return;
                }
                c.getID2().add(m);
                return;
            }
        }
        Connection con = new Connection(q, m);
        machinesIn.add(con);
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

}
