import React from 'react';
import axios from "axios";

function Friends(props) {


    const removeFriend = async (id) => {
        try {
            const res = await axios.delete(`${"http://localhost:3100/user/friends/2"}/${id}`)
            console.log('Item successfully deleted.')

            return res;
        } catch (error) {
            alert(error)
        }
    }

    const displayFriends = (props) => {
        const {friends} = props;

        if (friends.length > 0) {
            return (
                <div>
                    <div>
                        <h1 className="text-center"> All Friends </h1>
                        <table className="table table-striped">
                            <thead>
                            <tr>
                                <td> Friend id</td>
                                <td> Friend first name</td>
                                <td> Friend last name</td>
                                <td> Friend city</td>
                            </tr>
                            </thead>
                            <tbody>
                            {
                                friends.map(
                                    friend => {
                                        return <tr>
                                            <td>{friend.id}</td>
                                            <td>{friend.firstName}</td>
                                            <td>{friend.lastName}</td>
                                            <td>{friend.city}</td>
                                            <td><button className="btn btn-danger" onClick={() => removeFriend(friend.id)}>Delete</button></td>
                                            </tr>
                                    }
                                )
                            }
                            </tbody>
                        </table>
                    </div>
                </div>
            )
        }else {
            return (
                <h3>No friends yet</h3>
            );
        }
    }
    return (
        <>
            {displayFriends(props)}
        </>
    );

}

export default Friends;