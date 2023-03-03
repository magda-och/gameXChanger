import React, {useState} from 'react';
import {GameAPI} from "../../api/GameAPI";
import {Link} from "react-router-dom";
import {currentId} from "../Users/UserDetails";


function Games(props) {

    const removeGame = async(id) => {
        try {
            const res = await GameAPI.delete(id)
            console.log('Item successfully deleted.')
            alert("Game successfully deleted.")
            window.location.replace('/profile/shelf')
            return res;
        } catch (error) {
            alert(error)
        }
    }
    function printButtonToLent(id_,status){
        var id = id_
        if(status==="AVAILABLE"){
            return (
                <button className="btn btn-primary" style={{background:"rgb(134, 58, 111)", border:"none"}} onClick={(e) => updateGameStatus(id,"LENT", e)}>LENT</button>
            )
        }else{
            return (
                <button className="btn btn-primary" style={{background:"rgb(134, 58, 111)", border:"none"}} onClick={(e) => updateGameStatus(id,"AVAILABLE", e)}>RETURN</button>
            )
        }
    }
    function updateGameStatus(id, status, e){
        console.log("cos");
        if(status==="AVAILABLE") {
            GameAPI.update(id, status, currentId)
                .then(res => {
                    console.log("cos2")
                    console.log(res);
                    if (status === "LENT") {
                        alert("You lent game!")
                    } else {
                        alert("You dont lent game")
                    }
                    window.location.replace('/profile/shelf');

                });
        }else{
            GameAPI.update(id, status, 1)
                .then(res => {
                    console.log("cos3")
                    console.log(res);
                    if (status === "LENT") {
                        alert("You return game!")
                    } else {
                        alert("You dont return game")
                    }
                    window.location.replace('/profile/shelf');
                });
        }
    }
    const giveBackGame = async (id, userId) => {
        try {
            const res = await GameAPI.giveBack(id, userId)//, visibility)
            console.log('Item successfully updated.')
            alert("Game successfully updated.")
            window.location.replace('/profile/shelf')
            return res;
        } catch (error) {
            alert(error)
        }
    }
    // function printButtonToLent(game){
    //     if(game.gameStatus==="AVAILABLE"){
    //         return (
    //             <Link
    //                 to={{
    //                     pathname: `/profile/lend/${game.id}`,
    //                     state: { game: game.id }
    //                 }}
    //             >
    //                 <button className="btn btn-outline-secondary">LENT</button>
    //             </Link>
    //         )
    //     } else if(game.gameStatus === "LENT"){
    //         return(
    //             <div>{game.actualUserDto.firstName + " " + game.actualUserDto.lastName}
    //                 <button
    //                     className="btn btn-outline-secondary"
    //                     onClick={() => giveBackGame(game.id, game.actualUserDto.id)}>
    //                     given back
    //                 </button>
    //             </div>)
    //     }
    // }


    const displayGames = (props) => {
        const {games} = props;


        return (
            <div style={{resize:"both", overflow:"auto"}}>
                <h2 className="text-center">My Games</h2>
                <div>
                        <div >
                            {
                                games.map(game => {
                                        return <div className="col-md-12 container" style={{width:"170px", float:"left",height:"170px",background:"#FFADBC",margin:"10px",borderRadius:"12px"}}>
                                            <p>{game.name}</p>
                                            <p> {game.gameStatus}</p>
                                            {printButtonToLent(game.id,game.gameStatus)}
                                            {/*{printButtonToLent(game)}*/}
                                                <button className="btn btn-danger" style={{background:"rgb(151, 92, 141)", border:"none"}}
                                                        onClick={() => removeGame(game.id)}><span
                                                    className="bi bi-trash"></span>
                                                </button>
                                        </div>
                                    }
                                )
                            }
                        </div>
            </div>
            </div>
        )

    }
    return (
        <>
            {displayGames(props)}
        </>
    );
}

export default Games;
/*GameAPI.getMyGames(2).then(
            (response) => {
                this.setState({ ga:response.data});
            });
        InvitationAPI.getSend(2).then(
            (response) => {
                this.setState({ sendInvitations:response.data});
            });*/