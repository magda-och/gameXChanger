import {api} from "./configurationAPI";
import AuthenticationService from "../services/AuthenticationService";


export const InvitationAPI = {


    getReceived: function (userId) {
        return api.request({
            method: "GET",
            url: `/friends/requests/received/${userId}`,
            mode: 'cors',
            headers:{
                'Authorization': AuthenticationService.getHeader()
               /* "Content-Type": "application/json",
                "Access-Control-Allow-Origin": api.baseURL,
                "Access-Control-Allow-Methods": "GET"*/
            }
        });
    },
    getSend: function (userId) {
        return api.request({
            method: "GET",
            url: `/friends/requests/send/${userId}`,
            mode: 'cors',
            headers:{
                'Authorization': AuthenticationService.getHeader()
               /* "Content-Type": "application/json",
                "Access-Control-Allow-Origin": api.baseURL,
                "Access-Control-Allow-Methods": "GET"*/
            }
        });
    },

    create: function (invitation) {
        return api.request({
            method: "POST",
            url: `/friends/requests`,
            data: invitation,
            headers:{'Authorization': AuthenticationService.getHeader()}
        });
    },
    update: function (id, status) {
        return api.request({
            method: "PATCH",
            url: `/friends/requests/${id}?requestStatus=${status}`,
            data: status,
            headers:{'Authorization': AuthenticationService.getHeader()}
        });
    },

    delete: function (requestId) {
        return api.request({
            method: "DELETE",
            url: `/friends/requests/${requestId}`,
            headers:{'Authorization': AuthenticationService.getHeader()}
        });
    },
}