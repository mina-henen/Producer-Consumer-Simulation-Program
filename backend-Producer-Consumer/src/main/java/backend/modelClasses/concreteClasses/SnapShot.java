package backend.modelClasses.concreteClasses;

import java.util.ArrayList;
import java.util.List;

public class SnapShot {
    private Diagram saveDiagram;

    public void save(Diagram diagram) {
        saveDiagram = diagram;
    }

    public Diagram replay() {
        for (Queue q : saveDiagram.getQueues()) {
            q.setProductsList(new ArrayList<>());
        }
        for (Machine m : saveDiagram.getMachines()) {
            m.setCurrProduct(-1);
        }
        return saveDiagram;
    }
}







