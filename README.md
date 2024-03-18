# Gestion d'associations

Master 1 TIIL2, UBO, Brest

Auteurs : 
- Jean-Jacques MOUANDA MOUANDA 
- Moetez JAOUED
- Gatien BERTIN
- Mathurin HAUVILLE

## Description de l'application 
L’application réalisée gère les événements d’une association et de ses membres. L'application est composée de 4 API REST qui gèrent les entités suivantes :
- Membre
- Messages / commentaires
- Événements
- Lieux
### Backend

Les API Membre et Lieux sont développées en utilisant le framework Spring Boot et les API Messages et Événements sont développées en Servlets. 
L'API supplémentaire core-api permet de centraliser les appels aux autres API.

### Base de données

Deux bases de données sont utilisées pour ce projet. La principale est une base de données relationnelle MariaDB qui gère les membres, lieux, événements... La seconde est une base de données NoSQL MongoDB qui gère les commentaires. 

Ces bases de données sont déployées dans des conteneurs Docker pour la phase de développement avec des commandes Makefile pour faciliter leur manipulation.

### Frontend

Le frontend est développé en Vue.js.