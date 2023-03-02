import {api} from "./configurationAPI";
import AuthenticationService from "../services/AuthenticationService";


export const UserAPI = {
    getAll: function() {
        return api.request({
            method: "GET",
            url: `/user`,
            headers:{'Authorization': AuthenticationService.getHeader()}
        });
    },
    getById: function(userId) {
        return api.request({
            method: "GET",
            url: `/user/${userId}`,
            headers:{'Authorization': AuthenticationService.getHeader()}
        });
    },
    create: function(user) {
        return api.request({
            method: "POST",
            url: `/user/auth/register`,
            data: user
        });
    },
    update: function(userId, user) {
        return api.request({
            method: "PUT",
            url: `/user/${userId}`,
            data: user,
            headers:{'Authorization': AuthenticationService.getHeader()}
        });
    },

    delete: function (userId){
        return api.request({
            method: "DELETE",
            url: `/user/${userId}`,
            headers:{'Authorization': AuthenticationService.getHeader()}
        });
    },

    findUsers: function (params) {
        return api.request({
            method: "GET",
            url: `/user/name?${params}`,
            headers:{'Authorization': AuthenticationService.getHeader()}
        })
    },
    login: function (loginRequest){
        return api.request({
            method: "POST",
            url: `/user/auth/login`,
            data: loginRequest
        });
    },
    getByEmail: function(userEmail) {
        return api.request({
            method: "GET",
            url: `/user/me/${userEmail}`,
            headers:{'Authorization': AuthenticationService.getHeader()}
        });
    }

}