import React, {useEffect, useState} from 'react';
import {FriendAPI} from "../../api/FriendAPI";
import {Link} from "react-router-dom";
import classes from './Friends.module.css'
import FriendsSearchingBar from "./FriendsSearchingBar";
import {currentId} from "../Users/UserDetails"

function Friends() {

    const [friends, setFriends] = useState([]);

    const userId = currentId;

    useEffect(() => {
        FriendAPI.getAllFriends(userId).then(
            function (response) {
                setFriends(response.data)
            }
        ).catch(function (error) {
            console.error(`Error: ${error}`)
        });
    }, [userId]);

    const removeFriend = async (id) => {
        try {
            const res = await FriendAPI.delete(userId, id)
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
            <div className="col-md-5">
                <FriendsSearchingBar/>
            </div>

            <div className="row-cols-3">
                <h1 className="text-center "> All Friends </h1>
                <table className="table table-striped">
                    <thead>
                    <tr>
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
                                    <td>{friend.firstName}</td>
                                    <td>{friend.lastName}</td>
                                    <td>{friend.city}</td>
                                    <td>
                                        <Link
                                            to={{
                                                pathname: `/profile/games/${friend.id}`,
                                                state: {friends: friend}
                                            }}
                                        >
                                            <button
                                                style={{
                                                    background: "rgb(134, 58, 111)",
                                                    border: "none",
                                                    color: "white"
                                                }}
                                                className="btn btn-outline-secondary">Show games
                                            </button>
                                        </Link>
                                    </td>
                                    <td>
                                        <button style={{
                                            background: "rgb(151, 92, 141)",
                                            border: "none",
                                            color: "white"
                                        }}
                                                className="btn btn-outline-secondary"
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