import React, {useEffect, useState} from "react";
import {UserAPI} from "../../api/UserAPI";
import AuthenticationService from "../../services/AuthenticationService";

export let currentId

function UserDetailsPage() {

    const [user, setUser] = useState([])
    

    useEffect( () => {
            UserAPI.getByEmail(AuthenticationService.getLoggedInUserName()).then(
                function (response) {
                    setUser(response.data)
                }
            ).catch(function (error) {
                console.error(`Error: ${error}`)
            });
    }, []);

    currentId = user.id
   /* sessionStorage.setItem("id", user.id);*/

    return (
        <d>
            <h1>Hello {user.firstName}!</h1>
            <p>Your personal data: </p>
            <p>first name: {user.firstName}</p>
            <p>last name: {user.lastName}</p>
            <p>e-mail: {user.email}</p>
            <p>city: {user.city}</p>
            <p>phone number: {user.phoneNumber}</p>
        </d>
    );
}

export default UserDetailsPage;