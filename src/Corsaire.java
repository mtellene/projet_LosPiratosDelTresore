import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class Corsaire extends Personnage {

    public Pelle pelle = null;
    public Machette machette = null;
    public Armure armure = null;
    public Mousquet mousquet = null;

    public int pWin = 0;

    public Corsaire(CaseAccessible c){
        super(c);
    }

    /**
     * Pick up an Outil if exist
     */
    public void ramasse(){
        Outil outil = this.position.outil;
        if (outil != null){
            if (Pelle.class.equals(outil.getClass()) && (this.pelle == null )) {
                this.pelle = (Pelle) outil;
                this.position.outil = null;
            }
            else if (Machette.class.equals(outil.getClass()) && (this.machette == null )) {
                this.machette = (Machette) outil;
                this.position.outil = null;
            }
            else if (Armure.class.equals(outil.getClass()) && (this.armure == null )){
                this.armure = (Armure) outil;
                this.position.outil = null;
            }
            else if (Mousquet.class.equals(outil.getClass()) && (this.mousquet == null )) {
                this.mousquet = (Mousquet) outil;
                this.position.outil = null;
            }
        }
        setPourcentageVictoire();
    }

    /**
     * Dig the Case if the Corsaire have a Pelle
     */
    public void creuser(){
        if (this.pelle != null){
            this.position.creuse = true;
            if (this.position.aTresor){
                Controller.canvas.setOnMouseClicked(null);
                Stage popupwindow=new Stage();

                popupwindow.initModality(Modality.APPLICATION_MODAL);
                popupwindow.initStyle(StageStyle.UNDECORATED);
                //popupwindow.setTitle("This is a pop up window");

                String message;
                if( this.getClass().getName() != "CorsaireNonJoueur" ){
                    message = "Vous Avez Gagné ! Bravo !";
                }else{
                    message = "Vous Avez Perdu ! Le tresor a été trouvé !";
                }
                Label label1= new Label(message);


                Button button1= new Button("Close");


                button1.setOnAction(e -> popupwindow.close());



                VBox layout= new VBox(10);


                layout.getChildren().addAll(label1, button1);

                layout.setAlignment(Pos.CENTER);

                Scene scene1= new Scene(layout, 300, 100);

                popupwindow.setScene(scene1);
                popupwindow.show();
            }
        }
    }

    /**
     * set Percent of win in a fight
     */
    public void setPourcentageVictoire(){
        if(armure != null){
            if(mousquet != null) { pWin = 100;}
            else if (machette != null){ pWin = 40;}
            else{ pWin = 10;}
        }
        else{
            if(mousquet != null) { pWin = 90;}
            else if (machette != null){ pWin = 40;}
            else{ pWin = 0;}
        }
    }


    /**
     * Move , pick up, and dig
     *
     * @param nombreDeCase the range of the movement
     */
    @Override
    public void deplacement(int nombreDeCase) {
        super.deplacement(nombreDeCase);
        ramasse();
        creuser();
    }

}
