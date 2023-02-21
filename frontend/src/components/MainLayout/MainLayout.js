import React, {useEffect} from 'react';
import Footer from "../Footer/Footer";
import {Outlet} from "react-router-dom";
import Header from "../Header/Header";
import classes from "./MainLayout.module.css";
import {Helmet} from "react-helmet";



function MainLayout() {
    return (
        <>
            <Helmet>
                <title> GameXChanger | Main Page</title>
            </Helmet>
            <Header/>
            <main className={classes.content}>
            <Outlet/>
            </main>
            <h2> </h2>
            <Footer/>
        </>
    );
}

export default MainLayout;