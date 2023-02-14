import React from 'react';

function Users(props) {

    const displayUsers = (props) => {
        const {users} = props;

        if (users.length > 0) {
            return (

                <ul>
                    {
                        users.map(user =>
                            <li key={user.id}>
                                <p>{user.id}</p>
                                <p> {user.firstName}</p>
                                <p> {user.lastName}</p>
                                <p> {user.email}</p>
                                <p> {user.city}</p>
                                <p></p>
                            </li>
                        )
                    }
                </ul>
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