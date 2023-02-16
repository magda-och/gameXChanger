import React from 'react';

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