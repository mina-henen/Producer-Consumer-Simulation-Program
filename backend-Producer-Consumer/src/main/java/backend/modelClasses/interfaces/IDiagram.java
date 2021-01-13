package backend.modelClasses.interfaces;

import backend.modelClasses.concreteClasses.Machine;
import backend.modelClasses.concreteClasses.Queue;

import java.util.List;

public interface IDiagram {

    void addMachine(Machine m);

    void addQueue(Queue q);

    void removeMachine(long m);

    void removeQueue(long q);

    void connectMtoQ(long m, long q);

    void connectQtoM(long q, long m);

    List<Queue> getQueues();

    void setQueues(List<Queue> queues);

    List<Machine> getMachines();

    void setMachines(List<Machine> machines);

}
