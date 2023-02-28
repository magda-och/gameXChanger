import React, {useEffect, useState} from 'react';
import {GameAPI} from "../../api/GameAPI";
import {useLocation, useParams} from "react-router-dom";
import classes from "../Games/FriendGames.module.css";
//import classes from "../LeftSidebar/LeftSidebar.module.css";
function FriendGames() {
    const{id} = useParams();
    const [friendGames, setFriendGames] = useState([])
//console.log(state.friends.id)
    useEffect(() => {
        GameAPI.getMyGames(id).then(
            function (response) {
                //alert("hej")
                setFriendGames(response.data)
               // window.location.replace(`/profile/games`)
            }
        ).catch(function (error) {
            console.error(`Error: ${error}`)
        });
    }, []);

    return (
        <div className={classes.friendGames}>
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
                            return <tr key={game.id}>
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