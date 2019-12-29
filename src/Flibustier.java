import java.util.ArrayList;
import java.util.Random;

public class Flibustier extends Pirate {

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
}
