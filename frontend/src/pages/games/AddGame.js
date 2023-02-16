import {useState} from "react";
import axios from "axios";
import InvitationService from "../../services/InvitationService";
import GameService from "../../services/GameService";
function addGame(name){
    console.log(name)
    const game = {
        "name": name,
        "description": "fajna gra",
        "gameStatus": "AVAILABLE",
        "visibility":"PRIVATE"
    };

    axios.post('http://localhost:3100/games/2', game)
        .then(res =>{
           /* axios.get('http://localhost:3100/games')
                .then(res => {
                    this.setState({ res });
                });*/
        });
}
export default function AddGame() {

    const handleSubmit = (event) => {
        event.preventDefault()
        addGame(event.target.name.value)
        window.location.replace('/profile/games')
    }

    return (
        <div className="container">
            <div className="row">
                <div className="col-md-6 offset-md-3 border rounded p-4 mt-2 shadow">
                    <h2 className="text-center m-4"> Add game</h2>

                    <form className="add-game" id="add-game" onSubmit={handleSubmit}>
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
                            />
                        </div>
                        <button type="submit" className="btn btn-primary">
                            add
                        </button>
                    </form>

                </div>
            </div>
        </div>
    )
}