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

    /**
     *
     * @return the x coordinate
     */
    public int getX() {
        return x;
    }

    /**
     *
     * @return the y coordinate
     */
    public int getY() {
        return y;
    }

    /**
     *
     * @return the type of the case ( eau , foret , sable )
     */
    public String getType() {
        return type;
    }

    /**
     * set the type of the case
     *
     * @param type the type of the case
     */
    public void setType(String type) {
        this.type = type;
    }

}
