import React from 'react';
import {GameAPI} from "../../api/GameAPI";
import {Link} from "react-router-dom";

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
    function printButtonToLent(game){
        if(game.gameStatus==="AVAILABLE"){
            return (
                <Link
                    to={{
                        pathname: `/profile/lend/${game.id}`,
                        state: { game: game.id }
                    }}
                >
                    <button className="btn btn-outline-secondary">LENT</button>
                </Link>
            )
        } else if(game.gameStatus === "LENT"){
            return(
                <div>{game.actualUserDto.firstName + " " + game.actualUserDto.lastName}
                    <button
                        className="btn btn-outline-secondary"
                        onClick={() => giveBackGame(game.id, game.actualUserDto.id)}>
                        given back
                    </button>
                </div>)
        }
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
                                    return <div class="col-md-12 container" style={{width:"150px", float:"left",height:"150px",background:"gray",margin:"10px"}}>
                                        <p>{game.name}</p>
                                        <p> {game.gameStatus}</p>
                                        {printButtonToLent(game)}
                                        <button className="btn btn-danger"
                                                onClick={() => removeGame(game.id)}>Delete
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


