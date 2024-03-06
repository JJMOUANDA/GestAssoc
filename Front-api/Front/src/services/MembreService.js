import axios from 'axios';

const API_URL = 'http://localhost:8082/membres';

class MembreService {
    getAllMembres() {
        return axios.get(API_URL);
    }

    getMembreById(id) {
        return axios.get(`${API_URL}/${id}`);
    }

    createMembre(membre) {
        return axios.post(API_URL, membre);
    }

    updateMembre(id, membre) {
        return axios.put(`${API_URL}/${id}`, membre);
    }

    deleteMembre(id) {
        return axios.delete(`${API_URL}/${id}`);
    }
}

export default new MembreService();
