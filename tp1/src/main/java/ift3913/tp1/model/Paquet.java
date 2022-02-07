package ift3913.tp1.model;

/**
 * Obejt paquet qui contien les lignes de code par paquet
 */
public class Paquet {

    private int nbLignesCloc;
    private int nbLignesLoc;

    /**
     * Constructeur
     * @param nbLignesLoc nombre de lignes de code + commentaires
     * @param nbLignesCloc nombre de lignes de commentaires
     */
    public Paquet(int nbLignesLoc, int nbLignesCloc) {
        this.nbLignesCloc = nbLignesCloc;
        this.nbLignesLoc = nbLignesLoc;
    }

    /**
     * Getter
     * @return le nombre de lignes de commentaires
     */
    public int getNbLignesCloc() {
        return nbLignesCloc;
    }

    /**
     * Getter
     * @return le nombre de lignes de code + commentaires
     */
    public int getNbLignesLoc() {
        return nbLignesLoc;
    }

    /**
     * Setter
     * @param nbLignesCloc nombre de lignes commentaires
     */
    public void setNbLignesCloc(int nbLignesCloc) {
        this.nbLignesCloc = nbLignesCloc;
    }

    /**
     * Setter
     * @param nbLignesLoc nombre de lignes de code + commentaires
     */
    public void setNbLignesLoc(int nbLignesLoc) {
        this.nbLignesLoc = nbLignesLoc;
    }
}
