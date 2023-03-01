import React, {useState} from 'react';
import {GameAPI} from "../../api/GameAPI";
// import classes from "*.module.css";
import LendGameFunction from "./LendGame";

// import classes from '*.module.css';

function Games(props) {
    const [ setSelectedOption] = useState('');
    const handleOptionChange = (event) => setSelectedOption(event.target.value);
    const lendGame = async (id, option) => {
        try {
            const res = await GameAPI.borrowGame(id, option)//, visibility)
            console.log('Item successfully updated.')
            alert("Game successfully updated.")
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

    const removeGame = async (id) => {
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


    const displayGames = (props) => {
        const {games} = props;

        function showInput(game) {
            if (game.gameStatus === 'AVAILABLE') {
                return (
                    <td>
                        <LendGameFunction/>
                        {/*<button*/}
                        {/*    className="btn btn-outline-secondary"*/}
                        {/*    onClick={LendGameFunction(game.id)}>*/}
                        {/*    /!* wywołaj metode popup i przekaż gameId *!/*/}
                        {/*    /!* zamiast lendGame a tam dopiero lend game i przekaż gameId i UserId*!/*/}
                        {/*    Lend*/}
                        {/*</button>*/}
                    </td>
                )
            } else if (game.gameStatus === 'LENT') {
                return (
                    <td>{game.actualUserDto.firstName + " " + game.actualUserDto.lastName}
                        <button
                            className="btn btn-outline-secondary"
                            onClick={() => giveBackGame(game.id, game.actualUserDto.id)}>
                            given back
                        </button>
                    </td>
                )
            }
        }

        return (
            <div>
                <h2 className="text-center">Games</h2>
                <table className="table table-striped">
                    <thead>
                    <tr>
                        <td>Game name</td>
                        <td>Game Status</td>
                        <td></td>
                        <td></td>
                    </tr>
                    </thead>
                    <tbody>
                    {games.map(game => {
                        return (
                            <tr key={game.id}>
                                <td>{game.name}</td>
                                <td>{game.gameStatus}</td>
                                {showInput(game)}
                                <td>
                                    <button className="btn btn-outline-secondary"
                                            onClick={() => removeGame(game.id)}>Delete
                                    </button>
                                </td>
                            </tr>
                        );
                    })}
                    </tbody>
                </table>
            </div>
        );

    }
    return (
        <>
            {displayGames(props)}
        </>
    );
}

export default Games;