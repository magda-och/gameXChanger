import {useEffect, useState} from "react";
import Borrowed from "./Borrowed";
import {GameAPI} from "../../api/GameAPI";
import AuthenticationService from "../../services/AuthenticationService";

function BorrowedGameList() {
    const [games, setGames] = useState([])

    const userId = AuthenticationService.getLoggedInUserID()

    useEffect(() => {
        GameAPI.getBorrowedGames(userId).then(
            function (response) {
                setGames(response.data)
            }
        ).catch(function (error) {
            console.error(`Error: ${error}`)
        });
    }, []);

    return (
        <Borrowed games = {games}/>
    )
}

export default BorrowedGameList;