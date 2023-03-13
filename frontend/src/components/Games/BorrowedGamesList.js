import {useEffect, useState} from "react";
import Borrowed from "./Borrowed";
import {GameAPI} from "../../api/GameAPI";
import {currentId} from "../Users/UserDetails";

function BorrowedGameList() {
    const [games, setGames] = useState([])

    useEffect(() => {
        GameAPI.getBorrowedGames(currentId).then(
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