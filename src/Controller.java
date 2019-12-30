import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Controller extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        int n = 15;
        int canvasWidthHeight = n*40;
        Plateau plateauPartie  = new Plateau(n);


        // Create the Canvas
        Canvas canvas = new Canvas(canvasWidthHeight, canvasWidthHeight);
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


        plateauPartie.fillMatrice();
        //Boucanier b = (Boucanier) plateauPartie.addPersonage();
        CorsaireJoueur cj = (CorsaireJoueur) plateauPartie.addPersonage();
        plateauPartie.displayMatrice(gc);
        canvas.setOnMouseClicked( e ->{
            cj.deplacement(e);
            gc.clearRect(0,0,canvasWidthHeight,canvasWidthHeight);
            plateauPartie.displayMatrice(gc);
        });



        /*Flibustier f = new Flibustier();
        f.deplacement((CaseAccessible) matrice[10][10]);
        Boucanier b = new Boucanier();
        b.deplacement((CaseAccessible) matrice[10][10]);*/


        // Create the Scene
        Scene scene = new Scene(root);
        // Add the Scene to the Stage
        stage.setScene(scene);
        // Set the Title of the Stage
        stage.setTitle("LosPiratosDelTresore");
        // Display the Stage
        stage.show();

        System.out.println("bla");
    }
}
