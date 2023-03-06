import React from 'react';
import Footer from "../Footer/Footer";
import {Navigate, Outlet} from "react-router-dom";
import Header from "../Header/Header";
import classes from "../LeftSidebar/LeftSidebar.module.css";
import LeftSidebar from "../LeftSidebar/LeftSidebar";
import {Helmet} from "react-helmet";
import AuthenticationService from "../../services/AuthenticationService";

function ProfileLayout() {
    const isUserLoggedIn = AuthenticationService.isUserLoggedIn();

    if (!isUserLoggedIn) {
        // user is not authenticated
        return <Navigate to="/" />;
    }
    return (
        <>
            <Helmet>
                <title> GameXChanger | Profile</title>
            </Helmet>
            <Header/>
            <LeftSidebar className={classes.leftSidebar}/>
            <main>
                <Outlet/>
            </main>
            <Footer/>
        </>
    );
}

export default ProfileLayout;