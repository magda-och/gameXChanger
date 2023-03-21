import React from 'react';
import classes from "../components/ProfileLayout/Profile.module.css";
import UserDetailsPage from "../components/Users/UserDetails";

function Profile() {

    return (
        <div className={classes.profile}>
            <UserDetailsPage/>
        </div>
    );
}

export default Profile;