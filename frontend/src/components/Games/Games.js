import React, {useState} from 'react';
import {GameAPI} from "../../api/GameAPI";
// import Select from "react-select";



function Games(props) {

    const [value, setValue] = useState("AVAILABLE");

    const handleStatus = (e) => {
        setValue(e.target.value);
    };



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


        return (
            <div>
                <h2 className="text-center">Games</h2>
                <table className="table table-striped">
                    <thead>
                    <tr>
                        <td> Game id</td>
                        <td> Game name</td>
                        <td> Game Visibility</td>
                        <td> Game Status</td>
                        <td> Actual owner</td>
                    </tr>
                    </thead>
                    <tbody>
                    {
                        games.map(game => {
                                return <tr key={game.id}>
                                    <td> {game.id}</td>
                                    <td>{game.name}</td>
                                    <td> {game.visibility}</td>
                                    <td> <select value={value} onChange={handleStatus}>
                                        <option value="AVAILABLE">AVAILABLE</option>
                                        <option value="LENT">LENT</option>
                                    </select>
                                  </td>
                                    <td> {game.actual}</td>
                                    <td>
                                        <button className="btn btn-outline-secondary"
                                                onClick={() => removeGame(game.id)}>Delete
                                        </button>
                                    </td>
                                </tr>
                            }
                        )
                    }
                    </tbody>
                </table>
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