import {api} from "./configurationAPI";

export const GameAPI = {
    getAll: function() {
        return api.request({
            method: "GET",
            url: `/games`
        });
    },
    getMyGames: function(userId) {
        return api.request({
            method: "GET",
            url: `/games/myGames/${userId}`,
            mode: 'cors',
            headers:{
                "Content-Type": "application/json",
                "Access-Control-Allow-Origin": api.baseURL,
                "Access-Control-Allow-Methods": "GET"
            }
        });
    },
    getBorrowedGames: function(userId) {
        return api.request({
            method: "GET",
            url: `/games/borrowedGames/${userId}`
        });
    },
    create: function(ownerId, game) {
        return api.request({
            method: "POST",
            url: `/games/${ownerId}`,
            data: game
        });
    },
    update: function(gameId, status, ownerId) {
        return api.request({
            method: "PATCH",
            url: `/games/${gameId}?gameStatus=${status}&ownerId=${ownerId}`,
            data: status, ownerId
        });
    },

    delete: function (gameId){
        return api.request({
            method: "DELETE",
            url: `/games/${gameId}`,
        });
    },

}