import axios from "axios";
import {useEffect, useState} from "react";
import Users from "../pages/users/Users";


function UserService() {
    const [users, setUsers] = useState({
        firstName: "",
        lastName: "",
        email: "",
        city: "",
    });

    const USER_REST_API_URL ='http://localhost:3100/user';

    useEffect(()=>{
        getAllUsers();
    }, []);

    const getAllUsers =() =>{
        axios.get(USER_REST_API_URL)
            .then((response) =>{
                const allUsers = response.data;
                setUsers(allUsers);
        })
            .catch(error => console.error('Error: ${error'));
    }

    return (
        <Users users = {users}/>
    )
}

export default UserService;