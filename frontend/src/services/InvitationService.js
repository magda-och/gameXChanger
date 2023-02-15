import axios from "axios";

const API_URL = "http://localhost:3100/friends/requests";
const API_URL_RECEIVED = "http://localhost:3100/friends/requests/received/2";
const API_URL_SEND= "http://localhost:3100/friends/requests/send/2";

class InvitationService{
    getAllRequests(){
        return axios.get(API_URL).then();
    }
    getSendRequests(){
        return axios.get(API_URL_SEND).then();
    }
    getReceivedRequests(){
        return axios.get(API_URL_RECEIVED).then();
    }
}

export default new InvitationService();