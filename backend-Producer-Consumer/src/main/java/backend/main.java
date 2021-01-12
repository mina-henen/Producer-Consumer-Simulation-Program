package backend;

import backend.modelClasses.concreteClasses.*;

public class main {

    public static void main(String[] args){

        Diagram diagram = Diagram.getInstance();

        for (int i = 1; i<4 ; ++i){
            diagram.addMachine(new Machine(new Point(i*i,i*i)));
        }
        for (Machine m:diagram.getMachines()){
            System.out.println(m.toString());
        }
        for (int i = 1; i<7 ; ++i){
            diagram.addQueue(new Queue(new Point(i*2,i*2)));
        }
        for (Queue q:diagram.getQueues()){
            System.out.println(q.toString());
        }
        diagram.connect(diagram.getMachines().get(2),diagram.getQueues().get(3));
        diagram.connect(diagram.getMachines().get(1),diagram.getQueues().get(0));
        diagram.connect(diagram.getMachines().get(0),diagram.getQueues().get(2));
        diagram.connect(diagram.getQueues().get(3),diagram.getMachines().get(1));
        diagram.connect(diagram.getQueues().get(1),diagram.getMachines().get(1));
        diagram.connect(diagram.getQueues().get(5),diagram.getMachines().get(1));
        diagram.connect(diagram.getQueues().get(2),diagram.getMachines().get(1));

        System.out.println("connection enter machines");
        for (connection c: diagram.getMachinesIn()){
            System.out.println(c.toString());
        }
        System.out.println("connection out of machines");
        for (connection c: diagram.getMachinesOut()){
            System.out.println(c.toString());
        }
    }
}
