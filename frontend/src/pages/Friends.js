import React from 'react';
import classes from '../components/Friends/Friends.module.css'
import FriendsList from "../components/Friends/FriendsList";

function Friends() {
    return (
        <div className={classes.friends}>
            <FriendsList/>
        </div>
    );
}

export default Friends