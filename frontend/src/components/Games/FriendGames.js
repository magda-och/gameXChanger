import React, {useEffect, useState} from 'react';
import {GameAPI} from "../../api/GameAPI";
import {useLocation, useParams} from "react-router-dom";
import classes from "../Games/FriendGames.module.css";
import {UserAPI} from "../../api/UserAPI";
import {currentId} from "../Users/UserDetails";
import AuthenticationService from "../../services/AuthenticationService";
import {InvitationAPI} from "../../api/InvitationAPI";
function FriendGames() {
    const{id} = useParams();
    const [friendGames, setFriendGames] = useState([])
    const [user, setUser] = useState(null)
    useEffect(() => {
        GameAPI.getMyGames(id).then(
            function (response) {
                setFriendGames(response.data)
                console.log(response.data)
            }
        ).catch(function (error) {
            console.error(`Error: ${error}`)
        });
    }, []);

   /* InvitationAPI.getReceived(currentId).then(
        (response) => {
            this.setState({ receivedInvitations: [{
                    requestFriendId: 1,
                    requestStatus: 1,
                    fromUserId: 1,
                    toUserId: 1,
                    message:1
                }] });
            this.setState({ receivedInvitations:response.data });
        });*/


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
    function printUserName(game,gameStatus){
        if(gameStatus==="RESERVATION" || gameStatus==="LENT")
            return (
                <p>by {game.actualUserDto.firstName+ " "+ game.actualUserDto.lastName}</p>
            )
    }
    function printButtonToReservation(game_,status){
        var game = game_
        if(status==="AVAILABLE"){
            return (
                <button className="btn btn-primary" style={{background:"#443C68", margin: "6%", border:"none"}} onClick={(e) => updateGameStatus(game,"RESERVATION", e)}>RESERVATION</button>
            )
        }else if(status==="RESERVATION"){
            return (
                <button className="btn btn-primary" style={{background:"#443C68",margin: "6%", border:"none"}} onClick={(e) => updateGameStatus(game,"AVAILABLE", e)}>CANCEL</button>
            )
        }else if(status==="LENT"){
            return (
                <button className="btn btn-primary" style={{background:"#443C68", margin: "6%", border:"none"}} onClick={(e) => updateGameStatus(game,"RETURNING", e)}>RETURN</button>
            )
        }
    }

    function updateGameStatus(game, status, e){
        console.log("cos");
        if(status==="RESERVATION") {
            GameAPI.update(game.id, status, currentId)
                .then(res => {
                    console.log("cos2")
                    console.log(res);
                    if (status === "LENT") {
                        alert("Game is return!")
                    } else {
                        /* alert("You dont lent game")*/
                    }
                    GameAPI.getMyGames(user.id).then(
                        function (response) {
                            setFriendGames(response.data)
                        }
                    ).catch(function (error) {
                        console.error(`Error: ${error}`)
                    });
                });
        }else if(status==="AVAILABLE"){
            GameAPI.update(game.id, status, game.ownerDto.id)
                .then(res => {
                    console.log("cos3")
                    console.log(res);
                    if (status === "LENT") {
                        alert("Game is lent!")
                    } else {
                        /*alert("You dont return game")*/
                    }
                    GameAPI.getMyGames(user.id).then(
                        function (response) {
                            setFriendGames(response.data)
                        }
                    ).catch(function (error) {
                        console.error(`Error: ${error}`)
                    });
                });
        }else if(status==="RETURNING"){
            GameAPI.update(game.id, status, currentId)
                .then(res => {
                    console.log("cos3")
                    console.log(res);
                    if (status === "LENT") {
                        alert("Game is lent!")
                    } else {
                        /*alert("You dont return game")*/
                    }
                    GameAPI.getMyGames(user.id).then(
                        function (response) {
                            setFriendGames(response.data)
                        }
                    ).catch(function (error) {
                        console.error(`Error: ${error}`)
                    });
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
                                return <div className="col-md-12 container" style={{width:"170px", float:"left",height:"170px",background:"#cfcbf1",margin:"10px",borderRadius:"12px"}}>
                                    <p style={{margin:0}}>{game.name}</p>
                                    <p style={{margin:0}}> {game.gameStatus}</p>

                                    {printUserName(game,game.gameStatus)}
                                    {printButtonToReservation(game,game.gameStatus)}
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