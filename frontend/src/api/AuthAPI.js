import {api} from "./configurationAPI";


export const AuthAPI = {
    create: function(user) {
        return api.request({
            method: "POST",
            url: `/auth/register`,
            data: user
        });
    },

    login: function (loginRequest){
        return api.request({
            method: "POST",
            url: `/auth/login`,
            data: loginRequest
        });
    },
}