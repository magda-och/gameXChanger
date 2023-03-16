import {UserAPI} from "../../api/UserAPI";
import React, {useEffect, useState} from "react";
import classes from "./UserList.module.css";

import AuthenticationService from "../../services/AuthenticationService";


function UserList() {

    const [users, setUsers] = useState([])
    const adminId = AuthenticationService.getLoggedInUserID()

    useEffect(() => {
        UserAPI.getAll().then(
            function (response) {
                setUsers(response.data)
            }
        ).catch(function (error) {
            console.error(`Error: ${error}`)
        });
    }, []);

    const deleteUser = async (id) => {
        try {
            const res = await UserAPI.delete(id)
            console.log('User successfully deleted.')
            alert("User successfully deleted.")
            UserAPI.getAll().then(
                function (response) {
                    setUsers(response.data)
                }
            ).catch(function (error) {
                console.error(`Error: ${error}`)
            });
            return res;
        } catch (error) {
            alert(error)
        }
    }

    return (
        <div>
            <div>
                <h1 className="text-center" style={{width: "40%", marginLeft: "35%", textAlign: "center"}}> All users </h1>
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
                                const visibility = user.id === adminId ? classes.hidden : classes.visible
                                return (
                                    <tr key={user.id}>
                                        <td>{user.id}</td>
                                        <td>{user.firstName}</td>
                                        <td>{user.lastName}</td>
                                        <td>{user.email}</td>
                                        <td>{user.city}</td>
                                        <td>
                                            <button className={"btn btn-outline-secondary " + visibility}
                                                    onClick={() => deleteUser(user.id)}>Delete
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