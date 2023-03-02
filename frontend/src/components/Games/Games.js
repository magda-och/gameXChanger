import React, {useState} from 'react';
import {GameAPI} from "../../api/GameAPI";
import {InvitationAPI} from "../../api/InvitationAPI";

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
    function printButtonToLent(id,status){
        if(status==="AVAILABLE"){
            return (
                <button className="btn btn-primary" onClick={(e) => updateInvitationStatus(id,"LENT", e)}>LENT</button>
            )
        }else{
            return (
                <button onClick={(e) => updateInvitationStatus(id,"AVAILABLE", e)}>RETURN</button>
            )
        }
    }
    function updateInvitationStatus(id, status, e){
        console.log("cos");
        GameAPI.update(id, status, 2)
            .then(res =>{
                console.log("cos2")
                console.log(res);
                if(status==="LENT"){
                    alert("You lent game!")
                } else {
                    alert("You dont lent game")
                }
                window.location.replace('/profile/shelf');
                /*GameAPI.getMyGames(2).then(
                    (response) => {
                        this.setState({ ga:response.data});
                    });
                InvitationAPI.getSend(2).then(
                    (response) => {
                        this.setState({ sendInvitations:response.data});
                    });*/
            });
    }

    const displayGames = (props) => {
        const {games} = props;

        return (
            <div>
                <h2 className="text-center">My Games</h2>
                <div>
                        <div >
                            {
                                games.map(game => {
                                        return <div className="col-md-12 container" style={{width:"150px", float:"left",height:"150px",background:"#ADD8E6",margin:"10px"}}>
                                            <p>{game.name}</p>
                                            <p> {game.gameStatus}</p>
                                            {printButtonToLent(game.id,game.gameStatus)}

                                                <button className="btn btn-danger"
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