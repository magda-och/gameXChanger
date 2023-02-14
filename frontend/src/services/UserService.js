import axios from "axios";

const USER_REST_API_URL ='localhost:3100/user';


class UserService {
    getUsers(){
        axios.get(USER_REST_API_URL)
    }
}

export default new UserService;