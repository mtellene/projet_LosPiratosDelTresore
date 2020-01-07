import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

public class Plateau {
    public static int n;
    public  int piratesNumber;
    public static Case[][] matrice;
    public static ArrayList<CaseAccessible> caseAccessibles = new ArrayList<CaseAccessible>();

    public Plateau(int n , int piratesNumber) {
        this.n = n;
        this.piratesNumber = piratesNumber;
        matrice = new Case[n][n];
    }

    /**
     * draw the Matrix
     * @param gc the graphic context used to draw
     */
    public void displayMatrice(GraphicsContext gc) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!matrice[i][j].getType().equals("eau")) {
                    CaseAccessible c = (CaseAccessible) matrice[i][j];
                    if (c.getType().equals("foret")) {
                        gc.setFill(Color.GREEN);
                        gc.fillRect(i * 40, j * 40, 40, 40);
                    }
                    if (c.creuse) {
                        gc.setFill(Color.rgb(120, 60, 30));
                        gc.fillOval(c.getX() * 40 + 10, c.getY() * 40 + 10, 20, 20);
                    }
                    if (c.aTresor) {
                        gc.setFill(Color.RED);
                        gc.fillOval(c.getX() * 40 + 10, c.getY() * 40 + 10, 20, 20);
                    }

                    if (!c.personages.isEmpty()) {
                        for (Personnage p : c.personages) {
                            File file = openPersonageIcon(p);
                            try {
                                String localUrl = file.toURI().toURL().toString();
                                Image image = new Image(localUrl);

                                gc.drawImage(image, c.getX() * 40, c.getY() * 40, 40, 40);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            if (CorsaireJoueur.class.equals(p.getClass())) {
                                gc.strokeRect((p.position.getX() * 40) - 40, (p.position.getY() * 40) - 40, 120, 120);
                                ArrayList<CaseAccessible> listCaseOutils = caseVisible(c);
                                for (CaseAccessible caseA : listCaseOutils) {
                                    if (caseA.outil != null) {
                                        File file2 = openOutilIcon(caseA.outil);
                                        try {
                                            String localUrl = file2.toURI().toURL().toString();
                                            Image image = new Image(localUrl);

                                            gc.drawImage(image, caseA.getX() * 40 + 10, caseA.getY() * 40 + 10, 20, 20);
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }


                            }
                        }
                    }
                } else {
                    gc.setFill(Color.BLUE);
                    gc.fillRect(i * 40, j * 40, 40, 40);
                }
            }
        }
    }

    // focntion qui display les objets et les personnages , elle parcourt la matrice et verifie si la case a un objet ou un personnage

    /**
     * fill the Matrix with all items
     *
     * @return the CorsaireJoueur object
     */
    public Personnage fillMatrice() {
        setEau();
        setForet();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (matrice[i][j] == null) {
                    matrice[i][j] = new Sable(i, j);
                    caseAccessibles.add((CaseAccessible) matrice[i][j]);
                }
            }
        }
        CorsaireJoueur cj = (CorsaireJoueur) addPersonage();
        addOutils();
        setTresor();
        return cj;
    }

    /**
     * add the Cases with type "eau"
     */
    private void setEau() {
        int radomI, radomJ;
        int fivePercent = ((n * n) * 5) / 100;
        Random random = new Random();
        for (int i = 0; i < fivePercent; i++) {
            do {
                radomI = random.nextInt(n);
                radomJ = random.nextInt(n);
            } while (matrice[radomI][radomJ] != null);
            matrice[radomI][radomJ] = new Eau(radomI, radomJ);
        }
    }

    /**
     * add the Cases with type "foret"
     */
    private void setForet() {
        int radomI, radomJ;
        int fivePercent = ((n * n) * 5) / 100;
        Random random = new Random();
        for (int i = 0; i < fivePercent; i++) {
            do {
                radomI = random.nextInt(n);
                radomJ = random.nextInt(n);
            } while (matrice[radomI][radomJ] != null);
            matrice[radomI][radomJ] = new Foret(radomI, radomJ);
            caseAccessibles.add((CaseAccessible) matrice[radomI][radomJ]);
        }
    }

    /**
     * open an icon of the specific Personnage
     */
    private File openPersonageIcon(Object personage) {
        String path = "Icons/";
        if (Flibustier.class.equals(personage.getClass())) {
            return new File(path + "flibustier.png");
        } else if (Boucanier.class.equals(personage.getClass())) {
            return new File(path + "boucanier.jpg");
        } else if (CorsaireNonJoueur.class.equals(personage.getClass())) {
            return new File(path + "corsaire2.png");
        } else if (CorsaireJoueur.class.equals(personage.getClass())) {
            return new File(path + "corsaire.png");
        } else {
            return null;
        }
    }

    /**
     * open an icon of the specific Outil
     */
    private File openOutilIcon(Object outil) {
        String path = "Icons/";
        if (Armure.class.equals(outil.getClass())) {
            return new File(path + "armure.png");
        } else if (Machette.class.equals(outil.getClass())) {
            return new File(path + "machette.jpg");
        } else if (Pelle.class.equals(outil.getClass())) {
            return new File(path + "pelle.png");
        } else if (Mousquet.class.equals(outil.getClass())) {
            return new File(path + "mousquet.png");
        } else {
            return null;
        }
    }

    /**
     * add the Personnages in the Matrix
     * @return the CorsaireJoueur object
     */
    private Personnage addPersonage() {
        for (int i = 1; i <= piratesNumber; i++) {
            int index;
            do {
                Random random = new Random();
                index = random.nextInt(caseAccessibles.size());
            } while (!caseAccessibles.get(index).personages.isEmpty());
            Flibustier f = new Flibustier(caseAccessibles.get(index));
            caseAccessibles.get(index).personages.add(f);
        }
        for (int i = 1; i <= piratesNumber; i++) {
            int index;
            do {
                Random random = new Random();
                index = random.nextInt(caseAccessibles.size());
            } while (!caseAccessibles.get(index).personages.isEmpty());
            Boucanier b = new Boucanier(caseAccessibles.get(index));
            caseAccessibles.get(index).personages.add(b);
        }

        for (int i = 1; i <= 2; i++) {
            int index;
            do {
                Random random = new Random();
                index = random.nextInt(caseAccessibles.size());
            } while (caseAccessibles.get(index).getType().equals("foret") || !caseAccessibles.get(index).personages.isEmpty());
            CorsaireNonJoueur cnj = new CorsaireNonJoueur(caseAccessibles.get(index));
            caseAccessibles.get(index).personages.add(cnj);
        }
        int index;
        do {
            Random random = new Random();
            index = random.nextInt(caseAccessibles.size());
        } while (caseAccessibles.get(index).getType().equals("foret") || !caseAccessibles.get(index).personages.isEmpty());
        CorsaireJoueur cj = new CorsaireJoueur(caseAccessibles.get(index));
        caseAccessibles.get(index).personages.add(cj);


        return cj;
    }

    /**
     * Move all the Personnage in the Matrix
     */
    public static void deplacement() {
        ArrayList<Pirate> pirates = new ArrayList<>();
        ArrayList<CorsaireNonJoueur> corsaireNonJoueurs = new ArrayList<>();
        for (CaseAccessible caseA : caseAccessibles) {
            if (caseA.personages.size() > 0) {
                for (Personnage p : caseA.personages) {
                    if (!CorsaireJoueur.class.equals(p.getClass())) {
                        if (p instanceof Pirate){
                            pirates.add((Pirate) p);
                        }else{
                            corsaireNonJoueurs.add((CorsaireNonJoueur) p);
                        }
                    }
                    if (caseA.personages.size() == 0) {
                        break;
                    }
                }
            }
        }
        for (CorsaireNonJoueur c : corsaireNonJoueurs){
            c.deplacement(1);
        }
        for (Pirate p : pirates){
            if (p instanceof Boucanier) {
                p.deplacement(2);
            } else {
                p.deplacement(1);
            }
        }
    }

    /**
     * set the Outils in the matrix
     */
    private void addOutils() {
        int nbCorsaire = 0;
        for (CaseAccessible caseA : caseAccessibles) {
            for (Personnage p : caseA.personages) {
                if (p instanceof Corsaire) {
                    nbCorsaire += 1;
                }
            }
        }
        Random random = new Random();
        int index;
        for (int i = 0; i < nbCorsaire * 2; i++) {
            CaseAccessible caseA;
            Outil m = new Machette();
            do {
                index = random.nextInt(caseAccessibles.size());
                caseA = caseAccessibles.get(index);
            } while (caseA.getType().equals("foret") || caseA.outil != null || !caseA.personages.isEmpty());
            caseA.setOutil(m);
        }

        for (int i = 0; i < nbCorsaire; i++) {
            Outil p = new Pelle();
            CaseAccessible caseA;
            do {
                index = random.nextInt(caseAccessibles.size());
                caseA = caseAccessibles.get(index);
            } while (caseA.outil != null || !caseA.personages.isEmpty());
            caseA.setOutil(p);
        }

        for (int i = 0; i < nbCorsaire * 2; i++) {
            Outil mou = new Mousquet();
            CaseAccessible caseA;
            do {
                index = random.nextInt(caseAccessibles.size());
                caseA = caseAccessibles.get(index);
            } while (caseA.outil != null || !caseA.personages.isEmpty());
            caseA.setOutil(mou);
        }

        for (int i = 0; i < nbCorsaire * 2; i++) {
            Outil a = new Armure();
            CaseAccessible caseA;
            do {
                index = random.nextInt(caseAccessibles.size());
                caseA = caseAccessibles.get(index);
            } while (caseA.outil != null || !caseA.personages.isEmpty());
            caseA.setOutil(a);
        }
    }

    /**
     * Check if a Case is out of the matrix and is not a type "eau"
     *
     * @param x the x coordinate of a Case
     * @param y the y coordinate of a Case
     *
     * @return if the Case is able o be add or not
     */
    static boolean addListCaseVisibles(int x, int y) {
        if (x < 0 || x > n - 1) {
            return false;
        }
        if (y < 0 || y > n - 1) {
            return false;
        }
        return !matrice[x][y].getType().equals("eau");
    }

    /**
     * get all the visible Cases around the CorsaireJoueur
     *
     * @param c the position of the CorsaireJoueur
     * @return a list of the visible Cases
     */
    private ArrayList<CaseAccessible> caseVisible(CaseAccessible c) {
        ArrayList<CaseAccessible> listCaseVisible = new ArrayList<>();
        int x = c.getX();
        int y = c.getY();

        if (addListCaseVisibles(x, y)) {
            listCaseVisible.add((CaseAccessible) matrice[x][y]);
        }
        if (addListCaseVisibles(x + 1, y)) {
            listCaseVisible.add((CaseAccessible) matrice[x + 1][y]);
        }
        if (addListCaseVisibles(x - 1, y)) {
            listCaseVisible.add((CaseAccessible) matrice[x - 1][y]);
        }
        if (addListCaseVisibles(x, y + 1)) {
            listCaseVisible.add((CaseAccessible) matrice[x][y + 1]);
        }
        if (addListCaseVisibles(x, y - 1)) {
            listCaseVisible.add((CaseAccessible) matrice[x][y - 1]);
        }
        if (addListCaseVisibles(x + 1, y + 1)) {
            listCaseVisible.add((CaseAccessible) matrice[x + 1][y + 1]);
        }
        if (addListCaseVisibles(x + 1, y - 1)) {
            listCaseVisible.add((CaseAccessible) matrice[x + 1][y - 1]);
        }
        if (addListCaseVisibles(x - 1, y - 1)) {
            listCaseVisible.add((CaseAccessible) matrice[x - 1][y - 1]);
        }
        if (addListCaseVisibles(x - 1, y + 1)) {
            listCaseVisible.add((CaseAccessible) matrice[x - 1][y + 1]);
        }

        if (addListCaseVisibles(x + 2, y)) {
            listCaseVisible.add((CaseAccessible) matrice[x + 2][y]);
        }
        if (addListCaseVisibles(x + 2, y - 1)) {
            listCaseVisible.add((CaseAccessible) matrice[x + 2][y - 1]);
        }
        if (addListCaseVisibles(x + 2, y - 2)) {
            listCaseVisible.add((CaseAccessible) matrice[x + 2][y - 2]);
        }
        if (addListCaseVisibles(x + 2, y + 1)) {
            listCaseVisible.add((CaseAccessible) matrice[x + 2][y + 1]);
        }
        if (addListCaseVisibles(x + 2, y + 2)) {
            listCaseVisible.add((CaseAccessible) matrice[x + 2][y + 2]);
        }

        if (addListCaseVisibles(x - 2, y)) {
            listCaseVisible.add((CaseAccessible) matrice[x - 2][y]);
        }
        if (addListCaseVisibles(x - 2, y - 1)) {
            listCaseVisible.add((CaseAccessible) matrice[x - 2][y - 1]);
        }
        if (addListCaseVisibles(x - 2, y - 2)) {
            listCaseVisible.add((CaseAccessible) matrice[x - 2][y - 2]);
        }
        if (addListCaseVisibles(x - 2, y + 1)) {
            listCaseVisible.add((CaseAccessible) matrice[x - 2][y + 1]);
        }
        if (addListCaseVisibles(x - 2, y + 2)) {
            listCaseVisible.add((CaseAccessible) matrice[x - 2][y + 2]);
        }

        if (addListCaseVisibles(x, y - 2)) {
            listCaseVisible.add((CaseAccessible) matrice[x][y - 2]);
        }
        if (addListCaseVisibles(x - 1, y - 2)) {
            listCaseVisible.add((CaseAccessible) matrice[x - 1][y - 2]);
        }
        if (addListCaseVisibles(x + 1, y - 2)) {
            listCaseVisible.add((CaseAccessible) matrice[x + 1][y - 2]);
        }

        if (addListCaseVisibles(x, y + 2)) {
            listCaseVisible.add((CaseAccessible) matrice[x][y + 2]);
        }
        if (addListCaseVisibles(x - 1, y + 2)) {
            listCaseVisible.add((CaseAccessible) matrice[x - 1][y + 2]);
        }
        if (addListCaseVisibles(x + 1, y + 2)) {
            listCaseVisible.add((CaseAccessible) matrice[x + 1][y + 2]);
        }
        return listCaseVisible;
    }

    /**
     * add the tresor in a random Case
     */
    private void setTresor() {
        int index;
        Random random = new Random();
        index = random.nextInt(caseAccessibles.size());
        caseAccessibles.get(index).setaTresor();
    }
}
