import {api} from "./configurationAPI";

export const FriendAPI = {
    getAllFriends: function (userId) {
        return api.request({
            method: "GET",
            url: `/user/friends/${userId}`,
            mode: 'cors',
            headers:{
                "Content-Type": "application/json",
                "Access-Control-Allow-Origin": api.baseURL,
                "Access-Control-Allow-Methods": "GET"
            }
        });
    },

    delete: function (userId, friendId) {
        return api.request({
            method: "DELETE",
            url: `user/friends/${userId}/${friendId}`,
        });
    },
}