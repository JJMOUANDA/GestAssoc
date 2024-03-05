import axios from 'axios';

const API_URL = 'http://localhost:8080/lieux';

class LieuService {
    getAllLieux() {
        return axios.get(API_URL);
    }

    getLieuById(id) {
        return axios.get(`${API_URL}/${id}`);
    }

    createLieu(lieu) {
        return axios.post(API_URL, lieu);
    }

    updateLieu(id, lieu) {
        return axios.put(`${API_URL}/${id}`, lieu);
    }

    deleteLieu(id) {
        return axios.delete(`${API_URL}/${id}`);
    }
}

export default new LieuService();
