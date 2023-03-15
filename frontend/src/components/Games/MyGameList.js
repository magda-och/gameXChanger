import {useEffect, useState} from "react";
import Games from "./Games";
import {GameAPI} from "../../api/GameAPI";
import AuthenticationService from "../../services/AuthenticationService";

function MyGameList() {
    const [games, setGames] = useState([])

    const userId = AuthenticationService.getLoggedInUserID()

    useEffect(() => {
        GameAPI.getMyGames(userId).then(
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