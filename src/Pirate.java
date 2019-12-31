import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Random;

public class Pirate extends Personnage {

    public Pirate(CaseAccessible c){
        super(c);
    }

    @Override
    public void deplacement(int nombreDeCase) {
        super.deplacement(nombreDeCase);
        attaque();
    }

    public void attaque(){
        if (this instanceof Boucanier){
            Corsaire cible;
            for (Personnage p : position.personages){
                if ( p instanceof CorsaireNonJoueur){
                    System.out.println("un Corsaire est attaqué");
                    cible = (Corsaire) p;
                    int pWinAttaquant;
                    Random random = new Random();
                    pWinAttaquant = random.nextInt(100);
                    if (pWinAttaquant > cible.pWin){
                        position.personages.remove(cible);
                        System.out.println("un Corsaire est mort");
                    }else{
                        position.personages.remove(this);
                        System.out.println("un Boucanier est mort");
                    }
                }
                if ( p instanceof CorsaireJoueur){
                    System.out.println("Vous etes attaqué");
                    cible = (Corsaire) p;
                    int pWinAttaquant;
                    Random random = new Random();
                    pWinAttaquant = random.nextInt(100);
                    if (pWinAttaquant > cible.pWin){
                        position.personages.remove(cible);
                        Controller.canvas.setOnMouseClicked(null);
                        Stage popupwindow=new Stage();

                        popupwindow.initModality(Modality.APPLICATION_MODAL);
                        popupwindow.initStyle(StageStyle.UNDECORATED);
                        //popupwindow.setTitle("This is a pop up window");

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
        }
    };
}
