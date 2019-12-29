import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.util.Random;

public class canvasTest extends Application {

    // display les elements sable , eau , foret
    public static void displayMatrix(GraphicsContext gc,char[][] M, int size){
        for (int i = 0 ; i < size ; i++){
            for (int j=0 ; j< size ; j++){
                if (M[i][j] == 'x'){
                    gc.setFill(Color.BLUE);
                    gc.fillRect(j*20,i*20,20,20);
                }else if(M[i][j] == 'a'){
                    gc.setFill(Color.GREEN);
                    gc.fillRect(j*20,i*20,20,20);
                }
                System.out.print(M[i][j]);
            }
            System.out.println ();
        }
    }

    // focntion qui display les objets et les personnages , elle parcourt la matrice et verifie si la case a un objet ou un personnage

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

        root.setStyle("-fx-background-color: LemonChiffon ");
        // Add the Canvas to the Pane
        root.getChildren().add(canvas);

        char[][] matrix = new char[15][15];
        fillMatrix(matrix,15);
        for (int i=0; i < 11 ; i++){
            Random random = new Random();
            matrix[random.nextInt(15)][random.nextInt(15)] = 'x' ;
        }
        for (int i=0; i < 11 ; i++){
            Random random = new Random();
            int radomI, radomJ;
            do {
                radomI = random.nextInt(15);
                radomJ = random.nextInt(15);
            }while(matrix[radomI][radomJ] == 'x');
            matrix[radomI][radomJ] = 'a';
        }
        displayMatrix(gc,matrix,15);



        // Create the Scene
        Scene scene = new Scene(root);
        // Add the Scene to the Stage
        stage.setScene(scene);
        // Set the Title of the Stage
        stage.setTitle("LosPiratosDelTresore");
        // Display the Stage
        stage.show();
    }
}
