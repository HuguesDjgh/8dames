Introduction
Le jeu des 8 dames est un puzzle qui consiste à placer huit dames sur un échiquier de 8x8 sans 
qu’aucune dame ne soit menacée par une autre. Dans ce rapport, nous décrivons une application 
Android que nous avons développée pour résoudre ce puzzle

L’interface utilisateur, conçue en XML, comprend un échiquier représenté par un GridLayout et 
plusieurs boutons pour interagir avec le jeu. Chaque case de l’échiquier est un bouton qui peut être 
cliqué pour placer une dame, offrant ainsi une interaction directe et facile pour l’utilisateur.
La logique du jeu, mise en œuvre en Java, gère efficacement le placement des dames sur l’échiquier.
Elle offre deux modes de jeu : un mode de placement assisté qui guide l’utilisateur en empêchant le 
placement de dames menacées, et un mode de placement libre qui laisse à l’utilisateur la liberté de 
placer les dames comme il le souhaite.
La gestion des événements est réalisée à l’aide de listeners d’événements en Java. Lorsqu’un 
utilisateur interagit avec l’interface utilisateur, par exemple en cliquant sur un bouton, un 
OnClickListener est déclenché, ce qui entraîne l’exécution de la méthode correspondante pour 
gérer l’événement
