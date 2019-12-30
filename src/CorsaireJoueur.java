import javafx.scene.input.MouseEvent;

public class CorsaireJoueur extends Corsaire {
    public CorsaireJoueur(CaseAccessible c) {
        super(c);
    }

    public boolean verifClick(int x, int y) {
        int xPosition = postion.getX() * 20;
        int yPosition = postion.getY() * 20;

        int rightLimitX = xPosition + 40;
        int leftLimitX = xPosition  - 20;
        int downLimitY = yPosition  + 40;
        int upLimitY = yPosition  - 20;

        boolean xOk = false;
        boolean yOk = false;
        boolean center = false;
        boolean caseOk = false;
        // If the case is accessible
        int caseX = (x *10 / 10)/20;
        int caseY = (y *10 / 10)/20;
        if (Plateau.matrice[caseX][caseY].getType().equals("sable")){
            caseOk = true;
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

    public void Deplacement(MouseEvent event) {
        int xClick = 0;
        int yClick = 0;
        do {
            xClick = (int) event.getX();
            yClick = (int) event.getY();
        } while (!verifClick(xClick,yClick));

        int caseX = (xClick *10 / 10)/20;
        int caseY = (yClick *10 / 10)/20;
        CaseAccessible caseDeplacement = (CaseAccessible) Plateau.matrice[xClick][yClick];
        //CaseAccessible caseActuelle = (CaseAccessible) Plateau.matrice[postion.getX()][postion.getY()];

        postion = caseDeplacement;


    }
}
