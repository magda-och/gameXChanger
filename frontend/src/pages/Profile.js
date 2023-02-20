import React from 'react';
import UserService from "../services/UserService";
import classes from "../components/ProfileLayout/Profile.module.css";
function Profile() {
    return (
        <div className={classes.profile}>
            <UserService/>
        </div>
    );
}

export default Profile;