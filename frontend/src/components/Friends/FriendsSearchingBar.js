import React, {useState} from "react";
import {UserAPI} from "../../api/UserAPI";
import {InvitationAPI} from "../../api/InvitationAPI";
import "./Friends.module.css"
import classes from "./Friends.module.css";
import AuthenticationService from "../../services/AuthenticationService";

export default function FriendsSearchingBar() {
    const [state, setstate] = useState({
        query: '',
        list: []
    })

    let users;
    const userId = AuthenticationService.getLoggedInUserID();
    UserAPI.getNotFriends(userId).then(
        response => {
            const data = response.data;
            users = Object.values(data)
        });


    function addInvitation(fromId, toId) {
        const invitation = {
            "requestStatus": "WAITING",
            "fromUserId": {
                "id": fromId
            },
            "toUserId": {
                "id": toId
            },
            "message": "Zapraszam do znajomych"
        };

        InvitationAPI.create(invitation)
            .then(() => {
                alert("Invitation successfully send!")
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
            <div className="col-md-12 container">
                <h3> Find new friends </h3>
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
                                        onClick={() => addInvitation(userId, user.id)}>Send invitation
                                </button>
                            </td>
                        </li>
                    }))}
                </ul>
            </div>
        </div>
    )
}
