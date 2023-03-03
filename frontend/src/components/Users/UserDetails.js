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
    sessionStorage.setItem("id", user.id);

    return (
        <d>
            <h1>Hello {user.firstName}!</h1>
            <p></p>
            <p>{user.firstName}</p>
            <p>{user.lastName}</p>
            <p>{user.email}</p>
            <p>{user.city}</p>
            <p>{user.phoneNumber}</p>
        </d>
    );
}

export default UserDetailsPage;