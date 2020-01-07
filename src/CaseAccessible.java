import java.util.ArrayList;

public class CaseAccessible extends Case {
    public boolean creuse = false;
    public boolean aTresor = false;
    public Outil outil = null;
    public ArrayList<Personnage> personages = new ArrayList<Personnage>();

    public CaseAccessible(int x, int y) {
        super(x, y);
    }

    /**
     * set the Tresor
     */
    public void setaTresor(){
        aTresor = true;
    }

    /**
     * set the Outil
     * @param outil the object to set
     */
    public void setOutil(Outil outil) {
        this.outil = outil;
    }
}
