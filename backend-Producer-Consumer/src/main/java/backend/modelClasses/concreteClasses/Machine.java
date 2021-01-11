package backend.modelClasses.concreteClasses;

import java.util.*;

import backend.modelClasses.interfaces.IMachine;

public class Machine implements IMachine {
    private int serviceTime;
    private List<Integer> inputQues;
    private int outputQue;
    private int currProduct;

    public Machine(int out) {
        serviceTime = ((int) Math.random() % 15 + 2) * Global.unitTime;
        outputQue = out;
        currProduct = -1;
        inputQues = new ArrayList<>();
    }

    public int getCurrProduct() {
        return currProduct;
    }

    public void setCurrProduct(int curr) {
        currProduct = curr;
    }

    public int getOutputQue() {
        return outputQue;
    }

    public void setinputQues(List<Integer> in) {
        inputQues = in;
    }

    public List<Integer> getinputQue() {
        return inputQues;
    }

    public int getServiceTime() {
        return serviceTime;
    }

}
