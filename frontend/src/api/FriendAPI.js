import {api} from "./configurationAPI";

export const FriendAPI = {
    getAll: function (userId) {
        return api.request({
            method: "GET",
            url: `/friends/${userId}/`
        });
    },

    delete: function (friendId) {
        return api.request({
            method: "DELETE",
            url: `/friends/${friendId}`,
        });
    },
}