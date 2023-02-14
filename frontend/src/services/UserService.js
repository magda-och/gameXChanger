import axios from "axios";
import {useEffect, useState} from "react";
import Profile from "../pages/Profile";



function UserService() {
    const [users, getUsers] = useState('');

    const USER_REST_API_URL ='localhost:3100/user';

    useEffect(()=>{
        getAllUsers();
    }, []);

    const getAllUsers =() =>{
        axios.get(USER_REST_API_URL)
            .then((response) =>{
                const allUsers = response.data.users.allUsers;
                getUsers(allUsers);
        })
            .catch(error => console.error('Error: ${error'));
    }

    return (
        <Profile users = {users}/>
    )
}

export default new UserService;