package backend.controllers;

import backend.modelClasses.concreteClasses.*;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class HomeController {

    @PostMapping("/add/machine/")
    public Diagram addMachine(@RequestBody Point point) {
        //Point point = new Point(po[0],po[1]);
        System.out.println(point.toString());
        Diagram diagram = Diagram.getInstance();
        Machine m = new Machine(point);
        diagram.addMachine(m);

        return diagram;
    }

    @PostMapping("/add/queue/")
    public Diagram addQueue(@RequestBody Point point) {
        //Point point = new Point(po[0],po[1]);
        System.out.println(point.toString());
        Diagram diagram = Diagram.getInstance();
        diagram.addQueue(new Queue(point));
        return diagram;
    }

    @PostMapping("/remove/machine/")
    public Diagram removeMachine(@RequestBody String ID) {
        Diagram diagram = Diagram.getInstance();
        diagram.removeMachine(Long.valueOf(ID.substring(0,12)));
        return diagram;
    }

    @PostMapping("/remove/queue/")
    public Diagram removeQueue(@RequestBody String ID) {
        Diagram diagram = Diagram.getInstance();
        diagram.removeQueue(Long.valueOf(ID.substring(0,12)));
        return diagram;
    }

    @PostMapping("/connect/machine/queue/")
    public Diagram connectMToQ(@RequestBody connectionBody connection) {
        Diagram diagram = Diagram.getInstance();
        long mach = Long.valueOf(connection.getId1());
        long que = Long.valueOf(connection.getId2());
        diagram.connectMtoQ(mach,que);
        Machine c1 = null;
        Queue c2 = null;
        for (Machine m:diagram.getMachines()){
            if (m.getID()==mach){
                c1=m;
            }
        }
        for (Queue q:diagram.getQueues()){
            if (q.getID()==que){
                c2=q;
            }
        }
        diagram.connect(c1,c2);

        return diagram;
    }


    @PostMapping("/connect/queue/machine/")
    public Diagram connectQToM(@RequestBody connectionBody connection) {
        Diagram diagram = Diagram.getInstance();
        long que = Long.valueOf(connection.getId1());
        long mach = Long.valueOf(connection.getId2());
        diagram.connectQtoM(que,mach);
        Machine c1 = null;
        Queue c2 = null;
        for (Machine m:diagram.getMachines()){
            if (m.getID()==mach){
                c1=m;
            }
        }
        for (Queue q:diagram.getQueues()){
            if (q.getID()==que){
                c2=q;
            }
        }
        diagram.connect(c2,c1);
        return diagram;
    }

    @GetMapping("/get/updates/")
    public synchronized DiagramCopy getUpdates() {
        DiagramCopy diagramCopy = Diagram.cloneDiagram(Diagram.getInstance());
        /*
        Gson gson = new Gson();
        System.out.println(gson.toJson(diagramCopy));
        String ret = gson.toJson(diagramCopy);
        System.out.println(ret);
        */
        return diagramCopy;
    }

    @PostMapping("/start/simulation/")
    public void startSimulation(@RequestBody NumOfProducts numOfProducts) {
        System.out.println(numOfProducts.getNumOfProducts());
        Simulation simulation = new Simulation();
        simulation.startSimulation(numOfProducts.getNumOfProducts());
    }

    @GetMapping("/clear/")
    public void clear() {
        Diagram diagram = Diagram.clear();
    }
}
