import java.util.ArrayList;

public class CaseAccessible extends Case {
    public boolean aTresor;
    public ArrayList<Personnage> personages = new ArrayList<Personnage>();

    public CaseAccessible(int x, int y) {
        super(x, y);
    }


    public ArrayList<Personnage> getPersonages() {
        return personages;
    }
}
