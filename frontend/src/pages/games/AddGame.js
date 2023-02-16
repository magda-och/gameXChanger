import {useState} from "react";
import axios from "axios";

export default function AddGame() {

    const [game, setGame] = useState({
        gameName: "",
    });


    const {gameName} = game;

    const onSubmit = (e) => {
        e.preventDefault();
        axios.post("http://localhost:3100/addgame", game)
            .then((response) => {
            })
    }


    let name, value;

    const handleInputs = (e) => {
        console.log(e);
        name = e.target.name;
        value = e.target.value;

        setGame({...game, [name]: value});

    }

    return (
        <div className="container">
            <div className="row">
                <div className="col-md-6 offset-md-3 border rounded p-4 mt-2 shadow">
                    <h2 className="text-center m-4"> Add game</h2>

                    <form className="add-game" id="add-game">
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
                                value={game.name}
                                onChange={handleInputs}
                            />
                        </div>
                        <button type="submit" onClick={onSubmit} className="btn btn-primary">
                            add
                        </button>
                    </form>

                </div>
            </div>
        </div>
    )
}