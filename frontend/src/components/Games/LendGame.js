import React, {useState} from 'react';
import {useParams} from "react-router-dom";
import {UserAPI} from "../../api/UserAPI";
import {GameAPI} from "../../api/GameAPI";
import classes from "./Friends.moduleCopy.css";

function LendGame() {
    const{id} = useParams();
    const [state, setstate] = useState({
        query: '',
        list: []
    })

    let users;

    UserAPI.getAll().then(
        response => {
            const data = response.data;
            users = Object.values(data)
        });


    function borrowGame(id, userId) {
        const borrow =
            [id, userId];


        GameAPI.borrowGame(borrow[0], borrow[1])
            .then(() => {
                alert("Game Lent")
                window.location.replace("shelf")
            })
    }

    const handleChange = (e) => {
        const results = users.filter(user => {
            if (e.target.value === "") return users
            return user.firstName.toLowerCase().includes(e.target.value.toLowerCase())
                || user.lastName.toLowerCase().includes(e.target.value.toLowerCase())
        })
        setstate({
            query: e.target.value,
            list: results,
        })
    }

    return (
        <div className={classes.friends}>
            <div className="row d-flex justify-content-end">
                <h4> Find a friend you want to lend the game to </h4>
                <form id="form-id">
                    <input
                        size={50}
                        type="search"
                        value={state.query}
                        onChange={handleChange}
                        placeholder="Enter your friend's name"
                    />
                </form>
                <ul>
                    {(state.query === '' ? "No users match the query" : !state.list.length ? "Your query did not return any results" : state.list.map(user => {
                        return <li key={user.firstName}>
                            <td id="1">{user.firstName}&nbsp;</td>
                            <td id="1"> {user.lastName}&nbsp;&nbsp;</td>
                            <td id="1">
                                <button id="invitation-btn" className="btn btn-outline-secondary"
                                        onClick={() => borrowGame(id, user.id)}>Lend Game
                                </button>
                            </td>
                        </li>
                    }))}
                </ul>
            </div>
        </div>
    )

}

export default LendGame;