import React from 'react';
import Footer from "../Footer/Footer";
import {Outlet} from "react-router-dom";
import Header from "../Header/Header";
import classes from "../LeftSidebar/LeftSidebar.module.css";
import LeftSidebar from "../LeftSidebar/LeftSidebar";

function ProfileLayout() {
    return (
        <>
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