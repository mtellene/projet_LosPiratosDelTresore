import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

public class Plateau {
    public static int n;
    public static Case[][] matrice;
    public static ArrayList<CaseAccessible> caseAccessibles = new ArrayList<CaseAccessible>();

    public Plateau(int n) {
        Plateau.n = n;
        matrice = new Case[n][n];
    }

    // display les elements sable , eau , foret et les personnages
    public void displayMatrice(GraphicsContext gc) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!matrice[i][j].getType().equals("eau")) {
                    CaseAccessible c = (CaseAccessible) matrice[i][j];
                    if (c.getType().equals("foret")) {
                        gc.setFill(Color.GREEN);
                        gc.fillRect(i * 20, j * 20, 20, 20);
                    }

                    if (!c.personages.isEmpty()) {
                        for (Personnage p : c.personages) {
                            File file = openPersonageIcon(p);
                            try {
                                String localUrl = file.toURI().toURL().toString();
                                Image image = new Image(localUrl);

                                gc.drawImage(image, c.getX() * 20, c.getY() * 20, 20, 20);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            if (CorsaireJoueur.class.equals(p.getClass())) {
                                gc.strokeRect((p.postion.getX() * 20) - 20, (p.postion.getY() * 20) - 20, 60, 60);
                                ArrayList<CaseAccessible> listCaseOutils = caseVisible(c);
                                for (CaseAccessible caseA : listCaseOutils) {
                                    if (caseA.outil != null) {
                                        File file2 = openOutilIcon(caseA.outil);
                                        try {
                                            String localUrl = file2.toURI().toURL().toString();
                                            Image image = new Image(localUrl);

                                            gc.drawImage(image, caseA.getX() * 20 + 5, caseA.getY() * 20 + 5, 10, 10);
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }


                            }
                        }
                    }

                    /*if (c.outil != null){
                        File file = openOutilIcon(c.outil);
                        try {
                            String localUrl = file.toURI().toURL().toString();
                            Image image = new Image(localUrl);

                            gc.drawImage(image, c.getX() * 20+5, c.getY() * 20+5, 10, 10);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }*/

                } else {
                    gc.setFill(Color.BLUE);
                    gc.fillRect(i * 20, j * 20, 20, 20);
                }
            }
        }
    }

    // focntion qui display les objets et les personnages , elle parcourt la matrice et verifie si la case a un objet ou un personnage

    public void fillMatrice() {
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
        addOutils();
    }

    public void setEau() {
        int radomI, radomJ;
        int fivePercent = ((n * n) * 5) / 100;
        Random random = new Random();
        for (int i = 0; i < fivePercent; i++) {
            do {
                radomI = random.nextInt(n);
                radomJ = random.nextInt(n);
            } while (matrice[radomI][radomJ] != null);
            matrice[radomI][radomJ] = new Eau(radomI, radomJ);
            System.out.println("eau :" + radomI + " " + radomJ);
        }
    }

    public void setForet() {
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
            System.out.println("Foret :" + radomI + " " + radomJ);
        }
    }


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


    public Personnage addPersonage() {
        for (int i = 1; i <= 3; i++) {
            int index;
            Random random = new Random();
            index = random.nextInt(caseAccessibles.size());
            Flibustier f = new Flibustier(caseAccessibles.get(index));
            caseAccessibles.get(index).personages.add(f);
        }
        for (int i = 1; i <= 3; i++) {
            int index;
            Random random = new Random();
            index = random.nextInt(caseAccessibles.size());
            Boucanier b = new Boucanier(caseAccessibles.get(index));
            caseAccessibles.get(index).personages.add(b);
        }

        for (int i = 1; i <= 2; i++) {
            int index;
            do {
                Random random = new Random();
                index = random.nextInt(caseAccessibles.size());
            } while (caseAccessibles.get(index).getType().equals("foret"));
            CorsaireNonJoueur cnj = new CorsaireNonJoueur(caseAccessibles.get(index));
            caseAccessibles.get(index).personages.add(cnj);
        }
        int index;
        do {
            Random random = new Random();
            index = random.nextInt(caseAccessibles.size());
        } while (caseAccessibles.get(index).getType().equals("foret"));
        CorsaireJoueur cj = new CorsaireJoueur(caseAccessibles.get(index));
        System.out.println(caseAccessibles.get(index).getX() + "  " + caseAccessibles.get(index).getY());
        caseAccessibles.get(index).personages.add(cj);


        return cj;
    }

    public static void deplacement() {
        for (CaseAccessible caseA : caseAccessibles) {
            if (caseA.personages.size() > 0) {
                for (Personnage p : caseA.personages) {
                    if (!CorsaireJoueur.class.equals(p.getClass())) {
                        if (Flibustier.class.equals(p.getClass())) {
                            p.deplacement(2);
                        } else {
                            p.deplacement(1);
                        }
                    }
                    if (caseA.personages.size() == 0) {
                        break;
                    }
                }
            }
        }
    }

    private void addOutils() {
        Random random = new Random();
        int index;
        for (int i = 0; i < 2; i++) {
            CaseAccessible caseA;
            Outil m = new Machette();
            do {
                index = random.nextInt(caseAccessibles.size());
                caseA = caseAccessibles.get(index);
            } while (caseA.getType().equals("foret") || caseA.outil != null);
            caseA.setOutil(m);
        }

        for (int i = 0; i < 2; i++) {
            Outil p = new Pelle();
            CaseAccessible caseA;
            do {
                index = random.nextInt(caseAccessibles.size());
                caseA = caseAccessibles.get(index);
            } while (caseA.outil != null);
            caseA.setOutil(p);
        }

        for (int i = 0; i < 2; i++) {
            Outil mou = new Mousquet();
            CaseAccessible caseA;
            do {
                index = random.nextInt(caseAccessibles.size());
                caseA = caseAccessibles.get(index);
            } while (caseA.outil != null);
            caseA.setOutil(mou);
        }

        for (int i = 0; i < 2; i++) {
            Outil a = new Armure();
            CaseAccessible caseA;
            do {
                index = random.nextInt(caseAccessibles.size());
                caseA = caseAccessibles.get(index);
            } while (caseA.outil != null);
            caseA.setOutil(a);
        }
    }

    private boolean addListCaseVisibles(int x, int y) {
        if (x < 0 || x > n - 1) {
            return false;
        }
        if (y < 0 || y > n - 1) {
            return false;
        }
        return !matrice[x][y].getType().equals("eau");
    }

    private ArrayList<CaseAccessible> caseVisible(CaseAccessible c) {
        ArrayList<CaseAccessible> listCaseVisible = new ArrayList<>();
        int x = c.getX();
        int y = c.getY();

        if (addListCaseVisibles(x+1,y)) {
            listCaseVisible.add((CaseAccessible) matrice[x + 1][y]);
        }
        if (addListCaseVisibles(x-1,y)) {
            listCaseVisible.add((CaseAccessible) matrice[x - 1][y]);
        }
        if (addListCaseVisibles(x,y+1)) {
            listCaseVisible.add((CaseAccessible) matrice[x][y + 1]);
        }
        if (addListCaseVisibles(x,y-1)) {
            listCaseVisible.add((CaseAccessible) matrice[x][y - 1]);
        }
        if (addListCaseVisibles(x+1,y+1)) {
            listCaseVisible.add((CaseAccessible) matrice[x + 1][y + 1]);
        }
        if (addListCaseVisibles(x+1,y-1)) {
            listCaseVisible.add((CaseAccessible) matrice[x + 1][y - 1]);
        }
        if (addListCaseVisibles(x-1,y-1)) {
            listCaseVisible.add((CaseAccessible) matrice[x - 1][y - 1]);
        }
        if (addListCaseVisibles(x-1,y+1)) {
            listCaseVisible.add((CaseAccessible) matrice[x - 1][y + 1]);
        }
        if (addListCaseVisibles(x,y)) {
            listCaseVisible.add((CaseAccessible) matrice[x][y]);
        }
        return listCaseVisible;
    }
}
