import axios from 'axios'

const CHALLENGE_URL = "http://localhost:8080/api/challengeDAOs"
const CREATE_CHALLENGE_URL = "http://localhost:8080/api/newChallengeDTO"

class ChallengeSevice {

    getChallenges() {
        return axios.get(CHALLENGE_URL);
    }

    createChallenge(challenge) {
        return axios.post(CREATE_CHALLENGE_URL, challenge);
    }
}

export default new ChallengeSevice()