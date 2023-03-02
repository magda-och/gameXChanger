import React from 'react';
import kasiaImage from './jaaaaa (1).jpg';
import "./About.css";
import dominikaImage from './img_1_1677745215179.jpg'

function About() {
    return (
        <div className="image-grid">
            <div className="containter">
                <div className="row" align="left">
                    <h3>How did our story begin?</h3>
                </div>
                <div className="row mt-3" align="left">
                    <p> At the end of 2022, while attending the Java Academy,
                        the four of us teamed up to create a unique board game exchange site.
                        After several months of hard work, we managed to create a fully functional application.</p>
                </div>
                <div className="row mt-3" align="left">
                    <h3>Something more about our team...</h3>
                </div>
                <div className="row mt-3" align="left">

                    <div className="col-md-3">
                        <img id="photoKasia" src={kasiaImage} alt="Kasia"/>
                    </div>

                    <div className="col-md-3" align="left">
                        <h3>Kasia</h3>
                        <p>Text about kasia</p>
                        <p>text</p>
                        <p>text</p>
                    </div>
                    <div className="col-md-3">
                        <img id="photoKasia" src={kasiaImage} alt="Agas"/>
                    </div>
                    <div className="col-md-3">
                        <h3> Agnieszka</h3>
                        <p>Text about kasia</p>
                        <p>text</p>
                        <p>text</p>
                    </div>
                    <div className="row mt-5" align="left">
                        <div className="col-md-3">
                            <img id="photoKasia" src={kasiaImage} alt="Kasia"/>
                        </div>
                        <div className="col-md-3" >
                            <h3>Magda</h3>
                            <p>Text about kasia</p>
                            <p>text</p>
                            <p>text</p>
                        </div>
                        <div className="col-md-3">
                            <img id="photoDominika" src={dominikaImage} alt="Dominika"/>
                        </div>
                        <div className="col-md-3" >
                            <h3>Dominika</h3>
                            <p>Text about kasia</p>
                            <p>text</p>
                            <p>text</p>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    )
        ;
}

export default About;