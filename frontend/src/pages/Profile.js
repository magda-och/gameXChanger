import React from 'react';
import LeftSidebar from "../components/LeftSidebar";
import Header from "../components/Header";
import classes from "../components/MainLayout.module.css";
import {Outlet} from "react-router-dom";
import Footer from "../components/Footer";

function Profile({title}) {
    return (
        <>
            <LeftSidebar />
            <Header/>
            <main className={classes.content}>
                <UserService/>
                <Outlet/>
            </main>
            <h2>{title}</h2>
            <Footer/>

        </>
    );
}

export default Profile;