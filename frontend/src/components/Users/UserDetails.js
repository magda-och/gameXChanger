import React, {useEffect, useState} from "react";
import {UserAPI} from "../../api/UserAPI";
import AuthenticationService from "../../services/AuthenticationService";
import {useForm} from "react-hook-form";
import "./UserDetails.css"

export let currentId

function UserDetailsPage() {

    const [user, setUser] = useState([])
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
        let formForCity = document.getElementById("city");
        let formForPhoneNumber = document.getElementById("phoneNumber");
        let savingButton = document.getElementById("savingButton");
        if (formForFirstName.style.display === 'none') {
            formForFirstName.style.display = 'block'
            formForLastName.style.display = 'block'
            formForCity.style.display = 'block'
            formForPhoneNumber.style.display = 'block'
            savingButton.style.display = 'block'
        } else {
            formForFirstName.style.display = 'none'
            formForLastName.style.display = 'none'
            formForCity.style.display = 'none'
            formForPhoneNumber.style.display = 'none'
            savingButton.style.display = 'none'
        }
    }

    const onSubmit = async data => {
        let newFirstName = user.firstName;
        let newLastName = user.lastName;
        let newCity = user.city;
        let newPhoneNumber = user.phoneNumber;
        if (data.firstName !== null && data.firstName !== "" && data.firstName !== undefined) {
            newFirstName = firstUpper(data.firstName);
        }
        if (data.lastName !== null && data.lastName !== "" && data.lastName !== undefined) {
            newLastName = firstUpper(data.lastName);
        }

        if (data.city !== null && data.city !== "" && data.city !== undefined) {
            newCity = firstUpper(data.city);
        }

        if (data.phoneNumber !== null && data.phoneNumber !== "" && data.phoneNumber !== undefined) {
            newPhoneNumber = data.phoneNumber;
        }

        const newUser = {
            id: user.id,
            firstName: newFirstName,
            lastName: newLastName,
            email: user.email,
            city: newCity,
            phoneNumber: newPhoneNumber,

        }
        UserAPI.update(currentId, newUser)
            .then(() => {
                alert("First name changed")
            })
        showForm();
        // setUser(newUser);
        user.firstName = newFirstName;
        user.lastName = newLastName;
        user.city = newCity;
        user.phoneNumber = newPhoneNumber;
    };

    function firstUpper(name) {

        return name && name[0].toUpperCase() + name.slice(1) || name;

    }

    return (
        <div tyle={{marginLeft: "5%", background:"#cfcbf1", borderRadius:"12px", width:"80%"}}>
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
                    placeholder="Enter your first name"
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
                    placeholder="Enter your last name"
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
                <input
                    type="text"
                    className={`form-control ${errors.city ? 'errCity' : ''}`}
                    name="city"
                    id="city"
                    placeholder="Enter your city"
                    {...register("city", {
                        minLength: 3,
                        pattern: /^[a-zA-Z]+$/
                    })}
                />
                {errors.city?.type === 'minLength' &&
                    <p id='alert-msg' role="alert">City should be at least 3 characters long</p>}
                {errors.city?.type === 'pattern' &&
                    <p id="alert-msg" role="alert">City is not valid - should contains only letters </p>}
                <p><b>Phone number:</b> {user.phoneNumber}</p>
                <input
                    type={"number"}
                    className={`form-control ${errors.phoneNumber ? 'errPhoneNumber' : ''}`}
                    placeholder="Enter your phone number"
                    name="phoneNumber"
                    id="phoneNumber"
                    {...register("phoneNumber", {
                        minLength: 9,
                        pattern: /^\d+$/
                    })}
                />
                {errors.phoneNumber?.type === 'minLength' && <p id='alert-msg' role="alert">Phone number should be at least 9 characters long</p>}
                {errors.phoneNumber?.type === 'pattern' && <p id="alert-msg" role="alert">Phone number is not valid - should contains only digits </p>}
                <button className="btn btn-primary" type="submit" id="savingButton">SAVE</button>
            </form>


        </div>
    );
}

export default UserDetailsPage;