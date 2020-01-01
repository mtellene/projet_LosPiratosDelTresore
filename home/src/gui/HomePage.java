package gui;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class HomePage extends Application {
	Scene scene1, scene2,scene3,scene4,scene5,scene6;
    
	@Override
	public void start(Stage primaryStage) {
	        
	primaryStage.setTitle("Les Piratos");

	//Scene 1
	Label label1= new Label("LosPiratosDelTresor");
	Button button1= new Button("Jouer");
	button1.setPrefSize(180, 20);
	Button button2= new Button("Charger");
	button2.setPrefSize(180, 20);
	Button button3= new Button("Quitter");
	button3.setPrefSize(180, 20);
	button3.setOnAction(new EventHandler<ActionEvent>() {

		@Override
		public void handle(ActionEvent t) {
			System.exit(0);
		}
		
	});
	button1.setOnAction(e -> primaryStage.setScene(scene2));   
	VBox layout1 = new VBox(20);     
	layout1.getChildren().addAll(label1, button1,button2,button3);
	scene1= new Scene(layout1, 300, 250);
	 layout1.setAlignment(Pos.CENTER);
	//Scene 2
	Label label2= new Label("Level");
	Button b = new Button("Facile");
	b.setPrefSize(180, 20);
	b.setOnAction(e -> primaryStage.setScene(scene3)); 
	Button bb = new Button("Moyene");
	bb.setPrefSize(180, 20);
	Button bbb = new Button("Difficile");
	bbb.setPrefSize(180, 20);
	b.setOnAction(e -> primaryStage.setScene(scene3));
	VBox layout2= new VBox(20);
	layout2.getChildren().addAll(label2,b,bb, bbb);
	layout2.setAlignment(Pos.CENTER);
	scene2= new Scene(layout2,300,250);
	
	//Scene 3
		Label label3= new Label("Nombre Cases");
		RadioButton rb1 = new RadioButton("N=10");
		rb1.setOnAction(e -> primaryStage.setScene(scene1));
		RadioButton rb2 = new RadioButton("N=15");
		rb2.setOnAction(e -> primaryStage.setScene(scene1));
		RadioButton rb3 = new RadioButton("N=20");
		rb3.setOnAction(e -> primaryStage.setScene(scene1));
		VBox layout3= new VBox(20);
		layout3.getChildren().addAll(label3,rb1,rb2,rb3);
		layout3.setAlignment(Pos.CENTER);
		scene3= new Scene(layout3,300,250);
	
	        
	primaryStage.setScene(scene1);
	primaryStage.show();
	}

	public static void main(String[] args) {
	launch(args);
	}
}
