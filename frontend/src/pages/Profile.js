import React from 'react';
import LeftSidebar from "../components/LeftSidebar";
import Header from "../components/Header";
import classes from "../components/MainLayout.module.css";
import {Outlet} from "react-router-dom";
import Footer from "../components/Footer";
import UserService from "../services/UserService";
import GameService from "../services/GameService";

function Profile({title}) {
    return (
        <>
            <Header/>
            <LeftSidebar/>
            <main className={classes.content}>
                <UserService/>
                <GameService/>
                <Outlet/>
                <h1>profile</h1>
            </main>
            <h2>{title}</h2>
            <Footer/>

        </>
    );
}

export default Profile;