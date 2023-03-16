import React from 'react';
import kasiaImage from './jaaaaa (1).jpg';
import "./About.css";
import dominikaImage from './img_1_1677745215179.jpg'
import agaImage from './aga.jpg'
import magdaImage from './20220929_110943.jpg'
import usImage from './image6.jpeg'

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
                <div>

                    <div className="col-md-3">
                        <img id="usImage" src={usImage} alt="our-team"/>
                    </div>

                </div>
            </div>
        </div>
    )
        ;
}

export default About;