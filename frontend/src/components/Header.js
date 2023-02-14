import React from 'react';
import {Link} from "react-router-dom";
import classes from './Header.module.css';

function Header() {
    return (
        <header className={classes.header}>
            <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
                <div className="container-fluid">
                    <a href="#" className="navbar-brand">
                        <img src="https://bootstrapious.com/i/snippets/sn-nav-logo/logo.png" width="45" alt=""
                             className="d-inline-block align-middle mr-2"/>
                            <span className="text-uppercase font-weight-bold">GameXChanger</span>
                    </a>

                    <button
                        className="navbar-toggle"
                        type="button"
                        data-mdb-toggle="collapse"
                        data-mdb-target="#navbarExample01"
                        aria-controls="navbarExample01"
                        aria-expanded="false"
                        aria-label="Toggle navigation"
                        class="navbar-toggle"
                    >
                        <span class="navbar-toggle-icon"></span>
                    </button>
                    <div className="collapse navbar-collapse" id="navbarExample01">
                        <ul class="navbar-nav me-auto mb- mb-lg-0">
                            <li class="nav-item active">
                                <Link class="nav-link" to={"/"}>Home</Link>
                            </li>
                            <li class="nav-item active">
                                <Link class="nav-link" to={"/about"}>About</Link>
                            </li>
                            <li class="nav-item active">
                                <Link class="nav-link" to={"/user/login"}>Log in</Link>
                            </li>
                            <li class="nav-item active">
                                <Link class="nav-link" to={"/contacts"}>Contacts</Link>
                            </li>
                        </ul>
                    </div>
                </div>
            </nav>
        </header>
    );
}

export default Header;