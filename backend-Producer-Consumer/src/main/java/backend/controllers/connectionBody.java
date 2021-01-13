package backend.controllers;

public class connectionBody {
    private String id1;
    private String id2;

    public connectionBody(String ID1, String ID2) {
        this.id1 = ID1;
        this.id2 = ID2;
    }

    public String getId1() {
        return id1;
    }

    public String getId2() {
        return id2;
    }
}
