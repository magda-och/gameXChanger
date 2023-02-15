import React from 'react';
import UserService from "../services/UserService";
import FriendsService from "../services/FriendService";

function Profile() {
    return (
        <>
            <UserService/>
            <FriendsService/>
        </>
    );
}

export default Profile;
