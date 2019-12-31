import java.util.Random;

public  class Personnage {
    public CaseAccessible position;

    public Personnage(CaseAccessible c){
        position = c;
    }

    public void deplacement(int nombreDeCase) {
        boolean deplace = false;
        Case caseProchaine = null;
        Random random = new Random();
        int x = position.getX();
        int y = position.getY();
        int radomI, radomJ , randomType;
        while (!deplace) {
            do {
                radomI = random.nextInt(nombreDeCase+1);
                radomJ = random.nextInt(nombreDeCase+1);
            } while (radomI == 0 && radomJ == 0);

            boolean caseExist = false ;
            do{
                randomType = random.nextInt(4);
                switch (randomType){
                    case 0 :
                        if ( x + radomI < Plateau.n && y + radomJ < Plateau.n){
                            caseExist = true;
                            caseProchaine = Plateau.matrice[x + radomI][y + radomJ];
                        }
                        break;
                    case 1:
                        if ( x + radomI < Plateau.n && y - radomJ >= 0){
                            caseExist = true;
                            caseProchaine = Plateau.matrice[x + radomI][y - radomJ];
                        }
                        break;
                    case 2:
                        if ( x - radomI >= 0 && y + radomJ < Plateau.n){
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


            if (this instanceof Corsaire){
                if (!caseProchaine.getType().equals("eau")) {
                    CaseAccessible caseDeplacement = (CaseAccessible) caseProchaine;
                    if (caseProchaine.getType().equals("foret")){
                        if (((Corsaire) this).machette != null){

                            if (caseDeplacement.personages.size() < 2) {
                                CaseAccessible caseActuelle = (CaseAccessible) Plateau.matrice[x][y];
                                caseActuelle.personages.remove(this);
                                caseDeplacement.personages.add(this);
                                deplace = true;

                                position = caseDeplacement;
                            }
                        }
                    }else{
                        if (caseDeplacement.personages.size() < 2) {
                            CaseAccessible caseActuelle = (CaseAccessible) Plateau.matrice[x][y];
                            caseActuelle.personages.remove(this);
                            caseDeplacement.personages.add(this);
                            deplace = true;

                            position = caseDeplacement;
                        }
                    }
                }
            }else{
                if (!caseProchaine.getType().equals("eau")) {
                    CaseAccessible caseDeplacement = (CaseAccessible) caseProchaine;
                    if (caseDeplacement.personages.size() < 2 ){
                        CaseAccessible caseActuelle = (CaseAccessible) Plateau.matrice[x][y];
                        caseActuelle.personages.remove(this);
                        caseDeplacement.personages.add(this);
                        deplace = true;

                        position = caseDeplacement;
                    }
                }
            }
        }
    }
}
