package backend.modelClasses.concreteClasses;

public class Product {
    private int startTime;
    private int color;

    public Product() {
        startTime = ((int) Math.random() % 15 + 2) * Global.unitTime;
        color = ((int) Math.random() % 0xFFFFF0) + 0x00000A;
    }

    public int getColor() {
        return color;
    }

    public int getstartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
