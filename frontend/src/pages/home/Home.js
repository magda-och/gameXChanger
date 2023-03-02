import React, {useState} from 'react';
import classes from './Home.css';
import zd1 from './zd1.webp'
import zd2 from './zd2.jpeg'
import zd3 from './zd3.webp'
import zd4 from './708831.jpg'
import zdj1 from './gryplanszowe1.jpg'
import zdj5 from './gryplanszowe2.jpg'
import zdj6 from './planszowki4.jpg'
import zdj7 from './planszowki10.jpg'
import zdj8 from './zdj30.jpg'


const images = [
    {
        id: 1,
        src: zdj6,
        alt: 'Obrazek 1',
        description: 'GameXChanger is a website where you have the opportunity to exchange games.  ' +
            'It gives you the chance to very easily borrow a game you\'ve been dreaming of from a friend, and play it completely for free!',
    },
    {
        id: 2,
        src: zdj5,
        alt: 'Obrazek 2',
        description: 'With us, you have the chance to deepen your relationships with your friends by getting together to play board games!' +
            ' Why not start right away?',
    },
    {
        id: 3,
        src: zdj8,
        alt: 'Obrazek 3',
        description: 'Exchanging board games has never been so easy! Create an account, search for friends and create your own board game shelf so others know what games you own! Isn\'t it trivial?',
    },
];

const Home = () => {

    const [currentImageIndex, setCurrentImageIndex] = useState(0);

    const previousImage = () => {
        setCurrentImageIndex(
            currentImageIndex === 0 ? images.length - 1 : currentImageIndex - 1
        );
    };

    const nextImage = () => {
        setCurrentImageIndex(
            currentImageIndex === images.length - 1 ? 0 : currentImageIndex + 1
        );
    };

    return (
        <div className="container h-75" id="main-container">
            <div className="row" align="center">
                <div className="col-md-2">
                    <button id="left-arrow" className="btn btn-primary btn-lg" style={{"background-color" : "#80489C"}} onClick={previousImage}>{'<'}</button>
                </div>
                <div className="col-md-5">
                    <img className="image-home" src={images[currentImageIndex].src}
                         alt={images[currentImageIndex].alt}/>
                </div>
                <div className="col-md-3" id="description">
                    {images[currentImageIndex].description}
                </div>
                <div className="col-md-2">
                    <button id="right-arrow" className="btn btn-primary btn-lg" style={{"background-color" : "#80489C"}} onClick={nextImage}>{'>'}</button>
                </div>
            </div>
        </div>
    )
        ;
};


export default Home;