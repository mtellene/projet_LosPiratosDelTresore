import java.util.Random;

public class Corsaire extends Personnage {

    //variables
    boolean pelle = false;
    boolean machette = false;
    boolean mousquet = false;
    boolean armure = false;

    String nom;
    int pWin;
    //case mais jsp comment mettre

    //methodes
    public Corsaire(CaseAccessible c){
        super(c);
    }

    public void Ramassage(){
        /*
        if pelle == false && case == pelle {ramasser pelle; }
        elseif mousquet == false && case == mousquet { ramasser mousquet; }
        elseif machette == false && case == machette { ramasser machette; }
        elseif armure == false && case == armure {ramasser armure; }
         */
    }

    public void Deplacement(){
        /*
        if case == eau
            System.out.println("Impossible d'aller sur cette case");
            Deplacement();
        elseif case == foret
            if machette == false
                System.out.println("Impossible d'aller sur cette case");
                Deplacement();
            else
                aller sur la case;
        else
            aller sur la case;
         */
    }

    public int PourcentageVictoire(){
        /*
        if armure == true
            if mousquet == true { int pWin = 100; }
            elseif mousquet == false && machette == true { int pWin = 40; }
            else { int pWin = 10; }
        else
            if mousquet == true { int pWin = 90; } on prend le % du mousquet (le + haut)
            elseif mousquet == false && machette == true { int pWin = 40; }
            else {int pWin = 0; }
         */
        return pWin;
    }


    @Override
    public void deplacement(int nombreDeCase) {
        super.deplacement(nombreDeCase);
    }
}
