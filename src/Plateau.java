import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.util.Random;

public class Plateau extends Application {
    Case[][] matrix = new Case[15][15];


    // display les elements sable , eau , foret
    public void displayMatrix(GraphicsContext gc, Case[][] M, int size){
        for (int i = 0 ; i < size ; i++){
            for (int j=0 ; j< size ; j++){
                if (M[i][j].getType().equals("eau")){
                    gc.setFill(Color.BLUE);
                    gc.fillRect(j*20,i*20,20,20);
                }else if(M[i][j].getType().equals("foret")){
                    gc.setFill(Color.GREEN);
                    gc.fillRect(j*20,i*20,20,20);
                }
            }
            System.out.println ();
        }
    }

    // focntion qui display les objets et les personnages , elle parcourt la matrice et verifie si la case a un objet ou un personnage

    public void fillMatrix(Case[][] M, int size){
        for (int i = 0 ; i < size ; i++){
            for (int j=0 ; j< size ; j++){
                M[i][j] = new Sable(i,j);
            }
        }
    }

    public void setEau(){
        
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





        fillMatrix(matrix,15);
        int radomI, radomJ;
        for (int i=0; i < 11 ; i++){
            Random random = new Random();
            radomI = random.nextInt(15);
            radomJ = random.nextInt(15);
            matrix[radomI][radomJ] = new Eau(radomI,radomJ) ;
        }
        for (int i=0; i < 11 ; i++){
            Random random = new Random();
            do {
                radomI = random.nextInt(15);
                radomJ = random.nextInt(15);
            }while(matrix[radomI][radomJ].getType() == "eau");
            matrix[radomI][radomJ].setType("foret");
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
