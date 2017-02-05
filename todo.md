Objectif: Implémenter l'algorithm de Dijkstra pour mesurer le plus court de toute les villes avec toutes les autres villes

Cf. 
* http://www.normalesup.org/~dconduche/informatique/PT/Cours/Dijkstra.pdf
* http://www.cse.buffalo.edu/faculty/miller/Courses/CSE633/Ye-Fall-2012-CSE633.pdf
* http://liris.cnrs.fr/csolnon/polyGraphes.pdf (§9)

Ce que je veux tester/apprendre : 
- PostgreSQL
- Socket.IO / AngularJS
- NoSQL (ElasticSearch)
- Docker


L'application va s'occuper de charger un fichier contenant le graphe. 

Exemple: 

A ----- 10 ---- B ------ 1 ----- C


| Ville 	| Ville 	| Distance 	|
|-----------|-----------|-----------|
| A 	| B 	| 358 		|
| Nantes 	| Rennes 	| 98 		|

Charger le fichier signifie calculer le plus court chemin entre chaque villes et stocker ce résultat dans une base NoSQL.

Un autre écran de cette application s'occupera d'afficher, pour deux villes données, les 5 chemins les plus courts



Objectif: Implémenter l'algorithm de Dijkstra pour mesurer le plus court de toute les villes avec toutes les autres villes