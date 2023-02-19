import {GameAPI} from "../../api/GameAPI";
import {useForm} from "react-hook-form";
import React from "react";

/*function addGame(name){
    console.log(name)
    const game = {
        "name": name,
        "description": "fajna gra",
        "gameStatus": "AVAILABLE",
        "visibility":"PRIVATE"
    };
    GameAPI.create(game);
}*/
export default function AddGame(showForm) {

    const {register, watch, handleSubmit,  getValues, formState: {errors}} =
        useForm({mode: "onBlur"});

    const onSubmit = data => {
        // e.preventDefault();
        const newGame = {
            name: data.name,
            description: "fajna gra",
            gameStatus: "AVAILABLE",
            visibility: "PRIVATE",
        }
        GameAPI.create(2, newGame)
            .then(() => {
                alert("Game created!")
                window.location.replace('/profile/shelf')
            })
    };

    return (
        <div className={"form-popup"} id="myForm">
            <div className="row">
                <div className="col-md-6 offset-md-3 border rounded p-4 mt-2 shadow">

                    <form className="add-game" id="add-game" onSubmit={handleSubmit(onSubmit)}>
                        <div className="mb-3">
                            <label htmlFor="name" className="form-label">
                               game name
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
                        <button type="submit" className="btn btn-primary">
                            add
                        </button>
                        <button  type="button" className="btn cancel" onClick="closeForm()">Close</button>
                    </form>

                </div>
            </div>
        </div>
    )
}