import java.util.ArrayList;

public class CaseAccessible extends Case {
    public boolean aTresor;

    public CaseAccessible(int x, int y) {
        super(x, y);
    }

    public ArrayList<Personnage> personages = new ArrayList<Personnage>(2);

    public ArrayList<Personnage> getPersonages() {
        return personages;
    }
}
