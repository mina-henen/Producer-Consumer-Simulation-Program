package backend.modelClasses.concreteClasses;

public class connection {

    private Point p1;
    private Point p2;

    public connection(Point p1, Point p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    public Point getP1() {
        return p1;
    }

    public Point getP2() {
        return p2;
    }

    @Override
    public String toString() {
        return "connection{" +
                "p1=" + p1 +
                ", p2=" + p2 +
                '}';
    }
}
