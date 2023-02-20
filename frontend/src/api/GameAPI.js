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
            url: `/games/myGames/${userId}`
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
    update: function(gameId, game) {
        return api.request({
            method: "PUT",
            url: `/games/${gameId}`,
            data: game,
        });
    },

    delete: function (gameId){
        return api.request({
            method: "DELETE",
            url: `/games/${gameId}`,
        });
    },

}