import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.util.Random;

public class canvasTest extends Application {

    public static void displayMatrix(GraphicsContext gc,char[][] M, int size){
        for (int i = 0 ; i < size ; i++){
            for (int j=0 ; j< size ; j++){
                if (M[i][j] == 'x'){
                    gc.strokeRect(j*20,i*20,20,20);
                }
                System.out.print(M[i][j]);
            }
            System.out.println ();
        }
    }

    public static void fillMatrix(char[][] M, int size){
        for (int i = 0 ; i < size ; i++){
            for (int j=0 ; j< size ; j++){
                M[i][j] = '-';
            }
        }
    }

    public static void main(String[] args)
    {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        // Create the Canvas
        Canvas canvas = new Canvas(300, 300);
        // Set the width of the Canvas
        canvas.setWidth(300);
        // Set the height of the Canvas
        canvas.setHeight(300);

        // Get the graphics context of the canvas
        GraphicsContext gc = canvas.getGraphicsContext2D();


        // Create the Pane
        Pane root = new Pane();

        // Add the Canvas to the Pane
        root.getChildren().add(canvas);

        char[][] matrix = new char[15][15];
        fillMatrix(matrix,15);
        for (int i=0; i < 10 ; i++){
            Random random = new Random();
            matrix[random.nextInt(15)][random.nextInt(15)] = 'x' ;
        }
        displayMatrix(gc,matrix,15);






        // Create the Scene
        Scene scene = new Scene(root);
        // Add the Scene to the Stage
        stage.setScene(scene);
        // Set the Title of the Stage
        stage.setTitle("Creation of a Canvas");
        // Display the Stage
        stage.show();



    }
}
