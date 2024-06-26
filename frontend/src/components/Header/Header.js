import React, {useEffect, useState} from 'react';
import {Link, NavLink} from "react-router-dom";
import classes from './Header.module.css';
import './Header.module.css'
import AuthenticationService from "../../services/AuthenticationService";
import {UserAPI} from "../../api/UserAPI";

function Header() {
    const [user, setUser] = useState([]);
    const isUserLoggedIn = AuthenticationService.isUserLoggedIn();
    const userRole = AuthenticationService.getLoggedInUserRole();

    useEffect(() => {
        UserAPI.getByEmail(AuthenticationService.getLoggedInUserName()).then(
            function (response) {
                setUser(response.data)
            }
        ).catch(function (error) {
            console.error(`Error: ${error}`)
        });
    }, []);

    function showAdminButton() {

        if (isUserLoggedIn && userRole === "ADMIN"){
            return (
                <li className="nav-item active">
                    <Link className="nav-link" to={"/profile/admin"}>Admin Page</Link>
                </li>
            )
        }
    }

    return (
        <header className={classes.header}>
            <nav className="navbar navbar-expand-lg navbar-dark navbar-brand fixed-top"
                 style={{"background-color": "#443C68", "width": "100%", "height": "9%"}}>
                <div className="container-fluid">

                    <NavLink to="/" className="navbar-brand">
                        <img src="https://cdn-icons-png.flaticon.com/512/3430/3430812.png" width="40" alt=""
                             className="d-inline-block align-middle mr-2"/>
                        <span className="text-uppercase font-weight-bold" id="title">GameXChanger</span>
                    </NavLink>

                    <div className="collapse navbar-collapse" id="navbarExample01">
                        <ul className="navbar-nav me-auto mb- mb-rg-0">
                            <li className="nav-item active">
                                <Link className="nav-link" to={"/"}>Home</Link>
                            </li>
                            <li className="nav-item active">
                                <Link className="nav-link" to={"/about"}>About</Link>
                            </li>
                            {isUserLoggedIn && <li className="nav-item active">
                                <Link className="nav-link" to={"/profile"}>Profile</Link>
                            </li>}
                            {showAdminButton()}

                        </ul>

                        <ul className="navbar-nav ms-auto d-flex flex-row">

                            {isUserLoggedIn && <li className="nav-item active">
                                <Link className="nav-link" to={"/profile"}>Hello {user.firstName}!</Link>
                            </li>}

                            {!isUserLoggedIn && <li className="nav-item active">
                                <Link className="nav-link" to={"/login"}>Log in</Link>
                            </li>}
                            {isUserLoggedIn && <li className="nav-item active">
                                <Link className="nav-link" to={"/"} onClick={AuthenticationService.logout}>Log
                                    out</Link>
                            </li>}
                        </ul>

                    </div>
                </div>
            </nav>
        </header>
    );
}

export default Header;