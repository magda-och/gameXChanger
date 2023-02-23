import React, {useEffect, useState} from 'react';
import Games from "./Games";
import {GameAPI} from "../../api/GameAPI";

function FriendGames(id) {
    const [friendGames, setFriendGames] = useState([])

    useEffect(() => {
        GameAPI.getMyGames(id).then(
            function (response) {
                alert("hej")
                setFriendGames(response.data)
                window.location.replace(`/games`)
            }
        ).catch(function (error) {
            console.error(`Error: ${error}`)
        });
    }, []);

    return (
        <Games games = {friendGames}/>
    )
}

export default FriendGames;