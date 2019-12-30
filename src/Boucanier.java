import java.util.Random;

public class Boucanier extends Pirate {

    //methodes
    public Boucanier(CaseAccessible c) {
        super(c);
    }

    @Override
    void deplacement() {

    }

    public void attaque() {
        //if case_corsaire = case_boucanier
    }

    public void deplacement(CaseAccessible postion) {
        boolean deplace = false;
        Random random = new Random();
        int x = postion.getX();
        int y = postion.getY();
        int radomI, radomJ;
        while (!deplace) {
            do {
                radomI = random.nextInt(3);
                radomJ = random.nextInt(3);
            } while (radomI == 0 && radomJ == 0);

            if (!Plateau.matrice[x + radomI][y + radomJ].getType().equals("eau")){
                CaseAccessible caseDeplacement = (CaseAccessible) Plateau.matrice[x + radomI][y + radomJ];
                CaseAccessible caseActuelle = (CaseAccessible) Plateau.matrice[x][y];
                //Object[] personages = caseDeplacement.personages.toArray();
                int nbDePersonnages = caseDeplacement.personages.size();
                if (nbDePersonnages < 2) {
                    caseDeplacement.personages.add(this);
                    caseActuelle.personages.remove(this);
                    deplace = true;
                }
            }
        }
    }
}
