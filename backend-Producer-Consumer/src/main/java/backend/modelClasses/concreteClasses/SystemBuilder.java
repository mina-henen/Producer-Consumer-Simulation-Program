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

    private SystemSingletone systemSingletone;

    public void systemBuild(List<List<Integer>> ques, List<Integer> machines, int numberOfProducts) {
        systemSingletone = SystemSingletone.getInstance();
        queueBuild(ques);
        machineBuild(ques, machines);
        productBuild(numberOfProducts);
        /// copy to memo
    }

    private void queueBuild(List<List<Integer>> ques) {
        List<Queue> temp = new ArrayList<>(ques.size());
        for (int i = 0; i < ques.size(); i++) {
            temp.add(new Queue(ques.get(i)));
        }
        systemSingletone.setQueuesList(temp);
    }

    private void machineBuild(List<List<Integer>> ques, List<Integer> machines) {
        List<Machine> temp = new ArrayList<>(machines.size());
        for (int i = 0; i < machines.size(); i++) {
            temp.add(new Machine(machines.get(i)));
        }
        List<List<Integer>> inputQues = new ArrayList<>(machines.size());
        for (int i = 0; i < machines.size(); i++) {
            inputQues.add(new ArrayList<>());
        }
        for (int i = 0; i < ques.size(); i++) {
            for (int j = 0; j < ques.get(i).size(); j++) {
                inputQues.get(ques.get(i).get(j)).add(i);
            }
        }
        for (int i = 0; i < machines.size(); i++) {
            temp.get(i).setinputQues(inputQues.get(i));
        }
        systemSingletone.setMachinesList(temp);
    }

    private void productBuild(int numberOfProducts) {
        List<Product> temp = new ArrayList<>(numberOfProducts);
        for (int i = 0; i < numberOfProducts; i++) {
            temp.add(new Product());
        }
        systemSingletone.setProductsList(temp);
    }
}
