package backend.controllers;

import backend.modelClasses.concreteClasses.*;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class HomeController {

    @PostMapping("/add/machine/")
    public Diagram addMachine(@RequestBody Machine machine) {
        Diagram diagram = Diagram.getInstance();
        diagram.addMachine(machine);
        return diagram;
    }

    @PostMapping("/add/queue/")
    public Diagram addQueue(@RequestBody Queue queue) {
        Diagram diagram = Diagram.getInstance();
        diagram.addQueue(queue);
        return diagram;
    }

    @PostMapping("/connect/machine/queue/")
    public Diagram connectMToQ(@RequestBody Connection connection) {
        Diagram diagram = Diagram.getInstance();
        for (int i = 0; i < connection.getID2().size(); i++) {
            diagram.connectMtoQ(connection.getID1(), connection.getID2().get(i));
        }
        return diagram;
    }


    @PostMapping("/connect/queue/machine/")
    public Diagram connectQToM(@RequestBody Connection connection) {
        Diagram diagram = Diagram.getInstance();
        for (int i = 0; i < connection.getID2().size(); i++) {
            diagram.connectQtoM(connection.getID2().get(i), connection.getID1());
        }
        return diagram;
    }

    @GetMapping("/get/updates/")
    public Diagram getUpdates() {
        Diagram diagram = Diagram.getInstance();
        return diagram;
    }

    @GetMapping("/start/simulation/")
    public void startSimulation() {
        Simulation.startSimulation();
    }
}
