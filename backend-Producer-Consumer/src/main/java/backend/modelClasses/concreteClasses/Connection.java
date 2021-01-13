package backend.modelClasses.concreteClasses;

import java.util.ArrayList;
import java.util.List;

public class Connection {
    private long ID1;
    private List<Long> ID2;

    public Connection(long ID1, long id) {
        this.ID1 = ID1;
        this.ID2 = new ArrayList<>();
        ID2.add(id);
    }

    public long getID1() {
        return ID1;
    }

    public List<Long> getID2() {
        return ID2;
    }

    @Override
    public String toString() {
        return "connection{" + "ID1=" + ID1 + ", ID2=" + ID2.get(0) + '}';
    }
}
