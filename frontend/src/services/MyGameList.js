import {useEffect, useState} from "react";
import Games from "../components/Games/Games";
import {GameAPI} from "../api/GameAPI";
import {currentId} from "../components/Users/UserDetails";

function MyGameList() {
    const [games, setGames] = useState([])

    useEffect(() => {
        GameAPI.getMyGames(currentId).then(
            function (response) {
                setGames(response.data)
            }
        ).catch(function (error) {
            console.error(`Error: ${error}`)
        });
    }, []);

    return (
        <Games games = {games}/>
    )
}

export default MyGameList;