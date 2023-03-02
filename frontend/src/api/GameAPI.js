import {api} from "./configurationAPI";
import AuthenticationService from "../services/AuthenticationService";

export const GameAPI = {
    getAll: function() {
        return api.request({
            method: "GET",
            url: `/games`,
            headers:{'Authorization': AuthenticationService.getHeader()}
        });
    },
    getMyGames: function(userId) {
        return api.request({
            method: "GET",
            url: `/games/myGames/${userId}`,
            mode: 'cors',
            headers:{
                'Authorization': AuthenticationService.getHeader()
                /*"Content-Type": "application/json",
                "Access-Control-Allow-Origin": api.baseURL,
                "Access-Control-Allow-Methods": "GET"*/
            }
        });
    },
    getBorrowedGames: function(userId) {
        return api.request({
            method: "GET",
            url: `/games/borrowedGames/${userId}`,
            headers:{'Authorization': AuthenticationService.getHeader()}
        });
    },
    create: function(ownerId, game) {
        return api.request({
            method: "POST",
            url: `/games/${ownerId}`,
            data: game,
            headers:{'Authorization': AuthenticationService.getHeader()}
        });
    },
    update: function(gameId, status, ownerId) {
        return api.request({
            method: "PATCH",
            url: `/games/${gameId}?gameStatus=${status}&ownerId=${ownerId}`,
            data: status, ownerId,
            headers:{'Authorization': AuthenticationService.getHeader()}
        });
    },

    delete: function (gameId){
        return api.request({
            method: "DELETE",
            url: `/games/${gameId}`,
            headers:{'Authorization': AuthenticationService.getHeader()}
        });
    },

    getMyFriendsGames: function (userId){
        return api.request({
            method: "GET",
            url: `/games/myfriends/${userId}`,
            mode: 'cors',
            headers:{
                "Content-Type": "application/json",
                "Access-Control-Allow-Methods": "GET"
            }
        });
    },
}