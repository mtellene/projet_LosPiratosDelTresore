import javafx.scene.input.MouseEvent;

public class CorsaireJoueur extends Corsaire {
    public CorsaireJoueur(CaseAccessible c) {
        super(c);
    }

    public boolean verifClick(int x, int y) {
        //provisoire
        boolean machette = false;
        int xPosition = postion.getX() * 20;
        int yPosition = postion.getY() * 20;

        int rightLimitX = xPosition + 40;
        int leftLimitX = xPosition  - 20;
        int downLimitY = yPosition  + 40;
        int upLimitY = yPosition  - 20;

        boolean xOk = false;
        boolean yOk = false;
        boolean center = false;
        boolean caseOk = true;
        // If the case is accessible
        int caseX = (x *10 / 10)/20;
        int caseY = (y *10 / 10)/20;
        if (Plateau.matrice[caseX][caseY].getType().equals("eau")){
            System.out.println("eau inaccessible !");
            caseOk = false;
        }
        if (Plateau.matrice[caseX][caseY].getType().equals("foret")){
            System.out.println("Foret inaccessible : trouvez une machette");
            caseOk = false;
        }
        //position of the Click
        if (x < xPosition + 20 && x > xPosition && y < yPosition + 20  && y > yPosition){
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
        int xClick = (int) event.getX();
        int yClick = (int) event.getY();
        if (verifClick(xClick,yClick)){
            int caseX = (xClick *10 / 10)/20;
            int caseY = (yClick *10 / 10)/20;
            CaseAccessible caseDeplacement = (CaseAccessible) Plateau.matrice[caseX][caseY];
            CaseAccessible caseActuelle = (CaseAccessible) Plateau.matrice[postion.getX()][postion.getY()];

            caseDeplacement.personages.add(this);
            caseActuelle.personages.remove(this);
            postion = caseDeplacement;
            Plateau.deplacement();
        }else{
            System.out.println("Veuillez reclicker");
        }



    }
}
