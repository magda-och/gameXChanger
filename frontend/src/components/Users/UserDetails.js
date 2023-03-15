import React, {useEffect, useState} from "react";
import {UserAPI} from "../../api/UserAPI";
import AuthenticationService from "../../services/AuthenticationService";

export let currentId

function UserDetailsPage() {

    const [user, setUser] = useState([])


    useEffect(() => {
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
        <div style={{marginLeft: "5%", background:"#cfcbf1", borderRadius:"12px", width:"80%"}}>
            <d>
                <h1 style={{height:"60px"}}>Hello {user.firstName}!</h1>
                <p>Your personal data: </p>
                <p>First name: {user.firstName}</p>
                <p>Last name: {user.lastName}</p>
                <p>E-mail: {user.email}</p>
                <p>City: {user.city}</p>
                <p>Phone number: {user.phoneNumber}</p>
            </d>
        </div>
    );
}

export default UserDetailsPage;