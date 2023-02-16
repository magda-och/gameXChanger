import axios from "axios";
import {useEffect, useState} from "react";
import Games from "../pages/games/Shelf";

function GameService() {
    const [games, setGames] = useState({
       name: "",
    });

    const GAME_REST_API_URL ='http://localhost:3100/games';

    useEffect(()=>{
        getALlGames();
    }, []);

    const getALlGames =() =>{
        axios.get(GAME_REST_API_URL)
            .then((response) =>{
                const allGames = response.data;
                setGames(allGames);
            })
            .catch(error => console.error('Error: ${error'));
    }

    return (
        <Games games = {games}/>
    )
}

export default GameService;