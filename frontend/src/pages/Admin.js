import React, {useEffect} from 'react';
import UserList from "../components/Users/UserList";
import AuthenticationService from "../services/AuthenticationService";
import {Navigate} from "react-router-dom";

function Admin() {

    const isUserLoggedIn = AuthenticationService.isUserLoggedIn();
    const userRole = AuthenticationService.getLoggedInUserRole();

    useEffect(()=>{
        if (!isUserLoggedIn && userRole !== "ADMIN") {
            return <Navigate to="/" />;
        }
    }, []);

    return (
        <div><UserList/></div>
    );
}

export default Admin;