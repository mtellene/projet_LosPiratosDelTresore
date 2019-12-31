import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class Corsaire extends Personnage {

    //variables
    public Pelle pelle;
    public Machette machette;
    public Armure armure;
    public Mousquet mousquet;

    public int pWin;

    //methodes
    public Corsaire(CaseAccessible c){
        super(c);
    }

    public void ramasse(){
        Outil outil = this.postion.outil;
        if (outil != null){
            if (Pelle.class.equals(outil.getClass()) && (this.pelle == null )) {
                this.pelle = (Pelle) outil;
                this.postion.outil = null;
            }
            else if (Machette.class.equals(outil.getClass()) && (this.machette == null )) {
                this.machette = (Machette) outil;
                this.postion.outil = null;
            }
            else if (Armure.class.equals(outil.getClass()) && (this.armure == null )){
                this.armure = (Armure) outil;
                this.postion.outil = null;
            }
            else if (Mousquet.class.equals(outil.getClass()) && (this.mousquet == null )) {
                this.mousquet = (Mousquet) outil;
                this.postion.outil = null;
            }
        }
    }

    public void creuser(){
        if (this.pelle != null){
            this.postion.creuse = true;
        }
        if (this.postion.aTresor){
            Controller.canvas.setOnMouseClicked(null);
            Stage popupwindow=new Stage();

            popupwindow.initModality(Modality.APPLICATION_MODAL);
            popupwindow.initStyle(StageStyle.UNDECORATED);
            //popupwindow.setTitle("This is a pop up window");

            String message;
            if( this.getClass().getName() != "CorsaireJoueur" ){
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

    public int PourcentageVictoire(){
        /*
        if armure == true
            if mousquet == true { int pWin = 100; }
            elseif mousquet == false && machette == true { int pWin = 40; }
            else { int pWin = 10; }
        else
            if mousquet == true { int pWin = 90; } on prend le % du mousquet (le + haut)
            elseif mousquet == false && machette == true { int pWin = 40; }
            else {int pWin = 0; }
         */
        return pWin;
    }


    @Override
    public void deplacement(int nombreDeCase) {
        ramasse();
        creuser();
        super.deplacement(nombreDeCase);
    }
}
