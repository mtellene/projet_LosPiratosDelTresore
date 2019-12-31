import javafx.scene.input.MouseEvent;

public class CorsaireJoueur extends Corsaire {

    public CorsaireJoueur(CaseAccessible c) {
        super(c);
    }

    public boolean verifClick(int x, int y) {
        //provisoire
        //boolean machette = false;
        int xPosition = postion.getX() * 40;
        int yPosition = postion.getY() * 40;

        int rightLimitX = xPosition + 80;
        int leftLimitX = xPosition  - 40;
        int downLimitY = yPosition  + 80;
        int upLimitY = yPosition  - 40;

        boolean xOk = false;
        boolean yOk = false;
        boolean center = false;
        boolean caseOk = true;
        // If the case is accessible
        int caseX = (x *10 / 10)/40;
        int caseY = (y *10 / 10)/40;
        if (Plateau.matrice[caseX][caseY].getType().equals("eau")){
            System.out.println("eau inaccessible !");
            caseOk = false;
        }
        if (Plateau.matrice[caseX][caseY].getType().equals("foret")){
            if (machette == null) {
                System.out.println("Foret inaccessible : trouvez une machette");
                caseOk = false;
            }else{
                caseOk = true;
            }
        }
        //position of the Click
        if (x < xPosition + 40 && x > xPosition && y < yPosition + 40  && y > yPosition){
            center = true;
        }
        if (x >= leftLimitX && x <= rightLimitX) {
            xOk = true;
        }
        if (y >= upLimitY && y <= downLimitY) {
            yOk = true;
        }
        if (xOk && yOk && caseOk &&!center ){
            return true;
        }else {return false;}
    }

    public void deplacement(MouseEvent event) {
        ramasse();
        creuser();
        int xClick = (int) event.getX();
        int yClick = (int) event.getY();
        if (verifClick(xClick,yClick)){
            int caseX = (xClick *10 / 10)/40;
            int caseY = (yClick *10 / 10)/40;
            CaseAccessible caseDeplacement = (CaseAccessible) Plateau.matrice[caseX][caseY];
            CaseAccessible caseActuelle = (CaseAccessible) Plateau.matrice[postion.getX()][postion.getY()];

            caseActuelle.personages.remove(this);
            caseDeplacement.personages.add(this);
            postion = caseDeplacement;
            Plateau.deplacement();
        }else{
            System.out.println("Veuillez reclicker");
        }
    }
}
