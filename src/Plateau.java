import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

public class Plateau extends Application {
    public static Case[][] matrice = new Case[15][15];
    public static ArrayList<CaseAccessible> caseAccessibles = new ArrayList<CaseAccessible>();

    // display les elements sable , eau , foret
    public void displayMatrice(GraphicsContext gc) {
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                if (!matrice[i][j].getType().equals("eau")){
                    CaseAccessible c = (CaseAccessible) matrice[i][j];
                    if (matrice[i][j].getType().equals("foret")) {
                        gc.setFill(Color.GREEN);
                        gc.fillRect(i * 20, j * 20, 20, 20);
                    }
                    if (!c.personages.isEmpty()){
                        for (Personnage p : c.personages){
                            File file = openPersonageIcon(p);
                            try {
                                String localUrl = file.toURI().toURL().toString();
                                Image image = new Image(localUrl);

                                gc.drawImage(image,c.getX()*20,c.getY()*20,20,20);
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        }
                    }

                }else{
                    gc.setFill(Color.BLUE);
                    gc.fillRect(i * 20, j * 20, 20, 20);
                }

                /*// les cases
                if (matrice[i][j].getType().equals("eau")) {
                    gc.setFill(Color.BLUE);
                    gc.fillRect(j * 20, i * 20, 20, 20);
                } else if (matrice[i][j].getType().equals("foret")) {
                    gc.setFill(Color.GREEN);
                    gc.fillRect(j * 20, i * 20, 20, 20);
                }*/

            }
        }
    }

    // focntion qui display les objets et les personnages , elle parcourt la matrice et verifie si la case a un objet ou un personnage

    public void fillMatrice() {
        setEau();
        setForet();
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                if (matrice[i][j] == null){
                    matrice[i][j] = new Sable(i, j);
                    caseAccessibles.add((CaseAccessible) matrice[i][j]);
                }
            }
        }
    }

    public void setEau() {
        int radomI, radomJ;
        for (int i = 0; i < 11; i++) {
            Random random = new Random();
            radomI = random.nextInt(15);
            radomJ = random.nextInt(15);
            matrice[radomI][radomJ] = new Eau(radomI, radomJ);
        }
    }

    public void setForet(){
        int radomI, radomJ;
        for (int i = 0; i < 11; i++) {
            Random random = new Random();
            do {
                radomI = random.nextInt(15);
                radomJ = random.nextInt(15);
            } while (matrice[radomI][radomJ] != null);
            matrice[radomI][radomJ] = new Foret(radomI, radomJ);
            caseAccessibles.add((CaseAccessible) matrice[radomI][radomJ]);
        }
    }


    protected File openPersonageIcon(Object personage){
        String path = "Icons/";
        if (Flibustier.class.equals(personage.getClass())) {
            return new File(path + "flibustier.png");
        } else if (Boucanier.class.equals(personage.getClass())) {
            return new File(path + "boucanier.jpg");
        } else if (Corsaire.class.equals(personage.getClass())) {
            return new File(path + "corsaire.png");
        } else {
            return null;
        }
    }

    public Personnage addPersonage(){
        Flibustier f = null;
        Boucanier b = null;
        for (int i = 1; i < 2 ; i++){
            int index;
            Random random = new Random();
            index = random.nextInt(caseAccessibles.size());
            f = new Flibustier(caseAccessibles.get(index));
            caseAccessibles.get(index).personages.add(f);
        }
        for (int i = 1; i <= 3 ; i++){
            int index;
            Random random = new Random();
            index = random.nextInt(caseAccessibles.size());
            b = new Boucanier(caseAccessibles.get(index));
            caseAccessibles.get(index).personages.add(b);
        }

        for (int i = 1; i <= 3 ; i++){
            int index;
            do{
                Random random = new Random();
                index = random.nextInt(caseAccessibles.size());
            }while(caseAccessibles.get(index).getType().equals("foret"));
            Corsaire c = new Corsaire(caseAccessibles.get(index));
            System.out.println(caseAccessibles.get(index).getX()+ "  " +caseAccessibles.get(index).getY());
            caseAccessibles.get(index).personages.add(c);
        }
        return f;
    }

    public static void main(String[] args) {
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


        fillMatrice();
        //Boucanier b = (Boucanier) addPersonage();
        Flibustier f = (Flibustier) addPersonage();
        displayMatrice(gc);
        canvas.setOnMouseClicked( e ->{
           f.deplacement();
           gc.clearRect(0,0,300,300);
           displayMatrice(gc);
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
    }
}
