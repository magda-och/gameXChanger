import React, {useEffect, useState} from "react";
import {FriendAPI} from "../../api/FriendAPI";
import FriendGames from "../Games/FriendGames";

// konfiguracja - poczytac i usatlić jak trzymamamy url
// obsługa API - wszystkie metody tutaj

function FriendsList() {

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

    const getFriendGames = (id) => {
        window.location.replace(`/games`);
        return <FriendGames id = {id}/>
    };


    return (
        <div>
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
                                return <tr>
                                    <td>{friend.id}</td>
                                    <td>{friend.firstName}</td>
                                    <td>{friend.lastName}</td>
                                    <td>{friend.city}</td>
                                    <td>
                                        <button className="btn btn-outline-secondary"
                                                onClick={() => getFriendGames(friend.id)}>Show Games
                                        </button>
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

export default FriendsList;