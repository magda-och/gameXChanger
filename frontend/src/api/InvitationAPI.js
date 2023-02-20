import {api} from "./configurationAPI";


export const InvitationAPI = {


    getReceived: function (userId) {
        return api.request({
            method: "GET",
            url: `/friends/requests/received/${userId}`,
            mode: 'cors',
            headers:{
                "Content-Type": "application/json",
                "Access-Control-Allow-Origin": api.baseURL,
                "Access-Control-Allow-Methods": "GET"
            }
        });
    },
    getSend: function (userId) {
        return api.request({
            method: "GET",
            url: `/friends/requests/send/${userId}`,
            mode: 'cors',
            headers:{
                "Content-Type": "application/json",
                "Access-Control-Allow-Origin": api.baseURL,
                "Access-Control-Allow-Methods": "GET"
            }
        });
    },

    create: function (invitation) {
        return api.request({
            method: "POST",
            url: `/friends/requests/`,
            data: invitation
        });
    },
    update: function (id, status) {
        return api.request({
            method: "PATCH",
            url: `/friends/requests/${id}` +"?requestStatus="+`${status}`,
            data: status,
        });
    },

    delete: function (requestId) {
        return api.request({
            method: "DELETE",
            url: `/friends/requests/${requestId}`,
        });
    },
}