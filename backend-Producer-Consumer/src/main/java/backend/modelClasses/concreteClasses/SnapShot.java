package backend.modelClasses.concreteClasses;

import java.util.ArrayList;
import java.util.List;

public class SnapShot {

    private static SnapShot snp = null;

    private SnapShot() {
    }

    public static SnapShot getInstance() {
        if (snp == null) {
            snp = new SnapShot();
        }
        return snp;
    }
    private Diagram saveDiagram;

    public void save(Diagram diagram) {
        saveDiagram = diagram;
    }

    public Diagram replay() {
        saveDiagram.setProductsList(new ArrayList<>());
        return saveDiagram;
    }
}
