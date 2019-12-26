package gui;
import javafx.application.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;
public class HomePage extends Application implements EventHandler<ActionEvent>{
	//creation du button
	Button button1 = new Button("JOUER");
	Button button2 = new Button("Recharger");
	Button button3 = new Button("Quitter");

	@Override
	public void start(Stage FirstPage) throws Exception {
		//Titre de la fenetre
		FirstPage.setTitle("Les Piratos");
		BorderPane borderPaneRoot = new BorderPane();
		//la création de la scene
		Scene pirate = new Scene (borderPaneRoot,500,300);
		//Ajouter scene pirate au Stage
		FirstPage.setScene(pirate);	
		VBox Box1= new VBox();
		Box1.setPadding(new Insets(10));
		Box1.setSpacing(10);
		button1.setPrefSize(180, 20);
		button1.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
			}
			
		});
		//ajouter action sur button2 pour quitter lapp
		button3.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent t) {
				System.exit(0);
			}
			
		});
		button2.setPrefSize(180, 20);
		button3.setPrefSize(180, 20);
		//ajouter les button au conteneur
		Box1.getChildren().addAll(button1,button2,button3);
		borderPaneRoot.setTop(Box1);
		FirstPage.show();
	}
	public static void main(String[] args) {
		launch(args);

	}
	@Override
	public void handle(ActionEvent event) {
		
	}

}
