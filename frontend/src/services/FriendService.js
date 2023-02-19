import {useEffect, useState} from "react";
import Friends from "../components/Users/Friends";
import {FriendAPI} from "../api/FriendAPI";

// konfiguracja - poczytac i usatlić jak trzymamamy url
// obsługa API - wszystkie metody tutaj

function FriendsService() {

    const [friends, setFriends] = useState([])

    useEffect(() => {
        FriendAPI.getAll(2).then(
            function (response) {
                setFriends(response.data)
            }
        ).catch(function (error) {
            console.error(`Error: ${error}`)
        });
    }, []);

    return (
        <Friends friends = {friends}/>
    )
}

export default FriendsService;