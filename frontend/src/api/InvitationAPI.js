import {api} from "./configurationAPI";


export const InvitationAPI = {


    getReceived: function (userId) {
        return api.request({
            method: "GET",
            url: `/friends/requests/received/${userId}`
        });
    },
    getSend: function (userId) {
        return api.request({
            method: "GET",
            url: `/friends/requests/send/${userId}`
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
            url: `/${id}?requestStatus="+${status}`,
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