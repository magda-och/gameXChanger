import React from 'react';

function Friends(props) {

    const displayFriends = (props) => {
        const {friends} = props;

        if (friends.length > 0) {
            return (

                <ul>
                    {
                        friends.map(friend =>
                            <li key={friend.id}>
                                <p>{friend.firstName}</p>
                                <p>{friend.lastName}</p>
                            </li>
                        )
                    }
                </ul>
            )
        } else {
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