public abstract class Personnage {
    protected CaseAccessible postion;

    public Personnage(CaseAccessible c){
        postion = c;
    }

    abstract void deplacement();
}
