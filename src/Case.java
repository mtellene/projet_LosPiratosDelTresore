public class Case {
    private String type;
    private int x;
    private int y;
    public boolean estvisible;

    public Case(int x , int y){
        this.x = x;
        this.y = y;
        this.estvisible = false;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
