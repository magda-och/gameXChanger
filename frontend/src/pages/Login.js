import React, {useState} from 'react';
import {Helmet} from "react-helmet";
import "./Login.css"
import {NavLink} from "react-router-dom";
import AuthenticationService from "../services/AuthenticationService";
import {UserAPI} from "../api/UserAPI";
import {useForm} from "react-hook-form";


export default function Login() {
    const [token, setToken] = useState("");
    const [email, setEmail] = useState("")
    const [password, setPassword] = useState("")
    const [errorMessages, setErrorMessages] = useState({});
    const [togglePassword, setTogglePassword] = useState(false);

    const {handleSubmit, formState: {errors}} =
        useForm({mode: "onBlur"});

    const loginRequest = {
        email: email,
        password: password,
    };

    function loginClicked() {

        try {
            alert(loginRequest.email)
            UserAPI.login(loginRequest)
                .then((response) => {
                    alert("response code: " + response.status)
                    alert("response data: " + response.data.token)
                    //AuthenticationService.registerJwtSuccessfulLogin(email, setToken(response.data.token))
                    window.location.replace('/profile')
                }).catch((reason) => {
                    alert("Dadas"+ reason.status)
                // this.setState({showSuccessMessage: false})
                // this.setState({hasLoginFailed: true})
            })
        } catch (error) {
            alert(error);
            console.error(error);
        }

    };


    const renderErrorMessage = (name) =>
        name === errorMessages.name && (
            <div className="error">{errorMessages.message}</div>
        );

    return (
        <div>
            <div>
                <Helmet>
                    <title> GameXChanger | Login</title>
                </Helmet>
            </div>
            <div className="container mt-5 p-4">
                <div className="row">
                    <div className="col-md-4 offset-md-3 border rounded pb-15 mt-2 shadow">
                        <h2 className="text-center h-25 m-2"> Login! </h2>
                        <form onSubmit={handleSubmit(loginClicked)} className="login-form" id="login-form">
                            <div className="form-label mt-1">
                                <label> Email address </label>
                                <input
                                    className="form-control"
                                    type="email"
                                    placeholder="Enter your email address"
                                    name="email" required
                                    id="email"
                                    value={email}
                                    onChange={(e) => setEmail(e.target.value)}/>
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
                                        value={password}
                                        onChange={(e) => setPassword(e.target.value)}
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
    )
}
