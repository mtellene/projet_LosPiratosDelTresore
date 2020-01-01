import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class Controller extends Application {

    public static Canvas canvas;

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        int n = 8;
        int canvasWidthHeight = n*40;
        Plateau plateauPartie  = new Plateau(n);


        // Create the Canvas
        canvas = new Canvas(canvasWidthHeight, canvasWidthHeight);
        // Set the width of the Canvas
        canvas.setWidth(canvasWidthHeight);
        // Set the height of the Canvas
        canvas.setHeight(canvasWidthHeight);
        // Get the graphics context of the canvas
        GraphicsContext gc = canvas.getGraphicsContext2D();
        // Create the Pane
        Pane root = new Pane();
        root.setStyle("-fx-background-color: LemonChiffon ");
        // Add the Canvas to the Pane
        root.getChildren().add(canvas);


        CorsaireJoueur cj = (CorsaireJoueur) plateauPartie.fillMatrice();
        plateauPartie.displayMatrice(gc);
        cj.ramasse();
        cj.creuser();
        canvas.setOnMouseClicked( e ->{
            cj.deplacement(e);
            Plateau.deplacement();
            gc.clearRect(0,0,canvasWidthHeight,canvasWidthHeight);
            plateauPartie.displayMatrice(gc);
        });


        // Create the Scene
        Scene scene = new Scene(root);
        // Add the Scene to the Stage
        stage.setScene(scene);
        // Set the Title of the Stage
        stage.setTitle("LosPiratosDelTresore");
        // Display the Stage
        stage.show();
        System.out.println("Bonne chance ! ");
    }
}
