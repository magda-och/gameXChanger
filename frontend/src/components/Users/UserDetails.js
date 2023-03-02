import React, {useEffect, useState} from "react";
import {UserAPI} from "../../api/UserAPI";
import AuthenticationService from "../../services/AuthenticationService";

function UserDetailsPage({currentUser}) {

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

    return (
        <>
            <h1>Hello {user.fi}!</h1>
            <p>{currentUser}</p>
            <p>{user.id}</p>

        </>
    );
}

export default UserDetailsPage;