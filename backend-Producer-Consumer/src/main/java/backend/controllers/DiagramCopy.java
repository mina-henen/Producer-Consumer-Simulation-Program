package backend.controllers;

import backend.modelClasses.concreteClasses.*;

import java.util.ArrayList;
import java.util.List;

public class DiagramCopy {
    private List<Queue> queues;
    private List<Machine> machines;
    private List<Product> productsList;
    private List<Connection> machinesIn;
    private List<Connection> machinesOut;
    private List<connectionLine> machinesInL;
    private List<connectionLine> machinesOutL;

    public DiagramCopy() {
        queues = new ArrayList<>();
        machines = new ArrayList<>();
        productsList = new ArrayList<>();
        machinesIn = new ArrayList<>();
        machinesOut = new ArrayList<>();
        machinesInL = new ArrayList<>();
        machinesOutL = new ArrayList<>();
    }

    public List<Queue> getQueues() {
        return queues;
    }

    public void setQueues(List<Queue> queues) {
        this.queues = queues;
    }

    public List<Machine> getMachines() {
        return machines;
    }

    public void setMachines(List<Machine> machines) {
        this.machines = machines;
    }

    public List<Product> getProductsList() {
        return productsList;
    }

    public void setProductsList(List<Product> productsList) {
        this.productsList = productsList;
    }

    public List<Connection> getMachinesIn() {
        return machinesIn;
    }

    public void setMachinesIn(List<Connection> machinesIn) {
        this.machinesIn = machinesIn;
    }

    public List<Connection> getMachinesOut() {
        return machinesOut;
    }

    public void setMachinesOut(List<Connection> machinesOut) {
        this.machinesOut = machinesOut;
    }

    public List<connectionLine> getMachinesInL() {
        return machinesInL;
    }

    public void setMachinesInL(List<connectionLine> machinesInL) {
        this.machinesInL = machinesInL;
    }

    public List<connectionLine> getMachinesOutL() {
        return machinesOutL;
    }

    public void setMachinesOutL(List<connectionLine> machinesOutL) {
        this.machinesOutL = machinesOutL;
    }


}
