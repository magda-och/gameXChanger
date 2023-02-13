import React from 'react';
import {Link} from "react-router-dom";
import classes from './Header.module.css';

function Header() {
    return (
        <header className={classes.header}>
            <nav>
                <ul className={classes.list}>
                    <li>
                        <Link to={"/"}>Home</Link>
                    </li>
                    <li>
                        <Link to={"/about"}>About</Link>
                    </li>
                    <li>
                        <Link to={"/user/login"}>Log in</Link>
                    </li>
                    <li>
                        <Link to={"/contacts"}>Contacts</Link>
                    </li>
                </ul>
            </nav>
        </header>
    );
}

export default Header;