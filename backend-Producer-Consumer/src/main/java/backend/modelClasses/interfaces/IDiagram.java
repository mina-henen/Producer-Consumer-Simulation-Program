package backend.modelClasses.interfaces;

import backend.modelClasses.concreteClasses.Connector;
import backend.modelClasses.concreteClasses.Machine;
import backend.modelClasses.concreteClasses.Queue;

import java.util.List;

public interface IDiagram {
    List<Queue> getQueues();

    void setQueues(List<Queue> queues);

    List<Machine> getMachines();

    void setMachines(List<Machine> machines);

    List<Connector> getConnectors();

    void setConnectors(List<Connector> connectors);
}
