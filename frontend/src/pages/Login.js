import React, {useState} from 'react';
import Registration from "./Registration";
import {Helmet} from "react-helmet";
import "./Login.css"
import {NavLink} from "react-router-dom";


export default function Login() {
    const [errorMessages, setErrorMessages] = useState({});
    const [isSubmitted, setIsSubmitted] = useState(false);
    const [togglePassword, setTogglePassword] = useState(false);

    const renderErrorMessage = (name) =>
        name === errorMessages.name && (
            <div className="error">{errorMessages.message}</div>
        );

    return (
        <div>
            <div>
                <Helmet>
                    <title> GameXChanger | Registration</title>
                </Helmet>
                <div>
                    <h1></h1>
                </div>
            </div>
            <div className="container mt-5 p-4">
                <div className="row">
                    <div className="col-md-4 offset-md-3 border rounded pb-15 mt-2 shadow">
                        <h2 className="text-center h-25 m-2"> Login! </h2>
                        <form>
                            <div className="form-label mt-1">
                                <label> Email address </label>
                                <input
                                    className="form-control"
                                    type="email"
                                    placeholder="Enter your email address"
                                    name="email" required
                                    id="email" />
                                {renderErrorMessage("email")}
                            </div>
                            <div className="form-label">
                                <label> Password </label>
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
                                        className="form-control"
                                        placeholder="Enter your password"
                                        name="password"
                                        id="password"
                                    />
                                    {renderErrorMessage("pass")}
                                </div>
                            </div>
                            <button type="submit" className="btn btn-primary">
                                Log in!
                            </button>
                            <div className="mt-4">
                                <p className="mb-9 text-center">
                                    Don't you have an account yet? Register!{' '}
                                    <NavLink to="/user/register" className="text-primary fw-bold">
                                        Sign In
                                    </NavLink>
                                </p>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
            )};
