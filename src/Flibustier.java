import java.util.ArrayList;
import java.util.Random;

public class Flibustier extends Pirate {

    public Flibustier(CaseAccessible c){
        super(c);
    }

    public void attaque() {
        /*
        if case_corsaire = case_flibustier+x
        elseif case_corsaire = case_flibustier+y
        elseif case_corsaire = case_flibustier-x
        elseif case_corsaire = case_flibustier-y
        elseif case_corsaire = case_flibustier+x+y
        elseif case_corsaire = case_flibustier-x+y
        elseif case_corsaire = case_flibustier+x-y
        elseif case_corsaire = case_flibustier-x-y
         */
    }

    public void deplacement(CaseAccessible postion) {
        boolean deplace = false;
        Random random = new Random();
        int x = postion.getX();
        int y = postion.getY();
        int radomI, radomJ;
        while (!deplace) {
            do {
                radomI = random.nextInt(2);
                radomJ = random.nextInt(2);
            } while (radomI == 0 && radomJ == 0);

            if (!Plateau.matrice[x + radomI][y + radomJ].getType().equals("eau")) {
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

    @Override
    void deplacement() {
        boolean deplace = false;
        Case caseProchaine;
        Random random = new Random();
        int x = postion.getX();
        int y = postion.getY();
        int radomI, radomJ , randomType;
        while (!deplace) {
            do {
                radomI = random.nextInt(2);
                radomJ = random.nextInt(2);
            } while (radomI == 0 && radomJ == 0);
            
            randomType = random.nextInt(4);
            switch (randomType){
                case 0 :
                    caseProchaine = Plateau.matrice[x + radomI][y + radomJ];
                    break;
                case 1:
                    caseProchaine = Plateau.matrice[x + radomI][y - radomJ];
                    break;
                case 2:
                    caseProchaine = Plateau.matrice[x - radomI][y + radomJ];
                    break;
                case 3:
                    caseProchaine = Plateau.matrice[x - radomI][y - radomJ];
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + randomType);
            }
            

            if (!caseProchaine.getType().equals("eau")) {
                CaseAccessible caseDeplacement = (CaseAccessible) caseProchaine;
                CaseAccessible caseActuelle = (CaseAccessible) Plateau.matrice[x][y];

                int nbDePersonnages = caseDeplacement.personages.size();
                if (nbDePersonnages < 2) {
                    caseDeplacement.personages.add(this);
                    caseActuelle.personages.remove(this);
                    deplace = true;
                }

                postion = caseDeplacement;

            }
        }
    }
}
