import React from 'react';
import Footer from "./Footer";
import {Outlet} from "react-router-dom";
import Header from "./Header";
import classes from "./MainLayout.module.css";

function MainLayout({title}) {
    return (
        <>
            <Header/>
            <main className={classes.content}>
            <Outlet/>
            </main>
            <h2>{title}</h2>
            <Footer/>
        </>
    );
}

export default MainLayout;