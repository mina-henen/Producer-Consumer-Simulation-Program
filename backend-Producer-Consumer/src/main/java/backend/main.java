package backend;

import backend.modelClasses.concreteClasses.*;

public class main {

    public static void main(String[] args) {

        Diagram diagram = Diagram.getInstance();

        for (int i = 0; i < 7; ++i) {
            diagram.addMachine(new Machine(new Point(i * i, i * i)));
            int j = 10000000;
            while (j > 0)
                j--;
        }
        for (Machine m : diagram.getMachines()) {
            System.out.println(m.toString());
        }
        for (int i = 0; i < 6; ++i) {
            diagram.addQueue(new Queue(new Point(i * 2, i * 2)));
            int j = 10000000;
            while (j > 0)
                j--;
        }
        for (Queue q : diagram.getQueues()) {
            System.out.println(q.toString());
        }
        diagram.connectQtoM(diagram.getQueues().get(0).getID(), diagram.getMachines().get(0).getID());
        diagram.connectQtoM(diagram.getQueues().get(0).getID(), diagram.getMachines().get(1).getID());
        diagram.connectQtoM(diagram.getQueues().get(0).getID(), diagram.getMachines().get(2).getID());
        diagram.connectMtoQ(diagram.getMachines().get(0).getID(), diagram.getQueues().get(1).getID());
        diagram.connectMtoQ(diagram.getMachines().get(1).getID(), diagram.getQueues().get(1).getID());
        diagram.connectMtoQ(diagram.getMachines().get(2).getID(), diagram.getQueues().get(2).getID());
        diagram.connectMtoQ(diagram.getMachines().get(1).getID(), diagram.getQueues().get(2).getID());
        diagram.connectQtoM(diagram.getQueues().get(1).getID(), diagram.getMachines().get(3).getID());
        diagram.connectMtoQ(diagram.getMachines().get(3).getID(), diagram.getQueues().get(2).getID());
        diagram.removeMachine(diagram.getMachines().get(0).getID());
        diagram.connectQtoM(diagram.getQueues().get(2).getID(), diagram.getMachines().get(3).getID());
        diagram.connectQtoM(diagram.getQueues().get(2).getID(), diagram.getMachines().get(4).getID());
        diagram.connectMtoQ(diagram.getMachines().get(3).getID(), diagram.getQueues().get(3).getID());
        diagram.connectMtoQ(diagram.getMachines().get(4).getID(), diagram.getQueues().get(4).getID());
        diagram.connectQtoM(diagram.getQueues().get(3).getID(), diagram.getMachines().get(5).getID());
        diagram.connectQtoM(diagram.getQueues().get(4).getID(), diagram.getMachines().get(5).getID());
        diagram.connectMtoQ(diagram.getMachines().get(5).getID(), diagram.getQueues().get(5).getID());
        System.out.println("connection enter machines");
        for (Connection c : diagram.getMachinesIn()) {
            System.out.println(c.toString());
        }
        System.out.println("connection out of machines");
        for (Connection c : diagram.getMachinesOut()) {
            System.out.println(c.toString());
        }
        Simulation simulation = new Simulation();
        simulation.startSimulation(10);
        System.out.println(diagram.getQueues().get(5).productsSize());
    }
}
