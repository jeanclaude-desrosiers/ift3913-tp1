Noms des membres de l'équipe:
Geneviève Paul-Hus 20037331
Jean-Claude Desrosiers 20150403

Repo: https://github.com/jeanclaude-desrosiers/ift3913-tp1

Pour obtenir le JAR, il suffit de le télécharger à partir de la section 'Packages' du repo github

Si par contre, on veut compiler le projet par soi-même, on peut faire :

```
cd ./tp1 ; mvn clean install
```

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
java -jar tp1-2.jar <PATH du dossier à analyser>
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
 
 Note importante, dans les instructions, nous avons assumé la chose suivante :
 
 - Les mesures paquet_LOC et paquet_CLOC sont calculées comme la somme des classe_LOC et classe_CLOC des classes Java dans le paquet.
 - La mesure paquet_WCP fonctionne de la même façon, sauf qu'on ajoute aussi le paquet_WCP de tous les sous-paquets.
 
 Si ce n'est pas le comportement voulu :
 
 1. la branche `genevieve` assume le comportement d'aussi ajouter les "sous-paquets" pour toutes les mesures paquet_LOC, paquet_CLOC et paquet_WCP.
 2. le comportement est très facile à changer, il suffit de mettre `recursive` à `true/false` dans les classes `PackageMeasureCLOC`, `PackageMeasureLOC` et/ou `PackageMeasureWCP`, selon ce qui est voulu.
