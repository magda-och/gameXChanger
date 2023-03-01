import React, { useState } from 'react';
import { GameAPI } from "../../api/GameAPI";

function Games(props) {
    const [selectedOption, setSelectedOption] = useState('');
    const [selectedStatus, setSelectedStatus] = useState('');
     // const [selectedVisibility, setSelectedVisibility] = useState('');
    const lendGame = async (id, option) => {
        try {
            const res = await GameAPI.update(id, option)//, visibility)
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
        const { games } = props;



        const handleOptionChange = (event) => setSelectedOption(event.target.value);
        const handleStatusChange = (event, gameId) => {
            const game = games.find(game => game.id === gameId);
            if (game) {
                game.status = event.target.value;
            }
            setSelectedStatus(event.target.value);
        };
        // const handleVisibilityChange = (event, gameId) => {
        //     const game = games.find(game => game.id === gameId);
        //     if (game) {
        //         game.visibility = event.target.value;
        //     }
        //     setSelectedVisibility(event.target.value);
        // };

        return (
            <div>
                <h2 className="text-center">Games</h2>
                <table className="table table-striped">
                    <thead>
                    <tr>
                        {/*<td>Game id</td>*/}
                        <td>Game name</td>
                        {/*<td>Game Visibility</td>*/}
                        <td>Game Status</td>
                        {games.some(game => game.status === 'lent') && <td>Actual owner</td>}
                        {games.some(game => game.status === 'available') && <td>Lend to:</td>}
                        <td></td>
                        <td></td>
                    </tr>
                    </thead>
                    <tbody>
                    {games.map(game => {
                        return (
                            <tr key={game.id}>
                           {/*<td>{game.id}</td>*/}
                                <td>{game.name}</td>
                                {/*<td>*/}
                                {/*    <select value={game.visibility || selectedVisibility} onChange={(event) => handleVisibilityChange(event, game.id)}>*/}
                                {/*        <option value="">Select Visibility</option>*/}
                                {/*        <option value="PRIVATE">PRIVATE</option>*/}
                                {/*        <option value="PUBLIC">PUBLIC</option>*/}
                                {/*        <option value="FRIENDS">FRIENDS</option>*/}
                                {/*    </select>*/}
                                {/*</td>*/}
                                <td>
                                    <select value={game.status || selectedStatus} onChange={(event) => handleStatusChange(event, game.id)}>
                                        <option value="">{game.status}</option>
                                        <option value="AVAILABLE">AVAILABLE</option>
                                        <option value="LENT">LENT</option>
                                    </select>
                                </td>
                                {game.status === 'LENT' && <td>{game.actual}</td>}
                                {game.status === 'AVAILABLE' && (
                                    <td>
                                        <input
                                            type="text"
                                            value={game.option || selectedOption}
                                            onChange={handleOptionChange}
                                        />
                                    </td>
                                )}
                                <td>
                                    {game.status === 'AVAILABLE' && (
                                        <button
                                            className="btn btn-outline-secondary"
                                            onClick={() => lendGame(game.id, selectedStatus, selectedOption)}
                                        >
                                            Lend
                                        </button>
                                    )}
                                </td>
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