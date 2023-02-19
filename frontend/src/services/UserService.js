import {UserAPI} from "../api/UserAPI";
import React, {useEffect, useState} from "react";
import Users from "../components/Users/Users";
import {InvitationAPI} from "../api/InvitationAPI";

function UserService() {

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


    return (
        <div>
            <div>
                <h1 className ="text-center"> All users </h1>
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
                            user => {
                                return <tr>
                                    <td>{user.id}</td>
                                    <td>{user.firstName}</td>
                                    <td>{user.lastName}</td>
                                    <td>{user.email}</td>
                                    <td>{user.city}</td>
                                    <td><button className="btn btn-success" onClick={(e) => Users.addInvitation(2, user.id)}>Invite</button></td>
                                </tr>
                            }
                        )
                    }
                    </tbody>
                </table>
            </div>
        </div>
    )
}

export default UserService;