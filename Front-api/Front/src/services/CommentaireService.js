import axios from 'axios';

const API_URL = 'http://localhost:8081/message-api/commentaire';

class CommentaireService {

    getAllCommentaires() {
        return axios.get(API_URL);
    }

    getCommentaireById(id) {
        return axios.get(`${API_URL}/${id}`);
    }

    getCommentaireByEvenementId(id) {
        return axios.get(`${API_URL}/evenement/${id}`);
    }

    createCommentaire = (idEvenement, idAuteur, texteCommentaire) => {
        return axios.post(API_URL, null, {
            params: {
                evenementId: idEvenement,
                auteurId: idAuteur,
                texte: texteCommentaire
            }
        });
    };

    updateCommentaire(id, texte) {
        return axios.put(`${API_URL}/${id}`, texte);
    }

    deleteCommentaire(id) {
        return axios.delete(`${API_URL}/${id}`);
    }
}

export default new CommentaireService();
