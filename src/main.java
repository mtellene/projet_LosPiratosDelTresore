import java.util.Random;
import java.util.Scanner;

public class main {

    public static void main(String[] args) {
        Scanner x = new Scanner(System.in);
        System.out.println("Entrez le nombre de pirates à créer : ");
        String str = x.nextLine();

        int coucou = Integer.parseInt(str);
        Random random = new Random();
        int nb = random.nextInt(coucou + 1); //chiffre random

        for(int i=0 ; i<nb ; i++){ Flibustier flibustier = new Flibustier(); }
        for(int i=nb ; i<coucou ; i++){ Boucanier boucanier = new Boucanier(); }


    }
}
