import React from 'react';
import UserList from "../components/Users/UserList";
import classes from "../components/ProfileLayout/Profile.module.css";
function Profile() {
    return (
        <div className={classes.profile}>
            <UserList/>
        </div>
    );
}

export default Profile;