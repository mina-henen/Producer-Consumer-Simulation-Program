package backend.modelClasses.concreteClasses;

import java.util.ArrayList;
import java.util.List;

public class SystemBuilder {

    /*
     * public static void main(String[] args) { int x = 6; List<List<Integer>> ques
     * = new ArrayList<>(x); for (int i = 0; i < 6; i++) { ques.add(new
     * ArrayList<>()); } ques.get(0).add(0); ques.get(0).add(3); ques.get(1).add(1);
     * ques.get(1).add(2); ques.get(2).add(4); ques.get(3).add(5);
     * ques.get(3).add(6); ques.get(4).add(5); ques.get(4).add(6);
     * 
     * List<Integer> machines = new ArrayList<>(7); machines.add(1);
     * machines.add(3); machines.add(3); machines.add(4); machines.add(5);
     * machines.add(6); machines.add(6); systemBuild(ques, machines, 10);
     * 
     * }
     */

    private Diagram diagram;

    public void systemBuild(int numberOfProducts) {
        diagram = Diagram.getInstance();
        queueBuild();
        machineBuild();
        productBuild(numberOfProducts);
    }

    private void queueBuild() {

        for (Connection c : diagram.getMachinesIn()) {
            Queue currQueue = null;
            for (Queue q : diagram.getQueues()) {
                if (q.getID() == c.getID1()) {
                    currQueue = q;
                    break;
                }
            }
            for (long m : c.getID2()) {
                for (Machine targetM : diagram.getMachines()) {
                    if (m == targetM.getID()) {
                        currQueue.getMachinesList().add(targetM);
                        targetM.getinputQue().add(currQueue);
                    }
                }
            }
        }
    }

    private void machineBuild() {
        for (Connection c : diagram.getMachinesOut()) {
            Machine targeMachine = null;
            for (Machine m : diagram.getMachines()) {
                if (m.getID() == c.getID1()) {
                    targeMachine = m;
                    break;
                }
            }
            for (Queue q : diagram.getQueues()) {
                if (q.getID() == c.getID2().get(0)) {
                    targeMachine.setOutputQue(q);
                    break;
                }
            }
        }
    }

    private void productBuild(int numberOfProducts) {
        List<Product> temp = new ArrayList<>(numberOfProducts);
        for (int i = 0; i < numberOfProducts; i++) {
            temp.add(new Product());
            System.out.println(temp.get(i).getColor());
        }
        diagram.setProductsList(temp);
    }
}
