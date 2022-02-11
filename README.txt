Noms des membres de l'équipe:
Geneviève Paul-Hus 20037331
Jean-Claude Desrosiers 20150403

Repo: https://github.com/jeanclaude-desrosiers/ift3913-tp1

Utilisation du JAR :

```
usage: tp1 [-h] [-m {EXAMPLE,ALL}] [-od DIR] [-o FILE] PATH

positional arguments:
  PATH                   Specify the path of the project to measure

named arguments:
  -h, --help             show this help message and exit
  -m {EXAMPLE,ALL}, --measures {EXAMPLE,ALL}
                         Select a set of measures to run
  -od DIR, --out-directory DIR
                         Specify the directory where the out file will be saved
  -o FILE, --out-file-suffix FILE
                         Specify the suffix of the result file names (e.g. FILE is "jfreechart_" then files would be "jfreechart_classes.csv" and "jfreechart_paquets.csv"
```

Pour exécuter le code à partir du terminal, dans le dossier qui contient le jar, lancer:

```
java -jar tp1-1.1.jar <PATH du dossier à analyser>
```

Les fichiers `classes.csv` et `paquets.csv` seront générés dans le même dossier.

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
