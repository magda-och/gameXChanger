import {api} from "./configurationAPI";


export const UserAPI = {
    getAll: function() {
        return api.request({
            method: "GET",
            url: `/user`
        });
    },
    getById: function(userId) {
        return api.request({
            method: "GET",
            url: `/user/${userId}`
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
        });
    },

    delete: function (userId){
        return api.request({
            method: "DELETE",
            url: `/user/${userId}`,
        });
    },

    findUsers: function (params) {
        return api.request({
            method: "GET",
            url: `/user/name?${params}`
        })
    },
    login: function (user){
        return api.request({
            method: "POST",
            url: `/user/auth/login`,
            data: user
        });
    }


}