package backend.modelClasses.concreteClasses;

import javax.crypto.Mac;

public class Simulation {

    public void startSimulation(int numberOfProducts) {
        Diagram diagram = Diagram.getInstance();
        SnapShot snapShot = SnapShot.getInstance();
        snapShot.save();
        SystemBuilder systemBuilder = new SystemBuilder();
        systemBuilder.systemBuild(numberOfProducts);
        /*
         * int i = 0; for (Queue q : diagram.getQueues()) { System.out.print("Queue " +
         * i + ": "); for (Machine m : q.getMachinesList()) { int j = 0; for (Machine
         * target : diagram.getMachines()) { if (m.getID() == target.getID()) {
         * System.out.print(j + " "); break; } j++; } System.out.println(); } i++; } i =
         * 0; for (Machine m : diagram.getMachines()) { System.out.print("Machine " + i
         * + ": "); Queue q = m.getOutputQue(); int j = 0; for (Queue target :
         * diagram.getQueues()) { if (q.getID() == target.getID()) { System.out.print(j
         * + " "); break; } j++; } System.out.println(); i++; }
         */
        Thread threads = new Thread(new ProductGenerator());
        threads.start();
    }

}
