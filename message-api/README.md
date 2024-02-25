# API messages / commentaires

Auteur : Mathurin Hauville

## Utilisation

### GET http://localhost:8081/api-message/commentaire

Liste l'ensemble des commentaires de l'application.

````json
[
    {
        "id": {
            "timestamp": 1708800680,
            "counter": 7604069,
            "randomValue1": 7439702,
            "randomValue2": 10632
        },
        "evenementId": 2,
        "auteurId": 3,
        "texte": "Pas ouf",
        "date": "Feb 24, 2024, 8:51:20 PM"
    },
    {
        "id": {
            "timestamp": 1708800699,
            "counter": 7604070,
            "randomValue1": 7439702,
            "randomValue2": 10632
        },
        "evenementId": 1,
        "auteurId": 1,
        "texte": "J'ai beaucoup aimé",
        "date": "Feb 24, 2024, 8:51:39 PM"
    }
]
````

### GET http://localhost:8081/api-message/commentaire/{id}

Récupère un commentaire par son identifiant. **L'identifiant est un ObjectId est non un Integer**. 

Exemple : http://localhost:8081/message-api/commentaire/65da3aa87185562988740765

````json
{
    "id": {
        "timestamp": 1708800680,
        "counter": 7604069,
        "randomValue1": 7439702,
        "randomValue2": 10632
    },
    "evenementId": 2,
    "auteurId": 3,
    "texte": "Pas ouf",
    "date": "Feb 24, 2024, 8:51:20 PM"
}
````

### GET http://localhost:8081/api-message/commentaire/evenement/{id}

Récupère l'ensemble des commentaires d'un événement par son identifiant. **L'identifiant est un Integer**.

Exemple : http://localhost:8081/message-api/commentaire/evenement/2

````json
[
  {
    "id": {
      "timestamp": 1708800680,
      "counter": 7604069,
      "randomValue1": 7439702,
      "randomValue2": 10632
    },
    "evenementId": 2,
    "auteurId": 3,
    "texte": "\"Pas ouf\"",
    "date": "Feb 24, 2024, 8:51:20 PM"
  },
  {
    "id": {
      "timestamp": 1708800730,
      "counter": 7604073,
      "randomValue1": 7439702,
      "randomValue2": 10632
    },
    "evenementId": 2,
    "auteurId": 3,
    "texte": "\"Nul\"",
    "date": "Feb 24, 2024, 8:52:10 PM"
  }
]
````

### POST http://localhost:8081/api-message/commentaire

Ajoute un commentaire à un événement.

Paramètres :
- evenementId : Identifiant de l'événement (int)
- auteurId : Identifiant de l'auteur (int)
- texte : Texte du commentaire (String)

exemple : http://localhost:8081/message-api/commentaire?auteurId=3&evenementId=2&texte=Nul

### PUT http://localhost:8081/api-message/commentaire/{id}

Modifie un commentaire par son identifiant. **L'identifiant est un ObjectId est non un Integer**.

Paramètres :
- texte : Texte du commentaire (String)

http://localhost:8081/message-api/commentaire/65da22ef0194e0550eb3cc3a?texte=genial

### DELETE http://localhost:8081/api-message/commentaire/{id}

Supprime un commentaire par son identifiant. **L'identifiant est un ObjectId est non un Integer**.

http://localhost:8081/message-api/commentaire/65da22ef0194e0550eb3cc3a