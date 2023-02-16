import React from 'react';
import Footer from "./Footer";
import {Outlet} from "react-router-dom";
import Header from "./Header";
import classes from "./LeftSidebar.module.css";
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