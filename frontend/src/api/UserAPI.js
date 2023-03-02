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
            url: `/user`,
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

    getNotFriends: function (userId) {
        return api.request({
            method: "GET",
            url: `/user/notfriends/${userId}`,
            mode: 'cors',
            headers:{
                "Content-Type": "application/json",
                "Access-Control-Allow-Origin": api.baseURL,
                "Access-Control-Allow-Methods": "GET"
            }
        });
    },


}