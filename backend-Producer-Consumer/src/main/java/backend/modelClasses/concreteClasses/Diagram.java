package backend.modelClasses.concreteClasses;

import backend.controllers.DiagramCopy;
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
    private List<connectionLine> machinesInL = new ArrayList<>();
    private List<connectionLine> machinesOutL = new ArrayList<>();

    public List<Connection> getMachinesIn() {
        return machinesIn;
    }

    public List<Connection> getMachinesOut() {
        return machinesOut;
    }

    public List<connectionLine> getMachinesInL() {
        return machinesInL;
    }

    public List<connectionLine> getMachinesOutL() {
        return machinesOutL;
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
            if (m == mach.getID() / 10) {
                for (connectionLine l : machinesOutL) {
                    if (l.getP1().toString().equals(mach.getLocation().toString())) {
                        machinesOutL.remove(l);
                        break;
                    }
                }
                for (int i = 0; i < machinesInL.size(); i++) {
                    connectionLine l = machinesInL.get(i);
                    if (l.getP2().toString().equals(mach.getLocation().toString())) {
                        machinesInL.remove(l);
                        i = i - 1;
                    }
                }
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
            if (q == qu.getID() / 10) {
                for (connectionLine l : machinesOutL) {
                    if (l.getP2().toString().equals(qu.getLocation().toString())) {
                        machinesOutL.remove(l);
                    }
                }
                for (int i = 0; i < machinesInL.size(); i++) {
                    connectionLine l = machinesInL.get(i);
                    if (l.getP1().toString().equals(qu.getLocation().toString())) {
                        machinesInL.remove(l);
                        i = i - 1;
                    }
                }
                queues.remove(qu);
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
        for (Connection c : machinesIn) {
            if (c.getID1() == q) {
                if (c.getID2().indexOf(m) != -1) {
                    return;
                }
            }
        }
        Connection con = new Connection(m, q);
        machinesOut.add(con);
    }

    @Override
    public void connectQtoM(long q, long m) {
        for (Connection c : machinesOut) {
            if (c.getID1() == m) {
                if (c.getID2().indexOf(q) != -1) {
                    return;
                }
            }
        }
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

    public void connect(Machine m, Queue q) {
        for (connectionLine c : machinesOutL) {
            if (c.getP1().toString().equals(m.getLocation().toString())) {
                machinesOutL.remove(c);
                break;
            }
        }
        connectionLine con = new connectionLine(m.getLocation(), q.getLocation());
        machinesOutL.add(con);
    }

    public void connect(Queue q, Machine m) {
        connectionLine con = new connectionLine(q.getLocation(), m.getLocation());
        machinesInL.add(con);
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

    public static Diagram clear() {
        diagram = new Diagram();
        return diagram;
    }


    public static Queue cloneQueue(Queue queue) {
        Queue q = new Queue();
        q.setLocation(new Point(queue.getLocation().getX(), queue.getLocation().getY()));
        q.setID(queue.getID());
        q.setProductsnumber(queue.getProductsnumber());
        List<Integer> products = new ArrayList<>();
        /*
        List<Machine> machines = new ArrayList<>();
        for (int i = 0; i < queue.getMachines().size(); i++) {
            Machine m = Diagram.cloneMachine(queue.getMachines().get(i));
            machines.add(m);
        }
        */
        for (int i = 0; i < queue.getProducts().size(); i++) {
            int m = queue.getProducts().get(i);
            products.add(m);
        }
        q.setProductsList(products);
        //q.setMachinesList(machines);
        return q;
    }

    public static Machine cloneMachine(Machine machine) {
        Machine m = new Machine();
        m.setLocation(new Point(machine.getLocation().getX(), machine.getLocation().getY()));
        m.setID(machine.getID());
        m.setServiceTime(machine.getServiceTime());
        m.setCurrProduct(machine.getCurrProduct());
        for (int i = 0; i < machine.getinputQue().size(); i++) {
            Queue q = Diagram.cloneQueue(machine.getinputQue().get(i));
            m.getinputQue().add(q);
        }
        Queue q = Diagram.cloneQueue(machine.getOutputQue());
        m.setOutputQue(q);
        return m;
    }

    public static Product cloneProduct(Product product) {
        Product p = new Product();
        p.setColor(product.getColor());
        p.setStartTime(product.getstartTime());
        return p;
    }
    /*public synchronized static Connection cloneConnection(Machine machine) {
        Machine m = new Machine();
        m.setLocation(new Point(machine.getLocation().getX(), machine.getLocation().getY()));
        m.setID(machine.getID());
        m.setServiceTime(machine.getServiceTime());
        m.setCurrProduct(machine.getCurrProduct());
        for (int i = 0; i < machine.getinputQue().size(); i++) {
            Queue q = Diagram.cloneQueue(machine.getinputQue().get(i));
            m.getinputQue().add(q);
        }
        Queue q = Diagram.cloneQueue(machine.getOutputQue());
        m.setOutputQue(q);
        return m;
    }
    */

    public static DiagramCopy cloneDiagram(Diagram diagram) {
        DiagramCopy copy = new DiagramCopy();
        for (int i = 0; i < diagram.getMachines().size(); i++) {
            copy.getMachines().add(Diagram.cloneMachine(diagram.getMachines().get(i)));
        }
        for (int i = 0; i < diagram.getQueues().size(); i++) {
            copy.getQueues().add(Diagram.cloneQueue(diagram.getQueues().get(i)));
        }
        for (int i = 0; i < diagram.getMachinesIn().size(); i++) {
            copy.getMachinesIn().add(diagram.getMachinesIn().get(i));
        }
        for (int i = 0; i < diagram.getMachinesOut().size(); i++) {
            copy.getMachinesOut().add(diagram.getMachinesOut().get(i));
        }
        for (int i = 0; i < diagram.getProductsList().size(); i++) {
            copy.getProductsList().add(Diagram.cloneProduct(diagram.getProductsList().get(i)));
        }
        for (int i = 0; i < diagram.getMachinesInL().size(); i++) {
            copy.getMachinesInL().add(diagram.getMachinesInL().get(i));
        }
        for (int i = 0; i < diagram.getMachinesOutL().size(); i++) {
            copy.getMachinesOutL().add(diagram.getMachinesOutL().get(i));
        }
        return copy;
    }
}
