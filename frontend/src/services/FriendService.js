import axios from "axios";
import {useEffect, useState} from "react";
import Friends from "../pages/users/Friends";

// konfiguracja - poczytac i usatlić jak trzymamamy url
// obsługa API - wszystkie metody tutaj

function FriendsService() {
    const [friends, setFriends] = useState([]);

    const FRIEND_REST_API_URL ='http://localhost:3100/user/friends/2';

    useEffect(()=>{
        getAllFriends();
    }, []);

    const getAllFriends =() =>{
        axios.get(FRIEND_REST_API_URL)
            .then((response) =>{
                const allFriends = response.data;
                setFriends(allFriends);
            })
            .catch(error => console.error('Error: ${error'));
    }

    return (
        <Friends friends = {friends}/>
    )
}

export default FriendsService;