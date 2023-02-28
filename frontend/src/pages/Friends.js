import React from 'react';
import classes from '../components/Friends/Friends.module.css'
import FriendsList from "../components/Friends/FriendsList";
import FriendsSearchingBar from "../components/Friends/FriendsSearchingBar";

function Friends() {
    return (
        <div>
            <FriendsList/>
            <FriendsSearchingBar />
        </div>
    );
}

export default Friends