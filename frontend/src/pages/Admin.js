import React from 'react';
import UserList from "../components/Users/UserList";
import AuthenticationService from "../services/AuthenticationService";
import {Navigate} from "react-router-dom";

function Admin() {

    const isUserLoggedIn = AuthenticationService.isUserLoggedIn();
    const userRole = AuthenticationService.getLoggedInUserRole();


    if (isUserLoggedIn && userRole !== "ADMIN") {
        return <Navigate to="/"/>;
    } else {
        return (
            <div>
                <div><UserList/></div>
            </div>
        );
    }
}

export default Admin;