import {GameAPI} from "../../api/GameAPI";
import {useForm} from "react-hook-form";
import React, {useState} from "react";

export default function AddGame() {

    const {register, watch, handleSubmit, getValues, formState: {errors}} =
        useForm({mode: "onBlur"});

    const [showForm, setShowForm] = useState(undefined);
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
        GameAPI.create(2, newGame)
            .then(() => {
                alert("Game successfully added to shelf!")
                window.location.replace('/profile/shelf')
            })
    };

    return (
        <div className="text-center m-4" id="myForm">
            <div className="row">
                <div className="col-md-6 offset-md-3 border rounded p-4 mt-2 shadow">
                    <button type="button" className="btn btn-outline-secondary" onClick={openForm}> Add game</button>

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
                            <button type="submit" className="btn btn-outline-secondary">
                                Add
                            </button>
                            <button type="button" className="btn btn-outline-secondary" onClick={closeForm}>Close</button>
                        </form>
                    )}
                </div>
            </div>
        </div>
    )
}