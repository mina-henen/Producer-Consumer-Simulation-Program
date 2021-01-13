package backend.modelClasses.concreteClasses;

public class connection {

    private Point p1;
    private Point p2;
    private long ID1;
    private long ID2;

    public connection(Point p1, long ID1, Point p2, long ID2) {
        this.p1 = p1;
        this.p2 = p2;
        this.ID1 = ID1;
        this.ID2 = ID2;
    }

    public Point getP1() {
        return p1;
    }

    public Point getP2() {
        return p2;
    }

    public long getID1() {
        return ID1;
    }

    public long getID2() {
        return ID2;
    }

    @Override
    public String toString() {
        return "connection{" +
                "p1=" + p1 +
                ", p2=" + p2 +
                '}';
    }
}
