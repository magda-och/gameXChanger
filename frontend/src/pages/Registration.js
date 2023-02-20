import {useState} from "react";
import {useForm} from 'react-hook-form';
import React, {useRef} from "react";
import "./Registration.css";
import {UserAPI} from "../api/UserAPI";

export default function Registration() {

    const [user, setUser] = useState({
        firstName: "",
        lastName: "",
        email: "",
        password: "",
        city: "",
    });

    const [togglePassword, setTogglePassword] = useState(false);

    const {firstName, lastName, email, password, city} = user;

    const [firstNameErr, setFirstNameErr] = useState("");


    const {register, watch, handleSubmit,  getValues, formState: {errors}} =
        useForm({mode: "onBlur"});


    const onSubmit = data => {
        // e.preventDefault();
        const newUser = {
            firstName: data.firstName,
            lastName: data.lastName,
            email: data.email,
            password: data.password,
            city: data.city
        }
        UserAPI.create(newUser)
            .then(() => {
                alert("User created!")
                window.location.replace('/profile')
            })
    };

    return (
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
                                className="form-control"
                                placeholder="Enter your first name"
                                name="firstName"
                                id="firstname"
                                {...register("firstName", {required: true})}
                                aria-invalid={errors.firstName ? "true" : "false"}
                            />
                            {errors.firstName?.type === 'required' && <p role="alert">First name is required</p>}
                        </div>
                        <div className="mb-3">
                            <label htmlFor="lastName" className="form-label">
                                Last name
                            </label>
                            <input
                                type="text"
                                className="form-control"
                                placeholder="Enter your last name"
                                name="lastName"
                                id="lastname"
                                {...register("lastName", {required: true})}
                            />
                            {errors.lastName?.type === 'required' && <p role="alert">Last name is required</p>}
                        </div>
                        <div className="mb-3">
                            <label htmlFor="email" className="form-label">
                                Email address
                            </label>
                            <input
                                type="email"
                                className="form-control"
                                placeholder="Enter your email address"
                                name="email"
                                id="email"
                                {...register("email", {
                                    required: true,
                                    pattern: /^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$/
                                })}
                            />
                            {errors.email?.type === 'required' && <p role="alert">Email is required</p>}
                            {errors.email?.type === 'pattern' && <p role="alert">Email is not valid</p>}
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
                                    className={`form-control ${errors.password ? 'errClass' : ''}`}
                                    placeholder="Enter your password"
                                    name="password"
                                    id="password"
                                    // value={user.password}
                                    // onChange={handleInputs}
                                    {...register("password", {
                                        required: true,
                                        minLength: 6,
                                        pattern: /(?=[A-Za-z0-9@#$%^&+!=]+$)^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[@#$%^&+!=])(?=.{8,}).*$/
                                    })}
                                />
                                {errors.password?.type === 'required' && <p role="alert">Password is required</p>}
                                {errors.password?.type === 'minLength' &&
                                    <p role="alert">Password should have min 6 characters</p>}
                                {errors.password?.type === 'pattern' &&
                                    <p role="alert">Password should have minimum one small letter, one capital, one
                                        number
                                        and one special sign</p>}
                            </div>
                        </div>
{/*                        <div className="mb-3">
                            <label htmlFor="c_password" className="form-label">
                                Confirm password
                            </label>
                            <input
                                // type={toggle2 ? "text" : "password"}
                                type="password"
                                className="form-control"
                                placeholder="Repeat your password"
                                name="c_password"
                                id="c_password"
                                {...register("c_password" , {
                                    validate: value =>
                                        value === password.current || "The passwords do not match"
                                })}
                            />
                            {errors.c_password?.type === 'validate' &&
                                <p role="alert">Password doesn't match</p>}
                        </div>*/}
                        <div className="mb-3">
                            <label htmlFor="city" className="form-label">
                                City
                            </label>
                            <input
                                type={"text"}
                                className="form-control"
                                placeholder="Enter your city"
                                name="city"
                                id="city"
                                // value={user.city}
                                // onChange={handleInputs}
                                {...register("city")}
                            />
                        </div>
                        <button type="submit" className="btn btn-primary">
                            Submit
                        </button>
                        <div className="mt-3">
                            <p className="mb-0  text-center">
                                Already have an account?{' '}
                                <a href="{''}" className="text-primary fw-bold">
                                    Sign In
                                </a>
                            </p>
                        </div>
                    </form>

                </div>
            </div>
        </div>
    )
}