import {UserAPI} from "../../api/UserAPI";
import React, {useEffect, useState} from "react";
import {InvitationAPI} from "../../api/InvitationAPI";
import classes from "./UserList.module.css";

function UserList() {

    const [users, setUsers] = useState([])

    useEffect(() => {
        UserAPI.getAll().then(
            function (response) {
                setUsers(response.data)
            }
        ).catch(function (error) {
            console.error(`Error: ${error}`)
        });
    }, []);

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

    return (
        <div>
            <div>
                <h1 className="text-center"> All users </h1>
                <table className="table table-striped">
                    <thead>
                    <tr>
                        <td> User id</td>
                        <td> User first name</td>
                        <td> User last name</td>
                        <td> User email</td>
                        <td> User city</td>

                    </tr>
                    </thead>
                    <tbody>
                    {
                        users.map(
                            (user) => {
                                const visibility = user.id === 2 ? classes.hidden : classes.visible
                                return (
                                    <tr key={user.id}>
                                        <td>{user.id}</td>
                                        <td>{user.firstName}</td>
                                        <td>{user.lastName}</td>
                                        <td>{user.email}</td>
                                        <td>{user.city}</td>
                                        <td>
                                            <button className={"btn btn-outline-secondary " + visibility}
                                                    onClick={(e) => addInvitation(2, user.id)}>Invite
                                            </button>
                                        </td>
                                    </tr>)
                            }
                        )
                    }
                    </tbody>
                </table>
            </div>
        </div>
    )
}

export default UserList;