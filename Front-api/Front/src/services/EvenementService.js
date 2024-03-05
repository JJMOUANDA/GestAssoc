import axios from 'axios';

const API_URL = 'http://localhost:8080/evenement';

class EvenementService {
    getAllEvent() {
        return axios.get(API_URL);
    }

    getEventById(id) {
        return axios.get(`${API_URL}/${id}`);
    }

    createEvent(lieu) {
        return axios.post(API_URL, lieu);
    }

    updateEvent(id, lieu) {
        return axios.put(`${API_URL}/${id}`, lieu);
    }

    deleteEvent(id) {
        return axios.delete(`${API_URL}/${id}`);
    }
}

export default new EvenementService();
