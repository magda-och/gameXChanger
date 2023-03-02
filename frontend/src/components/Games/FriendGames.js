import React, {useEffect, useState} from 'react';
import {GameAPI} from "../../api/GameAPI";
import {useLocation, useParams} from "react-router-dom";
import classes from "../Games/FriendGames.module.css";
import {UserAPI} from "../../api/UserAPI";
import {currentId} from "../Users/UserDetails";
function FriendGames() {
    const{id} = useParams();
    const [friendGames, setFriendGames] = useState([])
    const [user, setUser] = useState(null)
    useEffect(() => {
        GameAPI.getMyGames(id).then(
            function (response) {
                setFriendGames(response.data)
            }
        ).catch(function (error) {
            console.error(`Error: ${error}`)
        });
    }, []);

    useEffect(()=>{
        UserAPI.getById(currentId).then(
            (response)=>{
                setUser(response.data)
            }
        ).catch(function (error){
            console.error(`Error: ${error}`)
        });
    },[]);

function show(){
        if (user != null) {
            return(
                <div>
                <h2>{user.firstName} {user.lastName} Games</h2>
                <p>Contact to borrow:
                    {<br/>}Email: {user.email}
                    {<br/>}Phone number: {user.phoneNumber}
                    {<br/>}Location: {user.city}
                </p>
                </div>
        )
        }
        return <p>email</p>;
    }

    return (
        <div className={classes.friendGames}>

            {show()}
            <table className="table table-striped">
                <thead>
                <tr>
                    <td> Game Id</td>
                    <td> Game Name</td>
                    <td> Game Status</td>
                </tr>
                </thead>
                <tbody>
                {
                    friendGames.map(game => {
                            return <tr key={game.id}>
                                <td> {game.id}</td>
                                <td>{game.name}</td>
                                <td> {game.gameStatus}</td>
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