import React, {useEffect, useState} from 'react';
import {GameAPI} from "../../api/GameAPI";
import {currentId} from "../Users/UserDetails";
import {useNavigate} from "react-router-dom";
import {useForm} from "react-hook-form";
import {UserAPI} from "../../api/UserAPI";


function Games(props) {
    const [shelf, setGames] = useState([])
    const[user, setUser]=useState(null)
    const navigate = useNavigate();
    const {register, handleSubmit, formState: {errors}} =
        useForm({mode: "onBlur"});

    const [showForm, setShowForm] = useState(undefined);

    useEffect(() =>{
        GameAPI.getMyGames(currentId).then(
            function (response) {
                setGames(response.data)
                console.log(response.data)
            }
        ).catch(function (error) {
            console.error(`Error: ${error}`)
        });
    }, []);
    const openForm = () => {
        setShowForm(true);
    }

    const closeForm = () =>{
        setShowForm(false)
    }
    function getUser(){
        return  UserAPI.getById(currentId).then(
            (response)=>{
                setUser(Object.values(response.data))
            }
        ).catch(function (error){
            console.error(`Error: ${error}`)
        });
    }


    const onSubmit = data => {
        const newGame = {
            name: data.name,
            description: "fajna gra",
            gameStatus: "AVAILABLE",
            visibility: "PRIVATE",
            ownerDto: getUser(),
            actualUserDto:getUser()
        }
        GameAPI.create(currentId, newGame)
            .then(() => {
                alert("Game successfully added to shelf!")
                //var lastId = "#" + currentId;
                //window.location.replace("/profile/shelf" + lastId);
                //window.location.reload();

                GameAPI.getMyGames(currentId).then(
                    function (response) {
                        setGames(response.data)
                    }
                ).catch(function (error) {
                    console.error(`Error: ${error}`)
                });
            })
    };

    const removeGame = async(id) => {
        try {
            const res = await GameAPI.delete(id)
            console.log('Item successfully deleted.')
            alert("Game successfully deleted.")

            GameAPI.getMyGames(currentId).then(
                function (response) {
                    setGames(response.data)
                }
            ).catch(function (error) {
                console.error(`Error: ${error}`)
            });
            return res;
        } catch (error) {
            alert(error)
        }
    }
    function printButtonToLent(game_,status){
        var game = game_
        if(status==="RESERVATION"){
            return (
                <div>
            <button className="btn btn-primary" style={{background:"rgb(134, 58, 111)", border:"none", marginRight:5}} onClick={(e) => updateGameStatus(game,"LENT", e)}>LENT</button>
            <button className="btn btn-primary" style={{background:"rgb(134, 58, 111)", border:"none"}} onClick={(e) => updateGameStatus(game,"AVAILABLE", e)}>REJECT</button>
                </div>
            )
        }else if(status==="RETURNING"){
            return (
                <button className="btn btn-primary" style={{background:"rgb(134, 58, 111)", border:"none"}} onClick={(e) => updateGameStatus(game,"AVAILABLE", e)}>RETURNED</button>
            )
        }
    }
    function updateGameStatus(game, status, e){
        console.log("cos");
        if(status==="AVAILABLE") {
            GameAPI.update(game.id, status, currentId)
                .then(res => {
                    console.log("cos2")
                    console.log(res);
                    if (status === "LENT") {
                        alert("Game is return!")
                    } else {
                       /* alert("You dont lent game")*/
                    }
                    GameAPI.getMyGames(currentId).then(
                        function (response) {
                            setGames(response.data)
                        }
                    ).catch(function (error) {
                        console.error(`Error: ${error}`)
                    });
                });
        }else if("LENT"){
            GameAPI.update(game.id, status, game.actualUserDto.id)
                .then(res => {
                    console.log("cos3")
                    console.log(res);
                    if (status === "LENT") {
                        alert("Game is lent!")
                    } else {
                        /*alert("You dont return game")*/
                    }
                    GameAPI.getMyGames(currentId).then(
                        function (response) {
                            setGames(response.data)
                        }
                    ).catch(function (error) {
                        console.error(`Error: ${error}`)
                    });
                });
        }
    }
    function printDeleteButton(id, status){
        if(status==="AVAILABLE"){
            return(
                <button className="btn btn-danger" style={{background:"rgb(151, 92, 141)", border:"none"}}
                        onClick={() => removeGame(id)}><span
                    className="bi bi-trash"></span>
                </button>
            )
        }
    }
    function printUserName(game,gameStatus){
        if(gameStatus==="RESERVATION" || gameStatus==="LENT")
            return (
                <p>by {game.actualUserDto.firstName+ " "+ game.actualUserDto.lastName}</p>
            )
    }
/*    const giveBackGame = async (id, userId) => {
        try {
            const res = await GameAPI.giveBack(id, userId)//, visibility)
            console.log('Item successfully updated.')
            alert("Game successfully updated.")
            window.location.replace('/profile/shelf')
            return res;
        } catch (error) {
            alert(error)
        }
    }*/
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


    const displayGames = () => {

        return (
            <div>
                <div className="text-center m-4" id="myForm">
                    <div className="row" >
                        <div style={{background:"rgb(255, 173, 188)"}} className="col-md-6 offset-md-3 border rounded p-4 mt-2 shadow">
                            <button style={{background:"rgb(134, 58, 111)", border:"none", color:"white"}} type="button" className="btn btn-outline-secondary" onClick={openForm}> Add game</button>

                            {showForm && (
                                <form className="add-game" id="add-game" onSubmit={handleSubmit(onSubmit)}>
                                    <div className="mb-3">
                                        <label htmlFor="name" className="form-label">

                                        </label>
                                        <input
                                            type="text"
                                            className="form-control"
                                            placeholder="Enter name of game you want to add"
                                            name="name"
                                            id="name"
                                            {...register("name", {required: true})}
                                            aria-invalid={errors.name ? "true" : "false"}
                                        />
                                        {errors.name?.type === 'required' && <p role="alert">Game name is required</p>}
                                    </div>
                                    <button style={{background:"rgb(134, 58, 111)", border:"none"}} type="submit" className="btn btn-secondary">
                                        Add
                                    </button>
                                    <span> </span>
                                    <button style={{background:"rgb(151, 92, 141)", border:"none", color:"white"}} type="button" className="btn btn-outline-secondary" onClick={closeForm}>Close</button>
                                </form>
                            )}
                        </div>
                    </div>
                </div>
                <div style={{resize:"both", overflow:"auto"}}>
                    <h2 className="text-center">My Games</h2>
                    <div className="shelf">
                            <div>
                                {
                                    shelf.map(game => {
                                            return <div className="col-md-12 container" style={{width:"170px", float:"left",height:"170px",background:"#FFADBC",margin:"10px",borderRadius:"12px"}}>
                                                <p style={{margin:0}}>{game.name}</p>
                                                <p style={{margin:0}}> {game.gameStatus}</p>
                                                {printUserName(game,game.gameStatus)}
                                                {printButtonToLent(game,game.gameStatus)}
                                                {/*{printButtonToLent(game)}*/}

                                                {printDeleteButton(game.id, game.gameStatus)}
                                            </div>
                                        }
                                    )
                                }
                            </div>
                </div>
                </div>
            </div>
        )

    }
    return (
        <>
            {displayGames()}
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