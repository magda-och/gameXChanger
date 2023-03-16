import {api} from "./configurationAPI";
import AuthenticationService from "../services/AuthenticationService";


export const UserAPI = {
    getAll: function() {
        return api.request({
            method: "GET",
            url: `/user/all`,
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

    update: function(userId, user) {
        return api.request({
            method: "PATCH",
            url: `/user/${userId}`,
            data: user,
            headers:{
                'Authorization': AuthenticationService.getHeader(),
                "Access-Control-Allow-Origin": api.baseURL,
            }
        });
    },

    delete: function (userId){
        return api.request({
            method: "DELETE",
            url: `/user/${userId}`,
            headers:{'Authorization': AuthenticationService.getHeader()}
        });
    },

    getNotFriends: function (userId) {
        return api.request({
            method: "GET",
            url: `/user/notfriends/${userId}`,
            headers:{'Authorization': AuthenticationService.getHeader()}
        })
    },

    getByEmail: function(userEmail) {
        return api.request({
            method: "GET",
            url: `/user/me/${userEmail}`,
            headers:{'Authorization': AuthenticationService.getHeader()}
        });
    }

}