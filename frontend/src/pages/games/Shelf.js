import React from 'react';
import AddGame from "./AddGame";

function Games(props) {

    const displayGames = (props) => {
        const {games} = props;

        if (games.length > 0) {
            return (
                <ul>
                    {
                        games.map(game =>
                            <li key={game.id}>
                                <p>{game.name}</p>
                                <p> {game.visibility}</p>
                                <p> {game.status}</p>
                                <p> {game.actual}</p>
                                <p></p>
                            </li>
                        )
                    }
                </ul>
            )
        } else {
            return (
                <>
                    <h3>No games yet</h3>
                    <AddGame/>
                </>


            )
                ;
        }
    }
    return (
        <>
            {displayGames(props)}
        </>
    );
}

export default Games;