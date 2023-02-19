import React from 'react';
import Footer from "./Footer/Footer";
import {Outlet} from "react-router-dom";
import Header from "./Header/Header";
import classes from "../styles/LeftSidebar.module.css";
import LeftSidebar from "./LeftSidebar";

function ProfileLayout({title}) {
    return (
        <>
            <Header/>
            <LeftSidebar className={classes.leftSidebar}/>
            <main>
                <Outlet/>
            </main>
            <h2>{title}</h2>
            <Footer/>
        </>
    );
}

export default ProfileLayout;