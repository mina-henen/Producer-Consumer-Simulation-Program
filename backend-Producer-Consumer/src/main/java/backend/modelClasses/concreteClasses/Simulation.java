package backend.modelClasses.concreteClasses;

import java.util.ArrayList;
import java.util.List;

public class Simulation {
    private List<Thread> threads;

    public Simulation(List<Machine> machines) {
        threads = new ArrayList<>(machines.size() + 1);
        threads.add(new Thread(new ProductGenerator()));
        for (int i = 0; i < machines.size(); i++) {
            threads.add(new Thread(machines.get(i)));
        }
        threads.get(0).start();
    }

}
