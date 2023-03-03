import {api} from "../api/configurationAPI";
import {UserAPI} from "../api/UserAPI";


const USER_NAME_SESSION_ATTRIBUTE_NAME = 'authenticatedUser'
const HEADER_SESSION= 'Authorization:'
const USER_ID_SESSION= 'id'

class AuthenticationService {


    registerJwtSuccessfulLogin(email, token) {
        sessionStorage.setItem(USER_NAME_SESSION_ATTRIBUTE_NAME, email)
        sessionStorage.setItem(HEADER_SESSION, this.createJWTToken(token))
        api.setupAxiosInterceptors(this.createJWTToken(token))
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

    setUserId(){
        UserAPI.getByEmail(this.getLoggedInUserName()).then(
            function (response) {
                sessionStorage.setItem(USER_ID_SESSION, response.data)
            }
        ).catch(function (error) {
            console.error(`Error: ${error}`)
        });
    }

    getLoggedInUserID() {
        let id = sessionStorage.getItem(USER_ID_SESSION)
        if (id === null) return ''
        return id
    }

    setupAxiosInterceptors(token) {
        api.interceptors.request.use(
            (config) => {
                if (this.isUserLoggedIn()) {
                    config.headers.authorization = token
                }
                return config
            }
        )
    }
}

export default new AuthenticationService()