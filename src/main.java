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
        char[][] matrix = new char[5][5];
        fillMatrix(matrix,5);
        for (int i=0; i < 10 ; i++){
            Random random = new Random();
            matrix[random.nextInt(5)][random.nextInt(5)] = 'x' ;
        }
        displayMatrix(matrix,5);
        System.out.println ("LosPiratos");
    }
}
