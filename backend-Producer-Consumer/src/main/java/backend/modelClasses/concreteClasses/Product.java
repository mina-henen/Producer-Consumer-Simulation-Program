package backend.modelClasses.concreteClasses;

public class Product {
    private int startTime;
    private int color;

    public Product() {
        int random;
        startTime =(int) ((Math.random() * 15 + 2) * Global.unitTime);
        random = (int) ((Math.random() * (899999 - 100000)) + 100000);
        color = (random + 100000);
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
