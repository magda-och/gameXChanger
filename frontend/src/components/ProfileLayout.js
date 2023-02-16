import React from 'react';
import Footer from "./Footer";
import {Outlet} from "react-router-dom";
import Header from "./Header";
import classes from "./LeftSidebar.module.css";
import LeftSidebar from "./LeftSidebar";
import Profile from "../pages/Profile";

function ProfileLayout({title}) {
    return (
        <>
            <Header/>
            <LeftSidebar className={classes.leftSidebar}/>
            <main>
                <Profile/>
                <Outlet/>
            </main>
            <h2>{title}</h2>
            <Footer/>
        </>
    );
}

export default ProfileLayout;