import React from 'react';
import classes from "./About.css";
import kasiaImage from './jaaaaa (1).jpg';
import agaImage from './aga.jpg';

const images = [
    {
        id: 1,
        src: kasiaImage,
        alt: 'Obrazek 1',
        description: 'Opis obrazka 1',
    },
    {
        id: 2,
        src: agaImage,
        alt: 'brace 2',
        description: 'Opis obrazka 2',
    },
    {
        id: 3,
        src: kasiaImage,
        alt: 'Obrazek 3',
        description: 'Opis obrazka 3',
    },
    {
        id: 4,
        src: agaImage,
        alt: 'Obrazek 4',
        description: 'Opis obrazka 4',
    },
];

function About() {
    return (
        <div className="image-grid">
            {images.map((image, index) => (
                <div key={index} className="image-container">
                    <img src={image.src} alt={image.alt} style={{height: "300px"}}/>
                    <div className="image-description">{image.description}</div>
                </div>
            ))}
        </div>
    );
}

export default About;