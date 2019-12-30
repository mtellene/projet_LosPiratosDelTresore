import java.util.ArrayList;

public class CaseAccessible extends Case {
    public boolean aTresor = false;
    public Outil outil = null;
    public ArrayList<Personnage> personages = new ArrayList<Personnage>();

    public CaseAccessible(int x, int y) {
        super(x, y);
    }

    public void setaTresor(){
        aTresor = true;
    }

    public void setOutil(Outil outil) {
        this.outil = outil;
    }
}
