import React from 'react';

function Borrowed(props) {
    const displayBorrowedGamesContain = (props) => {
        const {games} = props;
        if(Object.keys(games).length>0){
            return(
                <div>
                    <div >
                        {
                            games.map(game => {
                                    return <div className="col-md-12 container" style={{width:"170px", float:"left",height:"170px",background:"#FFADBC",margin:"10px",borderRadius:"12px"}}>
                                        {/*<p>{game.name}</p>*/}
                                        <p> {game.gameStatus}</p>
                                        <p>{game.owner.name}</p>
                                    </div>
                                }
                            )
                        }
                    </div>
                </div>
            )
        }else{
            return (
                <div> </div>
            )
        }
    }

    const displayBorrowedGames = (props) => {
        const {games} = props;
        console.log(Object.keys(games).length)

        return (
            <div style={{resize:"both", overflow:"auto"}}>
                <h2 className="text-center">My Borrowed Games</h2>
                {displayBorrowedGamesContain(props)}
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