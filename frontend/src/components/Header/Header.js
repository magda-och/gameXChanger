import React from 'react';
import {Link, NavLink} from "react-router-dom";
import classes from './Header.module.css';

function Header() {
    return (
        <header className={classes.header}>
            <nav className="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
                <div className="container-fluid">

                    <NavLink to="/" className="navbar-brand">
                        <img src="https://cdn-icons-png.flaticon.com/512/3430/3430812.png" width="45" alt=""
                             className="d-inline-block align-middle mr-2"/>
                            <span className="text-uppercase font-weight-bold">GameXChanger</span>
                    </NavLink>

                    <div className="collapse navbar-collapse" id="navbarExample01">
                        <ul className="navbar-nav me-auto mb- mb-rg-0">
                            <li className="nav-item active">
                                <Link className="nav-link" to={"/"}>Home</Link>
                            </li>
                            <li className="nav-item active">
                                <Link className="nav-link" to={"/about"}>About</Link>
                            </li>

                            <li className="nav-item active">
                                <Link className="nav-link" to={"/contacts"}>Contacts</Link>
                            </li>
                            <li className="nav-item active">
                                <Link className="nav-link" to={"/profile"}>Profile</Link>
                            </li>
                        </ul>

                        <ul className="navbar-nav ms-auto d-flex flex-row">
                            <li className="nav-item active">
                                <Link className="nav-link" to={"/user/login"}>Sign in</Link>
                            </li>
                        </ul>

                    </div>
                </div>
            </nav>
        </header>
    );
}

export default Header;