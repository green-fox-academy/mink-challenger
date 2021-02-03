import axios from 'axios'

const COMMITMENT_URL = "http://localhost:8080/api/commitmentDAOsByUser"
const CREATE_COMMITMENT_URL = "http://localhost:8080/api/commitment"

class CommitmentService {
    
    getCommitments() {
        return axios.get(COMMITMENT_URL);
    }

    createCommitment(commitment) {
        return axios.post(CREATE_COMMITMENT_URL, commitment);
    }
}

export default new CommitmentService()