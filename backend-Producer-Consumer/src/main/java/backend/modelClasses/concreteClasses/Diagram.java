package backend.modelClasses.concreteClasses;

import backend.modelClasses.interfaces.IDiagram;
import org.springframework.boot.test.util.TestPropertyValues;

import java.util.ArrayList;
import java.util.List;

//Class for storing all the canvas elements in the frontend
public class Diagram implements IDiagram {
    private List<Queue> queues= new ArrayList<>();
    private List<Machine> machines = new ArrayList<>();
    private List<Product> productsList;
    private List<connection> machinesIn= new ArrayList<>();
    private List<connection> machinesOut= new ArrayList<>();

    public List<connection> getMachinesIn() {
        return machinesIn;
    }

    public List<connection> getMachinesOut() {
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
    public void removeMachine(Machine m) {
        for (connection c: machinesOut){
            if (c.getP1().toString().equals(m.getLocation().toString())){
                machinesOut.remove(c);
                break;
            }
        }
        for (connection c: machinesIn){
            if (c.getP2().toString().equals(m.getLocation().toString())){
                machinesOut.remove(c);
            }
        }
        for(Machine mach: machines){
            if (m.getID()== mach.getID()){
                machines.remove(mach);
                break;
            }
        }
    }

    @Override
    public void removeQueue(Queue q) {
        for (connection c: machinesOut){
            if (c.getP2().toString().equals(q.getLocation().toString())){
                machinesOut.remove(c);
            }
        }
        for (connection c: machinesIn){
            if (c.getP1().toString().equals(q.getLocation().toString())){
                machinesOut.remove(c);
            }
        }
        for(Queue qu: queues){
            if (q.getID()== qu.getID()){
                machines.remove(qu);
                break;
            }
        }
    }

    @Override
    public void connect(Machine m, Queue q) {
        for (connection c : machinesOut){
            if(c.getP1().toString().equals(m.getLocation().toString())){
                return;
            }
        }
        connection con = new connection(m.getLocation(),m.getID(),q.getLocation(),q.getID());
        machinesOut.add(con);
    }

    @Override
    public void connect(Queue q, Machine m) {
        connection con = new connection(q.getLocation(),q.getID(), m.getLocation(),m.getID());
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
