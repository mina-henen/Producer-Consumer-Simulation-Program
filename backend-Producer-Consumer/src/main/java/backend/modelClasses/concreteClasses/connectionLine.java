package backend.modelClasses.concreteClasses;

public class connectionLine {

    private Point p1;
    private Point p2;

    public connectionLine(Point p1, Point p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    public Point getP1() {
        return p1;
    }

    public Point getP2() {
        return p2;
    }

}
