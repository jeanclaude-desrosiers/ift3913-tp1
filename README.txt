Noms des membres de l'équipe:
Geneviève Paul-Hus 20037331
Jean-Claude Desrosiers 

Repo: https://github.com/jeanclaude-desrosiers/ift3913-tp1

Pour exécuter le code à partir du terminal, dans le dossier qui contient le jar, lancer:

java -jar tp1.jar <PATH du fichier à analyser>

Le programme génèrera deux fichiers .csv (classes.csv et paquets.csv) qui détailleront l'analyse de documents se
trouvant sous le chemin (PATH) passé en argument (classes et des paquets respectifs). Le programme analyse les classes
.java et retourne:
 * le chemin du document
 * le nom du document
 * le nombre de lignes de code (LOC)
 * le nombre de lignes de commentaires (CLOC)
 * la densité de commentaire
 * la somme pondérée des complexités cyclomatique de McCabe (WMC)
 * le degré selon lequel un document est bien documenté