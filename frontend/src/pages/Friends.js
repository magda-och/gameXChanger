import React from 'react';
import FriendService from "../services/FriendService";
import classes from '../styles/Friends.module.css'

function Friends() {
    return (
        <div className={classes.friends}>
            <FriendService/>
        </div>
    );
}

export default Friends