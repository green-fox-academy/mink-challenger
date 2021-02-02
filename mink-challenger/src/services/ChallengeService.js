import axios from 'axios'

const CHALLENGE_URL = "http://localhost:8080/api/challengeDAOs"
const CREATE_CHALLENGE_URL = "http://localhost:8080/api/admin/addnewChallengeDTO"

class ChallengeSevice {

    getChallenges() {
        return axios.get(CHALLENGE_URL);
    }

    createChallenge(challenge) {
        return axios.post(CREATE_CHALLENGE_URL, challenge);
    }

    getChallengeById(challengeId) {
        return axios.get('Http://localhost:8080/api/currentChallengeDAO/' + challengeId);
    }

    updateChallenge(challenge, challengeId) {
        return axios.put("http://localhost:8080/api/admin/editChallengeDTO/" + challengeId, challenge);
    }
}

export default new ChallengeSevice()