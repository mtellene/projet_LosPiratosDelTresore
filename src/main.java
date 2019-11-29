import java.util.Random;

public class main {
    // Go dev une matrice ici

    public static void displayMatrix(char[][] M, int size){
        for (int i = 0 ; i < size ; i++){
            for (int j=0 ; j< size ; j++){
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


    public static void main(String[] args) {
        char[][] matrix = new char[15][15];
        fillMatrix(matrix,15);
        for (int i=0; i < 30 ; i++){
            Random random = new Random();
            matrix[random.nextInt(15)][random.nextInt(15)] = 'x' ;
        }
        displayMatrix(matrix,15);
        System.out.println ("LosPiratos");
    }
}
