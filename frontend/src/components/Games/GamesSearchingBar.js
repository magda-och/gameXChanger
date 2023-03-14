import React, { useState} from "react";
import {GameAPI} from "../../api/GameAPI";
import classes from "../../pages/Shelf.module.css"
import "../../pages/Shelf.module.css"
import {currentId} from "../Users/UserDetails";

export default function GamesSearchingBar() {
    const [state, setstate] = useState({
        query: '',
        list: []
    })

    let games;

   GameAPI.getMyFriendsGames(currentId).then(
        response => {
            const data = response.data;
            games = Object.values(data)
        });


    const handleChange = (e) => {
        const results = games.filter(game => {
            if (e.target.value === "") return games
            return game.name.toLowerCase().includes(e.target.value.toLowerCase())
        })
        setstate({
            query: e.target.value,
            list: results,
        })
    }

    return (
        <div className={classes.friendsGames}>
            <div className="row d-flex justify-content-end">
                <h3 style={{fontSize: "18px"}}> Search your friend's games </h3>
                <form>
                    <input
                        size={30}
                        type="search"
                        value={state.query}
                        onChange={handleChange}
                        placeholder="Enter name of the game"
                    />
                </form>
                <ul style={{textAlign: "left"}}>
                    {(state.query === '' ? "No users match the query" : !state.list.length ? "Your query did not return any results" : state.list.map(game => {
                        return <li key={game.id}>
                            <td id="1">{game.name}&nbsp;</td>
                            <td id="1"> {game.gameStatus}&nbsp;&nbsp;</td>
                            <td id="1"> {game.ownerDto.firstName}</td>
                        </li>
                    }))}
                </ul>
            </div>
        </div>
    )


}
