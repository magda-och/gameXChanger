import React from 'react';

function Borrowed(props) {

    const displayBorrowedGames = (props) => {
        const {games} = props;

        return (
            <div>
                <h1 className="text-center">Borrowed Games</h1>
                <table className="table table-striped">
                    <thead>
                    <tr>
                        <td> Game id</td>
                        <td> Game name</td>
                        <td> Borrow from</td>
                    </tr>
                    </thead>
                    <tbody>
                    {
                        games.map(game => {
                                return <tr>
                                    <td> {game.id}</td>
                                    <td>{game.name}</td>
                                    <td> {game.from}</td>
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
            {displayBorrowedGames(props)}
        </>
    );
}

export default Borrowed;