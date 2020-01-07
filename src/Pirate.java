import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Random;

public class Pirate extends Personnage {

    public Pirate(CaseAccessible c){
        super(c);
    }

    /**
     * Move the Pirate and attack before and after a movement
     *
     * @param nombreDeCase the range of the movement
     */
    @Override
    public void deplacement(int nombreDeCase) {
        attaque();
        super.deplacement(nombreDeCase);
        attaque();
    }

    /**
     * Attack if a Corsaire is in the range
     */
    public void attaque(){
        if (this instanceof Boucanier){
            Corsaire cible;
            for (Personnage p : position.personages){
                if ( p instanceof CorsaireNonJoueur){
                    System.out.println("un Corsaire est attaqué par un Boucanier");
                    cible = (Corsaire) p;
                    int pWinAttaquant;
                    Random random = new Random();
                    pWinAttaquant = random.nextInt(100);
                    if (pWinAttaquant > cible.pWin){
                        cible.position.personages.remove(cible);
                        position.personages.remove(cible);
                        System.out.println("un Corsaire est mort");
                    }else{
                        position.personages.remove(this);
                        System.out.println("un Boucanier est mort");
                    }
                }
                else if ( p instanceof CorsaireJoueur){
                    System.out.println("Vous etes attaqué par un Boucanier");
                    cible = (Corsaire) p;
                    int pWinAttaquant;
                    Random random = new Random();
                    pWinAttaquant = random.nextInt(100);
                    if (pWinAttaquant > cible.pWin){
                        position.personages.remove(cible);
                        Controller.canvas.setOnMouseClicked(null);
                        //PopUp
                        Stage popupwindow=new Stage();
                        popupwindow.initModality(Modality.APPLICATION_MODAL);
                        popupwindow.initStyle(StageStyle.UNDECORATED);
                        String message = "Vous Avez Perdu ! Vous etes Mort !";
                        Label label1= new Label(message);
                        Button button1= new Button("Close");
                        button1.setOnAction(e -> popupwindow.close());
                        VBox layout= new VBox(10);
                        layout.getChildren().addAll(label1, button1);
                        layout.setAlignment(Pos.CENTER);
                        Scene scene1= new Scene(layout, 300, 100);
                        popupwindow.setScene(scene1);
                        popupwindow.show();
                    }else{
                        position.personages.remove(this);
                        System.out.println("un Boucanier est mort");
                    }
                }
            }
        }else if(this instanceof Flibustier){
            // attaque flibustier
            Corsaire cible = null;
            int x = position.getX();
            int y = position.getY();
            ArrayList<CaseAccessible> caseAuTour = new ArrayList<>();
            if (Plateau.addListCaseVisibles(x , y)) {
                caseAuTour.add((CaseAccessible) Plateau.matrice[x][y]);
            }
            if (Plateau.addListCaseVisibles(x + 1, y)) {
                caseAuTour.add((CaseAccessible) Plateau.matrice[x + 1][y]);
            }
            if (Plateau.addListCaseVisibles(x - 1, y)) {
                caseAuTour.add((CaseAccessible) Plateau.matrice[x - 1][y]);
            }
            if (Plateau.addListCaseVisibles(x, y + 1)) {
                caseAuTour.add((CaseAccessible) Plateau.matrice[x][y + 1]);
            }
            if (Plateau.addListCaseVisibles(x, y - 1)) {
                caseAuTour.add((CaseAccessible) Plateau.matrice[x][y - 1]);
            }
            if (Plateau.addListCaseVisibles(x + 1, y + 1)) {
                caseAuTour.add((CaseAccessible) Plateau.matrice[x + 1][y + 1]);
            }
            if (Plateau.addListCaseVisibles(x + 1, y - 1)) {
                caseAuTour.add((CaseAccessible) Plateau.matrice[x + 1][y - 1]);
            }
            if (Plateau.addListCaseVisibles(x - 1, y - 1)) {
                caseAuTour.add((CaseAccessible) Plateau.matrice[x - 1][y - 1]);
            }
            if (Plateau.addListCaseVisibles(x - 1, y + 1)) {
                caseAuTour.add((CaseAccessible) Plateau.matrice[x - 1][y + 1]);
            }
            
            ListIterator<CaseAccessible> it = caseAuTour.listIterator();
            boolean cibleOrfinish = false;
            while ( !cibleOrfinish){
                while (it.hasNext() && !cibleOrfinish){
                    CaseAccessible caseA = it.next();
                    for (Personnage p : caseA.personages){
                        if (p instanceof Corsaire){
                            cible = (Corsaire) p;
                            cibleOrfinish = true;
                            break;
                        }
                    }
                }
                cibleOrfinish = true;
            }
            if (cible != null){
                int pWinAttaquant;
                Random random = new Random();
                pWinAttaquant = random.nextInt(100);
                if (cible instanceof CorsaireNonJoueur){
                    System.out.println("un Corsaire est attaqué par un Flibustier");
                    if (pWinAttaquant > cible.pWin){
                        cible.position.personages.remove(cible);
                        position.personages.remove(cible);
                        System.out.println("un Corsaire est mort");
                    }else{
                        position.personages.remove(this);
                        System.out.println("un Flibustier est mort");
                    }
                }else{
                    System.out.println("Vous etes attaqué par un Flibustier");
                    if (pWinAttaquant > cible.pWin){
                        position.personages.remove(cible);
                        Controller.canvas.setOnMouseClicked(null);
                        //PopUp
                        Stage popupwindow=new Stage();
                        popupwindow.initModality(Modality.APPLICATION_MODAL);
                        popupwindow.initStyle(StageStyle.UNDECORATED);
                        String message = "Vous Avez Perdu ! Vous etes Mort !";
                        Label label1= new Label(message);
                        Button button1= new Button("Close");
                        button1.setOnAction(e -> popupwindow.close());
                        VBox layout= new VBox(10);
                        layout.getChildren().addAll(label1, button1);
                        layout.setAlignment(Pos.CENTER);
                        Scene scene1= new Scene(layout, 300, 100);
                        popupwindow.setScene(scene1);
                        popupwindow.show();
                    } else{
                        position.personages.remove(this);
                        System.out.println("un Flibustier est mort");
                    }
                }
            }
        }
    }
}
