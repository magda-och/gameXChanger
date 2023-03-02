import {api} from "./configurationAPI";
import AuthenticationService from "../services/AuthenticationService";


export const FriendAPI = {
    getAllFriends: function (userId) {
        return api.request({
            method: "GET",
            url: `/user/friends/${userId}`,
            mode: 'cors',
            headers:{
                'Authorization': AuthenticationService.getHeader()
            }
        });
    },

    delete: function (userId, friendId) {
        return api.request({
            method: "DELETE",
            url: `user/friends/${userId}/${friendId}`,
            headers:{
                'Authorization': AuthenticationService.getHeader()
            }
        });
    },
}