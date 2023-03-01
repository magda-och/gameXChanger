import {useEffect, useState} from "react";

import {GameAPI} from "../api/GameAPI";
// import Games from "../components/Games/Games";
import Games2 from "../components/Games/Games2";


function MyGameList() {
    const [games, setGames] = useState([])

    useEffect(() => {
        GameAPI.getMyGames(1).then(
            function (response) {
                setGames(response.data)
            }
        ).catch(function (error) {
            console.error(`Error: ${error}`)
        });
    }, []);

    return (
        <Games2 games = {games}/>
      //  <Games games = {games}/>
    )
}

export default MyGameList;