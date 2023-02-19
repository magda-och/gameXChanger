import {useEffect, useState} from "react";
import Borrowed from "../components/Games/Borrowed";
import {GameAPI} from "../api/GameAPI";

function BorrowedGameList() {
    const [games, setGames] = useState([])

    useEffect(() => {
        GameAPI.getBorrowedGames(2).then(
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