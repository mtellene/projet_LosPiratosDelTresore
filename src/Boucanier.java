import java.util.Random;

public class Boucanier extends Pirate {

    //methodes
    public Boucanier(CaseAccessible c) {
        super(c);
    }

    public void attaque() {
        //if case_corsaire = case_boucanier
    }

    @Override
    void deplacement() {
        boolean deplace = false;
        Case caseProchaine = null;
        Random random = new Random();
        int x = postion.getX();
        int y = postion.getY();
        int radomI, radomJ , randomType;
        while (!deplace) {
            do {
                radomI = random.nextInt(3);
                radomJ = random.nextInt(3);
            } while (radomI == 0 && radomJ == 0);

            boolean caseExist = false ;
            do{
                randomType = random.nextInt(4);
                switch (randomType){
                    case 0 :
                        if ( x + radomI < 15 && y + radomJ < 15){
                            caseExist = true;
                            caseProchaine = Plateau.matrice[x + radomI][y + radomJ];
                        }
                        break;
                    case 1:
                        if ( x + radomI < 15 && y - radomJ >= 0){
                            caseExist = true;
                            caseProchaine = Plateau.matrice[x + radomI][y - radomJ];
                        }
                        break;
                    case 2:
                        if ( x - radomI >= 0 && y + radomJ < 15){
                            caseExist = true;
                            caseProchaine = Plateau.matrice[x - radomI][y + radomJ];
                        }
                        break;
                    case 3:
                        if ( x - radomI >= 0 && y - radomJ >= 0){
                            caseExist = true;
                            caseProchaine = Plateau.matrice[x - radomI][y - radomJ];
                        }
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + randomType);
                }
            }while(!caseExist);


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
