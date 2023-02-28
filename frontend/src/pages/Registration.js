import {useState} from "react";
import {useForm} from 'react-hook-form';
import React, {useRef} from "react";
import "./Registration.css";
import {UserAPI} from "../api/UserAPI";
import {Helmet} from "react-helmet";
import {NavLink} from "react-router-dom";

export default function Registration() {

    const [togglePassword, setTogglePassword] = useState(false);

    const {register, watch, handleSubmit,  getValues, formState: {errors}} =
        useForm({mode: "onBlur"});


    const onSubmit = data => {
        let modifiedFirstName = firstUpper(data.firstName)
        let modifiedLastName = firstUpper(data.lastName)
        let modifiedCity = firstUpper(data.city)
        // e.preventDefault();
        const newUser = {
            firstName: modifiedFirstName,
            lastName: modifiedLastName,
            email: data.email,
            password: data.password,
            city: modifiedCity,
            phoneNumber: data.phoneNumber,
        }
        UserAPI.create(newUser)
            .then(() => {
                alert("User created!")
                window.location.replace('/profile')
            })
    };

    function firstUpper(name){

        return name && name[0].toUpperCase() + name.slice(1) || name;

    }

    return (
        <div>
            <div>
                <Helmet>
                    <title> GameXChanger | Registration</title>
                </Helmet>
                <div>
                    <h1>GameXChanger</h1>
                </div>
            </div>
        <div className="container">
            <div className="row">
                <div className="col-md-6 offset-md-3 border rounded p-4 mt-2 shadow">
                    <h2 className="text-center m-4"> Join us! </h2>

                    <form onSubmit={handleSubmit(onSubmit)} className="register-form" id="register-form">
                        <div className="mb-3">
                            <label htmlFor="firstName" className="form-label">
                                First name
                            </label>
                            <input
                                type="text"
                                className={`form-control ${errors.firstName ? 'errFirstName' : ''}`}
                                placeholder="Enter your first name"
                                name="firstName"
                                id="firstname"
                                {...register("firstName", {
                                    required: true,
                                    minLength: 3,
                                    pattern: /^[a-zA-Z]+$/
                                })}
                                // aria-invalid={errors.firstName ? "true" : "false"}
                            />
                            {errors.firstName?.type === 'required' && <p id='alert-msg' role="alert">First name is required</p>}
                            {errors.firstName?.type === 'minLength' && <p id='alert-msg' role="alert">First name should be at least 3 characters long</p>}
                            {errors.firstName?.type === 'pattern' && <p id="alert-msg" role="alert">First name is not valid - should contains only letters </p>}
                        </div>
                        <div className="mb-3">
                            <label htmlFor="lastName" className="form-label">
                                Last name
                            </label>
                            <input
                                type="text"
                                className={`form-control ${errors.lastName ? 'errLastName' : ''}`}
                                placeholder="Enter your last name"
                                name="lastName"
                                id="lastname"
                                {...register("lastName", {
                                    required: true,
                                    minLength: 3,
                                    // pattern: /^[A-Z][a-z]+$/
                                    pattern : /^[a-zA-Z]+$/
                                })}
                            />
                            {errors.lastName?.type === 'required' && <p id='alert-msg' role="alert">Last name is required</p>}
                            {errors.lastName?.type === 'minLength' && <p id='alert-msg' role="alert">Last name should be at least 3 characters long</p>}
                            {errors.lastName?.type === 'pattern' && <p id="alert-msg" role="alert">Last name is not valid - should contains only letters </p>}
                        </div>
                        <div className="mb-3">
                            <label htmlFor="email" className="form-label">
                                Email address
                            </label>
                            <input
                                type="email"
                                className={`form-control ${errors.email ? 'errEmail' : ''}`}
                                placeholder="Enter your email address"
                                name="email"
                                id="email"
                                {...register("email", {
                                    required: true,
                                    pattern: /^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$/
                                })}
                            />
                            {errors.email?.type === 'required' && <p id='alert-msg' role="alert">Email is required</p>}
                            {errors.email?.type === 'pattern' && <p id='alert-msg' role="alert">Email is not valid</p>}
                        </div>
                        <div className="mb-3">
                            <label htmlFor="password" className="form-label">
                                Password
                            </label>
                            <div className="password-box">
                                <i
                                    className={`${togglePassword ? "fa fa-eye icon" : "fa fa-eye icon grayscaled"}`}
                                    style={{"position": "absolute", "right": "7px", "top": "10px"}}
                                    onClick={() => {
                                        setTogglePassword(!togglePassword);
                                    }}
                                ></i>
                                <input
                                    type={togglePassword ? "text" : "password"}
                                    className={`form-control ${errors.password ? 'errPassword' : ''}`}
                                    placeholder="Enter your password"
                                    name="password"
                                    id="password"
                                    {...register("password", {
                                        required: true,
                                        minLength: 6,
                                        pattern: /(?=[A-Za-z0-9@#$%^&+!=]+$)^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[@#$%^&+!=])(?=.{8,}).*$/
                                    })}
                                />
                                {errors.password?.type === 'required' && <p id='alert-msg' role="alert">Password is required</p>}
                                {errors.password?.type === 'minLength' &&
                                    <p id='alert-msg' role="alert">Password should have min 6 characters</p>}
                                {errors.password?.type === 'pattern' &&
                                    <p id='alert-msg' role="alert">Password should have minimum one small letter, one capital, one
                                        number
                                        and one special sign</p>}
                            </div>
                        </div>
                        <div className="mb-3">
                            <label htmlFor="city" className="form-label">
                                City (optional)
                            </label>
                            <input
                                type={"text"}
                                className={`form-control ${errors.city ? 'errCity' : ''}`}
                                placeholder="Enter your city"
                                name="city"
                                id="city"
                                {...register("city", {
                                    minLength: 3,
                                    pattern: /^[a-zA-Z]+$/    //todo? nie akceptuje polskich znaków
                                })}
                            />
                            {errors.city?.type === 'minLength' && <p id='alert-msg' role="alert">City should be at least 3 characters long</p>}
                            {errors.city?.type === 'pattern' && <p id="alert-msg" role="alert">City is not valid - should contains only letters </p>}
                        </div>
                        <div className="mb-3">
                            <label htmlFor="phoneNumber" className="form-label">
                                Phone number (optional)
                            </label>
                            <input
                                type={"text"}
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
                        </div>
                        <button type="submit" className="btn btn-primary">
                            Submit
                        </button>
                        <div className="mt-3">
                            <p className="mb-0  text-center">
                                Already have an account?{' '}
                                <NavLink to="/user/login" className="text-primary fw-bold">
                                    Log In
                                </NavLink>
                            </p>
                        </div>
                    </form>

                </div>
            </div>
        </div>
        </div>
    )
}