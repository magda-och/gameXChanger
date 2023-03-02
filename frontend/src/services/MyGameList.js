import {useEffect, useState} from "react";

import {GameAPI} from "../api/GameAPI";
import Games from "../components/Games/Games";



function MyGameList() {
    const [games, setGames] = useState([])

    useEffect(() => {
        GameAPI.getMyGames(2).then(
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