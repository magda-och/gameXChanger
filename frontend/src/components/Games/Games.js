import React, {useEffect, useState} from 'react';
import {GameAPI} from "../../api/GameAPI";
import {currentId} from "../Users/UserDetails";
import {useForm} from "react-hook-form";
import {UserAPI} from "../../api/UserAPI";
import AuthenticationService from "../../services/AuthenticationService";


function Games() {
    const [shelf, setGames] = useState([])
    const [borrowedGames, setBorrowedGames] = useState([])
    const[user, setUser]=useState(null)
    const {register, handleSubmit, formState: {errors}} =
        useForm({mode: "onBlur"});

    const [showForm, setShowForm] = useState(undefined);

    const userId = AuthenticationService.getLoggedInUserID()

    useEffect(() =>{
        GameAPI.getMyGames(userId).then(
            function (response) {
                setGames(response.data)
            }
        ).catch(function (error) {
            console.error(`Error: ${error}`)
        });
    }, []);
    useEffect(() =>{
        GameAPI.getBorrowedGames(userId).then(
            function (response) {
                setBorrowedGames(response.data)
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
        return  UserAPI.getById(userId).then(
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
        GameAPI.create(userId, newGame)
            .then(() => {
                alert("Game successfully added to shelf!")
                //var lastId = "#" + currentId;
                //window.location.replace("/profile/shelf" + lastId);
                //window.location.reload();

                GameAPI.getMyGames(userId).then(
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

            GameAPI.getMyGames(userId).then(
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
            <button className="btn btn-primary" style={{background:"#443C68", border:"none", marginRight:5}} onClick={(e) => updateGameStatus(game,"LENT", e)}>LENT</button>
            <button className="btn btn-primary" style={{background:"#443C68", border:"none"}} onClick={(e) => updateGameStatus(game,"AVAILABLE", e)}>REJECT</button>
                </div>
            )
        }else if(status==="RETURNING"){
            return (
                <button className="btn btn-primary" style={{background:"#443C68", border:"none"}} onClick={(e) => updateGameStatus(game,"AVAILABLE", e)}>RETURNED</button>
            )
        }
    }
    function updateGameStatus(game, status, e){
        console.log("cos");
        if(status==="AVAILABLE") {
            GameAPI.update(game.id, status, userId)
                .then(res => {
                    console.log("cos2")
                    console.log(res);
                    if (status === "LENT") {
                        alert("Game is return!")
                    } else {
                       /* alert("You dont lent game")*/
                    }
                    GameAPI.getMyGames(userId).then(
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
                    GameAPI.getMyGames(userId).then(
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
                <button className="btn btn-danger" style={{background:"#443C68", border:"none",margin:"10%"}}
                        onClick={() => removeGame(id)}><span
                    className="bi bi-trash"></span>
                </button>
            )
        }
    }
    function printUserName(game,gameStatus){
        if(gameStatus==="RESERVATION" || gameStatus==="LENT" || gameStatus==="RETURNING")
            return (
                <p>by {game.actualUserDto.firstName+ " "+ game.actualUserDto.lastName}</p>
            )
    }
    function printOwnerName(game,gameStatus){
        if(gameStatus==="RETURNING") {
            return (<p>to {game.ownerDto.firstName + " " + game.ownerDto.lastName}</p>)
        }else{
            return (<p>from {game.ownerDto.firstName + " " + game.ownerDto.lastName}</p>)
        }
    }
    function printButtonToCancelOrReturnBorrowedGames(game_,status){
        var game = game_
        if(status==="RESERVATION"){
            return (
                <button className="btn btn-primary" style={{background:"#443C68",margin: "6%", border:"none"}} onClick={(e) => updateBorrowedGameStatus(game,"AVAILABLE", e)}>CANCEL</button>
            )
        }else if(status==="LENT" && game.actualUserDto.id===userId){
            return (
                <button className="btn btn-primary" style={{background:"#443C68", margin: "6%", border:"none"}} onClick={(e) => updateBorrowedGameStatus(game,"RETURNING", e)}>RETURN</button>
            )
        }
    }

    function updateBorrowedGameStatus(game, status, e){
        console.log("cos");
        if(status==="RESERVATION") {
            GameAPI.update(game.id, status, userId)
                .then(res => {
                    console.log("cos2")
                    console.log(res);
                    if (status === "LENT") {
                        alert("Game is return!")
                    } else {
                        /* alert("You dont lent game")*/
                    }
                    GameAPI.getBorrowedGames(user.id).then(
                        function (response) {
                            setBorrowedGames(response.data)
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
                    GameAPI.getBorrowedGames(user.id).then(
                        function (response) {
                            setBorrowedGames(response.data)
                        }
                    ).catch(function (error) {
                        console.error(`Error: ${error}`)
                    });
                });
        }else if(status==="RETURNING"){
            GameAPI.update(game.id, status, userId)
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
                            setBorrowedGames(response.data)
                        }
                    ).catch(function (error) {
                        console.error(`Error: ${error}`)
                    });
                });
        }
    }

    const displayGames = () => {

        return (
            <div>
                <div className="text-center m-4" id="myForm">
                    <div className="row" >
                        <div style={{background:"#F0EEED"}} className="col-md-6 offset-md-3 border rounded p-4 mt-2 shadow">
                            <button style={{background:"#443C68", border:"none", color:"white"}} type="button" className="btn btn-outline-secondary" onClick={openForm}> Add game</button>

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
                                    <button style={{background:"#443C68", border:"none"}} type="submit" className="btn btn-secondary">
                                        Add
                                    </button>
                                    <span> </span>
                                    <button style={{background:"#443C68", border:"none", color:"white"}} type="button" className="btn btn-outline-secondary" onClick={closeForm}>Close</button>
                                </form>
                            )}
                        </div>
                    </div>
                </div>
                <div style={{overflow:"auto"}}>
                    <h2 className="text-center">My Games</h2>
                    <div className="shelf">
                            <div style={{marginLeft: "5%"}}>
                                {
                                    shelf.map(game => {

                                            return <div className="col-md-12 container" style={{width:"170px", float:"left",height:"170px",background:"#cfcbf1",margin:"10px",borderRadius:"12px"}}>
                                                <p style={{margin:0, fontFamily: "Arial"}}>{game.name}</p>
                                                <p style={{margin:0, fontFamily: "Arial"}}> {game.gameStatus}</p>
                                                {printUserName(game,game.gameStatus)}
                                                {printButtonToLent(game,game.gameStatus)}
                                                {printDeleteButton(game.id, game.gameStatus)}
                                            </div>
                                        }
                                    )
                                }
                            </div>
                </div>
                </div>
                <div style={{overflow:"auto"}}>
                    <h2 className="text-center">My Borrowed Games</h2>
                    <div className="shelf">
                        <div style={{marginLeft: "5%"}}>
                            {
                                borrowedGames.map(game => {

                                        return <div className="col-md-12 container" style={{width:"170px", float:"left",height:"170px",background:"#cfcbf1",margin:"10px",borderRadius:"12px"}}>
                                            <p style={{margin:0}}>{game.name}</p>
                                            <p style={{margin:0}}> {game.gameStatus}</p>
                                            {printOwnerName(game,game.gameStatus)}
                                          {/* { printButtonToCancelOrReturnBorrowedGames(game,game.gameStatus)}*/}
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
