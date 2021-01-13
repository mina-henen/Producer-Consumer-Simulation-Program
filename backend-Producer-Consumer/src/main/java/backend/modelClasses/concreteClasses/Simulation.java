package backend.modelClasses.concreteClasses;

public class Simulation {
    public static void startSimulation() {
        Diagram diagram = Diagram.getInstance();
        SnapShot snapShot = new SnapShot();
        snapShot.save(diagram);
        Thread threads = new Thread(new ProductGenerator());
        threads.start();
    }

}
