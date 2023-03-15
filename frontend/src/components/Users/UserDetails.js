import React, {useEffect, useState} from "react";
import {UserAPI} from "../../api/UserAPI";
import AuthenticationService from "../../services/AuthenticationService";

export let currentId

function UserDetailsPage() {

    const [user, setUser] = useState([])
    const [encryptedData, setEncryptedData] = useState("")
    const [decryptedData, setDecryptedData] = useState("")
    const [firstName, setFirstName] = useState();
    const {register, handleSubmit, formState: {errors}} =
        useForm({mode: "onBlur"});

    useEffect(() => {
        UserAPI.getByEmail(AuthenticationService.getLoggedInUserName()).then(
            function (response) {
                setUser(response.data)
            }
        ).catch(function (error) {
            console.error(`Error: ${error}`)
        });
    }, []);

    currentId = user.id
    /* sessionStorage.setItem("id", user.id);*/

    const showForm = () => {
        let formForFirstName = document.getElementById("firstName")
        let formForLastName = document.getElementById("lastName")
        let savingButton = document.getElementById("savingButton");
        if (formForFirstName.style.display === 'none') {
            formForFirstName.style.display = 'block'
            formForLastName.style.display = 'block'
            savingButton.style.display = 'block'
        } else {
            formForFirstName.style.display = 'none'
            formForLastName.style.display = 'none'
            savingButton.style.display = 'none'
        }
    }

    const onSubmit = async data => {
        let modifiedFirstName = firstUpper(data.firstName)
        // let modifiedLastName = firstUpper(data.lastName)
        // let modifiedCity = firstUpper(data.city)
        let newFirstName = user.firstName;
        let newLastName = user.lastName;
        if (data.firstName !== null && data.firstName !== "" && data.firstName !== undefined) {
            newFirstName = data.firstName;
        }
        if (data.lastName !== null && data.lastName !== "" && data.lastName !== undefined) {
            newLastName = data.lastName;
        }
        const newUser = {
            id: user.id,
            firstName: newFirstName,
            lastName: newLastName,
            email: user.email,
            city: user.city,
            phoneNumber: user.phoneNumber,

        }
        setFirstName(modifiedFirstName);
        await UserAPI.update(currentId, newUser)
            .then(() => {
                alert("First name changed")
            })
        showForm();
        // setUser(newUser);
        user.firstName = newFirstName;
        user.lastName = newLastName;
    };

    function firstUpper(name) {

        return name && name[0].toUpperCase() + name.slice(1) || name;

    }

    return (
        <div style={{marginLeft: "5%"}}>
            <h1 style={{height: "60px"}}>Hello {user.firstName}!</h1>
            <p><b>Your personal data: </b>
                <button id="changeButton" className="btn btn-primary" onClick={showForm}>CHANGE YOUR DATA</button>
            </p>
            <p><b>First name:</b> {user.firstName}&nbsp;&nbsp;&nbsp;</p>
            <form onSubmit={handleSubmit(onSubmit)}>
                <input
                    type="text"
                    className={`form-control ${errors.firstName ? 'errFirstName' : ''}`}
                    name="firstName"
                    id="firstName"
                    {...register("firstName", {
                        minLength: 3,
                        pattern: /^[a-zA-Z]+$/
                    })}
                />
                {errors.firstName?.type === 'minLength' &&
                    <p id='alert-msg' role="alert">First name should be at least 3 characters long</p>}
                {errors.firstName?.type === 'pattern' &&
                    <p id="alert-msg" role="alert">First name is not valid - should contains only letters </p>}
                <p><b>Last name:</b> {user.lastName}</p>
                <input
                    type="text"
                    className={`form-control ${errors.firstName ? 'errFirstName' : ''}`}
                    name="lastName"
                    id="lastName"
                    {...register("lastName", {
                        minLength: 3,
                        pattern: /^[a-zA-Z]+$/
                    })}
                />
                {errors.firstName?.type === 'minLength' &&
                    <p id='alert-msg' role="alert">First name should be at least 3 characters long</p>}
                {errors.firstName?.type === 'pattern' &&
                    <p id="alert-msg" role="alert">First name is not valid - should contains only letters </p>}
                <p><b>E-mail:</b> {user.email}</p>
                <p><b>City:</b> {user.city}</p>
                <p><b>Phone number:</b> {user.phoneNumber}</p>
                <p><b>Password:</b> {decryptedData}</p>
                <button className="btn btn-primary" type="submit" id="savingButton">SAVE</button>
            </form>


        </div>
    );
}

export default UserDetailsPage;