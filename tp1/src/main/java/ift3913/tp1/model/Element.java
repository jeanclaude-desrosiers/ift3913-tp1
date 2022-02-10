package ift3913.tp1.model;

/**
 * Modèle élément (classe ou paquet)
 */
public class Element {

    private int nbLignesCloc;
    private int nbLignesLoc;
    private int complexite;
    private float densite;
    private float degre;
    private String chemin;
    private String nom;

    /**
     * Constructeur
     */
    public Element() {
        this.nbLignesCloc = 0;
        this.nbLignesLoc = 0;
        this.densite = 0;
        this.degre = 0;
        this.complexite = 0;
        this.chemin = "";
        this.nom = "";
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
     * Getter
     * @return la complexite
     */
    public int getComplexite() { return complexite; }

    /**
     * Getter
     * @return la densité
     */
    public float getDensite() { return densite; }

    /**
     * Getter
     * @return le degré de bons commentaires
     */
    public float getDegre() { return degre; }

    /**
     * Getter
     * @return le path de l'élément
     */
    public String getChemin() { return chemin; }

    /**
     * Getter
     * @return le nom de l'élément
     */
    public String getNom() { return nom; }

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

    /**
     * Setter
     * @param complexite la complexité de l'élément
     */
    public void setComplexite(int complexite) { this.complexite = complexite; }

    /**
     * Setter
     * @param chemin le path de l'élément
     */
    public void setChemin(String chemin) { this.chemin = chemin; }

    /**
     * Setter
     * @param nom le nom de l'élément
     */
    public void setNom(String nom) { this.nom = nom; }

    /**
     * Ajout au nb de lignes commentaires
     */
    public void ajoutCloc(int ajout) { this.nbLignesCloc += ajout; }

    /**
     * Ajout au nb de lignes code + commentaire
     */
    public void ajoutLoc(int ajout) { this.nbLignesLoc += ajout; }

    /**
     * Augmente la complexité
     */
    public void augmenteComplexite(int augmentation) { this.complexite += augmentation; }

    /**
     * Calcule la densité
     */
    public void calculeDensite() { this.densite = division(nbLignesCloc, nbLignesLoc); }

    /**
     * Calcule le degré de bons commentaires
     */
    public float calculeDegreCommentaire() { return division(densite, complexite); }

    private float division(float numerateur, int denominateur) {
        if(denominateur != 0)
            return numerateur / denominateur;
        else
            return 0;
    }
}
