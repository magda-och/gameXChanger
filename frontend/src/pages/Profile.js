import React from 'react';
import classes from "../components/ProfileLayout/Profile.module.css";
import AuthenticationService from "../services/AuthenticationService";
import UserDetailsPage from "../components/Users/UserDetails";

function Profile() {


    const  currentUser = AuthenticationService.getLoggedInUserName();

    return (
        <div className={classes.profile}>
            <UserDetailsPage currentUser={currentUser}/>
        </div>
    );
}

export default Profile;