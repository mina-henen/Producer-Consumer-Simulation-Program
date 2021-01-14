package backend.modelClasses.concreteClasses;

import java.util.ArrayList;
import java.util.List;

public class SnapShot {
    private Diagram saveDiagram;

    public void save(Diagram diagram) {
        saveDiagram = diagram;
    }

    public Diagram replay() {
        int sz = saveDiagram.getQueues().size();
        saveDiagram.getQueues().get(sz - 1).setProductsnumber(0);
        while (saveDiagram.getQueues().get(sz - 1).getProducts().size() > 0) {
            saveDiagram.getQueues().get(sz - 1).getProducts().remove(0);
        }
        return saveDiagram;
    }
}
