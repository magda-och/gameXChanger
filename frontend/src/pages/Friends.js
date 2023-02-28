import React, {useEffect, useState} from 'react';
import classes from "./Friends.module.css"
import {FriendAPI} from "../api/FriendAPI";
import FriendGames from "../components/Games/FriendGames";
import {Link} from "react-router-dom";

import React from 'react';
import classes from '../components/Friends/Friends.module.css'
import FriendsList from "../components/Friends/FriendsList";
import FriendsSearchingBar from "../components/Friends/FriendsSearchingBar";

function Friends() {

    const [friends, setFriends] = useState([])

    const userId = 2;

    useEffect(() => {
        FriendAPI.getAllFriends(userId).then(
            function (response) {
                setFriends(response.data)
            }
        ).catch(function (error) {
            console.error(`Error: ${error}`)
        });
    }, []);

    const removeFriend = async (id) => {
        try {
            const res = await FriendAPI.delete(2, id)
            console.log('Item successfully deleted.')
            alert("Friend successfully deleted.")
            window.location.replace('/profile/friends')
            return res;
        } catch (error) {
            alert(error)
        }
    }

   /* const getFriendGames = (id) => {
        alert("wchodze do game friend")
        window.location.replace(`/profile/games/${id}`);
        return <FriendGames id = {id}/>
    };*/

    return (
        <div>
            <FriendsSearchingBar />
        <div className={classes.friends}>
            <div>
                <h1 className="text-center "> All Friends </h1>
                <table className="table table-striped">
                    <thead>
                    <tr>
                        <td> Friend id</td>
                        <td> Friend first name</td>
                        <td> Friend last name</td>
                        <td> Friend city</td>
                    </tr>
                    </thead>
                    <tbody>
                    {
                        friends.map(
                            friend => {
                                return <tr key={friend.id}>
                                    <td>{friend.id}</td>
                                    <td>{friend.firstName}</td>
                                    <td>{friend.lastName}</td>
                                    <td>{friend.city}</td>
                                    <td>
                                        <Link
                                            to={{
                                                pathname: `/profile/games/${friend.id}`,
                                                state: { friends: friend }
                                            }}
                                        >
                                            <button className="btn btn-outline-secondary">Show games</button>
                                        </Link>
                                    </td>
                                    <td>
                                        <button className="btn btn-outline-secondary"
                                                onClick={() => removeFriend(friend.id)}>Delete
                                        </button>
                                    </td>
                                </tr>
                            }
                        )
                    }
                    </tbody>
                </table>
            </div>
        </div>
    )
}

export default Friends