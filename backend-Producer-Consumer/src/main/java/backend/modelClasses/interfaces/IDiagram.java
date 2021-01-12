package backend.modelClasses.interfaces;

import backend.modelClasses.concreteClasses.Machine;
import backend.modelClasses.concreteClasses.Queue;

import java.util.List;

public interface IDiagram {

    void addMachine(Machine m);

    void addQueue(Queue q);

    void removeMachine(Machine m);

    void removeQueue(Queue q);

    void connect(Machine m, Queue q);

    void connect(Queue q, Machine m);

    List<Queue> getQueues();

    void setQueues(List<Queue> queues);

    List<Machine> getMachines();

    void setMachines(List<Machine> machines);


}
