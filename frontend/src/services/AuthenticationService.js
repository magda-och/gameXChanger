import {UserAPI} from "../api/UserAPI";


const USER_NAME_SESSION_ATTRIBUTE_NAME = 'authenticatedUser'
const HEADER_SESSION= 'Authorization:'
const USER_ID_SESSION= 'id'
const USER_ROLE = 'role';

class AuthenticationService {


    registerJwtSuccessfulLogin(email, token) {
        sessionStorage.setItem(USER_NAME_SESSION_ATTRIBUTE_NAME, email)
        sessionStorage.setItem(HEADER_SESSION, this.createJWTToken(token))
        this.setUserIdAndRole();
      /*  this.setupAxiosInterceptors(this.createJWTToken(token))*/
    }

    createJWTToken(token) {
        return 'Bearer ' + token
    }


    logout() {
        sessionStorage.removeItem(USER_NAME_SESSION_ATTRIBUTE_NAME);
    }

    isUserLoggedIn() {
        let user = sessionStorage.getItem(USER_NAME_SESSION_ATTRIBUTE_NAME)
        return user !== null;

    }

    getHeader(){
        let header = sessionStorage.getItem(HEADER_SESSION)
        if (header === null) return ''
        return header
    }
    getLoggedInUserName() {
        let user = sessionStorage.getItem(USER_NAME_SESSION_ATTRIBUTE_NAME)
        if (user === null) return ''
        return user
    }

    setUserIdAndRole(){
        UserAPI.getByEmail(this.getLoggedInUserName()).then(
            function (response) {
                sessionStorage.setItem(USER_ID_SESSION, response.data.id);
                sessionStorage.setItem(USER_ROLE, response.data.role);
            }
        ).catch(function (error) {
            console.error(`Error: ${error}`)
        });
    }

    getLoggedInUserID() {
        let id = sessionStorage.getItem(USER_ID_SESSION);
        //if (id === null) return ''
        return id
    }

    getLoggedInUserRole() {
        let role = sessionStorage.getItem(USER_ROLE)
        if (role === null) return 'anonymous'
        return role
    }

   /* setupAxiosInterceptors(token) {
        api.interceptors.request.use(
            (config) => {
                if (this.isUserLoggedIn()) {
                    config.headers.authorization = token
                }
                return config
            }
        )
    }*/
}

export default new AuthenticationService()