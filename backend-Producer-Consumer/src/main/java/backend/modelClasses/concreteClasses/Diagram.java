package backend.modelClasses.concreteClasses;

import backend.modelClasses.interfaces.IDiagram;

import java.util.List;

//Class for storing all the canvas elements in the frontend
public class Diagram implements IDiagram {
    private List<Queue> queues;
    private List<Machine> machines;
    private List<Connector> connectors;

}
