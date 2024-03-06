import axios from 'axios';

const API_URL = 'http://localhost:8082/evenement-api/evenement';

class EvenementService {
    getAllEvent() {
        return axios.get(`${API_URL}/listeEvenement`);
    }

    getEventById(id) {
        return axios.get(`${API_URL}/${id}`);
    }

    createEvent(event) {
        return axios.post(API_URL, event);
    }

    updateEvent(id, event) {
        return axios.put(`${API_URL}/${id}`, event);
    }

    deleteEvent(id) {
        return axios.delete(`${API_URL}/${id}`);
    }
}

export default new EvenementService();
