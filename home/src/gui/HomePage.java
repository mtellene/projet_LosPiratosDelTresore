package gui;
import javafx.application.*;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;
public class HomePage extends Application {

	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage FirstPage) throws Exception {
		FirstPage.setTitle("Les Piratos");
		BorderPane borderPaneRoot = new BorderPane();
		Scene pirate = new Scene (borderPaneRoot,500,300);
		FirstPage.setScene(pirate);	
		HBox hBox1= new HBox();
		hBox1.setPadding(new Insets(10));
		hBox1.setSpacing(10);
		Button button1 = new Button("JOUER");
		button1.setPrefSize(180, 20);
		Button button2 = new Button("Recharger");
		button2.setPrefSize(180, 20);
		Button button3 = new Button("Quitter");
		button3.setPrefSize(180, 20);
		hBox1.getChildren().addAll(button1,button2,button3);
		borderPaneRoot.setTop(hBox1);
		FirstPage.show();
	}

}
