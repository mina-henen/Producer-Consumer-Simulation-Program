package backend.modelClasses.concreteClasses;

import java.util.ArrayList;
import java.util.List;

import javax.imageio.plugins.tiff.GeoTIFFTagSet;

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
        /*
         * Diagram diagram = Diagram.getInstance(); int sz = diagram.getQueues().size();
         * diagram.getQueues().get(sz - 1).setProductsnumber(0); while
         * (!diagram.getQueues().get(sz - 1).getProducts().isEmpty()) {
         * diagram.getQueues().get(sz - 1).getProducts().remove(0); }
         */
        return diagram;
        saveDiagram.setProductsList(new ArrayList<>());
        return saveDiagram;
    }
}
