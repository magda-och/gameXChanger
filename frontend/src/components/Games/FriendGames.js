import React, {useEffect, useState} from 'react';
import Games from "./Games";
import {GameAPI} from "../../api/GameAPI";

function FriendGames(id) {
    const [friendGames, setFriendGames] = useState([])

    useEffect(() => {
        GameAPI.getMyGames(id).then(
            function (response) {
                alert("hej")
                setFriendGames(response.data)
                window.location.replace(`/games`)
            }
        ).catch(function (error) {
            console.error(`Error: ${error}`)
        });
    }, []);

    return (
        <div>
            <h2 className="text-center">Games</h2>
            <table className="table table-striped">
                <thead>
                <tr>
                    <td> Game name</td>
                    <td> Game Visibility</td>
                    <td> Game Status</td>
                    <td> Contact</td>
                </tr>
                </thead>
                <tbody>
                {
                    friendGames.map(game => {
                            return <tr>
                                <td> {game.id}</td>
                                <td>{game.name}</td>
                                <td> {game.visibility}</td>
                                <td> {game.gameStatus}</td>
                                <td> {game._owner}</td>
                            </tr>
                        }
                    )
                }
                </tbody>
            </table>
        </div>
    )
}

export default FriendGames;