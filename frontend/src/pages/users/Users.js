import React from 'react';
import axios from "axios";

function addInvitation(fromId, toId){
    const invitation = {
        "requestStatus": "WAITING",
        "fromUserId":{
            "id":fromId
        },
        "toUserId":{
            "id":toId
        },
        "message": "Zapraszam do znajomych"
    };
    axios.post('http://localhost:3100/friends/requests', invitation)
        .then(/*response => this.setState({ articleId: response.data.id })*/);
}

function Users(props) {


    const displayUsers = (props) => {
        const {users} = props;

        if (users.length > 0) {
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
                                            <td><button className="btn btn-success" onClick={(e) => addInvitation(2, user.id)}>Invite</button></td>
                                        </tr>
                                    }
                                )
                            }
                            </tbody>
                        </table>
                    </div>
                </div>
            )
        } else {
            return (
                <h3>No users yet</h3>
            );
        }
    }
    return (
        <>
            {displayUsers(props)}
        </>
    );

}

export default Users;