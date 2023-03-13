import React, {useEffect, useState} from 'react';
import {GameAPI} from "../../api/GameAPI";
import {useLocation, useParams} from "react-router-dom";
import classes from "../Games/FriendGames.module.css";
import {UserAPI} from "../../api/UserAPI";
import {currentId} from "../Users/UserDetails";
import AuthenticationService from "../../services/AuthenticationService";
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
        UserAPI.getById(id).then(
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
    function printButtonToReservation(id_,status){
        var id = id_
        if(status==="AVAILABLE"){
            return (
                <button className="btn btn-primary" style={{background:"rgb(134, 58, 111)", border:"none"}} onClick={(e) => updateGameStatus(id,"RESERVATION", e)}>RESERVATION</button>
            )
        }else if("RESERVATION"){
            return (
                <button className="btn btn-primary" style={{background:"rgb(134, 58, 111)", border:"none"}} onClick={(e) => updateGameStatus(id,"AVAILABLE", e)}>CANCEL</button>
            )
        }else if("LENT"){
            return (
                <button className="btn btn-primary" style={{background:"rgb(134, 58, 111)", border:"none"}} onClick={(e) => updateGameStatus(id,"RETURNING", e)}>RETURN</button>
            )
        }
    }
    function printUserName(game,gameStatus){
    if(gameStatus==="RESERVATION")
    return (
        <p>by+{game.ownerID.id}</p>
    )
    }
    function updateGameStatus(id, status, e){
        console.log("cos");
        if(status==="RESERVATION") {
            GameAPI.update(id, status, currentId)
                .then(res => {
                    console.log("cos2")
                    console.log(res);
                    if (status === "LENT") {
                        alert("Game is return!")
                    } else {
                        /* alert("You dont lent game")*/
                    }
                });
        }else{
            GameAPI.update(id, status, currentId)
                .then(res => {
                    console.log("cos3")
                    console.log(res);
                    if (status === "LENT") {
                        alert("Game is lent!")
                    } else {
                        /*alert("You dont return game")*/
                    }
                });
        }
    }
    return (
        <div className={classes.friendGames}>

            {show()}
            <div>
                <div >
                    {
                        friendGames.map(game => {
                                return <div className="col-md-12 container" style={{width:"170px", float:"left",height:"170px",background:"#FFADBC",margin:"10px",borderRadius:"12px"}}>
                                    <p>{game.name}</p>
                                    <p> {game.gameStatus}</p>
                                    {printUserName(game,game.gameStatus)}
                                    {printButtonToReservation(game.id,game.gameStatus)}
                                    {/*{printButtonToLent(game)}*/}
                                </div>
                            }
                        )
                    }
                </div>
            </div>
            {/*<table className="table table-striped">
                <thead>
                <tr>
                    <td> Game Name</td>
                    <td> Game Status</td>
                </tr>
                </thead>
                <tbody>
                {
                    friendGames.map(game => {
                            return <tr key={game.id}>
                                <td>{game.name}</td>
                                <td> {game.gameStatus}</td>
                            </tr>
                        }
                    )
                }
                </tbody>
            </table>*/}
        </div>
    )
}


export default FriendGames;