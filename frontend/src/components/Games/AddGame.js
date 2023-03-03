import {GameAPI} from "../../api/GameAPI";
import {useForm} from "react-hook-form";
import React, {useState} from "react";
import AuthenticationService from "../../services/AuthenticationService";
import {currentId} from "../Users/UserDetails";

export default function AddGame() {

    const {register, handleSubmit, formState: {errors}} =
        useForm({mode: "onBlur"});

    const [showForm, setShowForm] = useState(undefined);
    const [games, setGames] = useState([])
    const openForm = () => {
        setShowForm(true);
    }

    const closeForm = () =>{
        setShowForm(false)
    }

    const onSubmit = data => {
        const newGame = {
            name: data.name,
            description: "fajna gra",
            gameStatus: "AVAILABLE",
            visibility: "PRIVATE",
        }
        GameAPI.create(currentId, newGame)
            .then(() => {
                alert("Game successfully added to shelf!")
                GameAPI.getMyGames(currentId).then(
                    function (response) {
                        setGames(response.data)
                    }
                ).catch(function (error) {
                    console.error(`Error: ${error}`)
                });
            })
    };

    return (
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
    )
}