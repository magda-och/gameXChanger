import React from 'react';
import LeftSidebar from "../components/LeftSidebar";
import Header from "../components/Header";
import classes from "../components/MainLayout.module.css";
import {Outlet} from "react-router-dom";
import Footer from "../components/Footer";
import UserService from "../services/UserService";

function Profile({title}) {
    return (
        <>
            <Header/>
            <LeftSidebar/>
            <main className={classes.content}>
                <Outlet/>
            </main>
            <h2>{title}</h2>
            <UserService/>
            <Footer/>

        </>
    );
}

export default Profile;