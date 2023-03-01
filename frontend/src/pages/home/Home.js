import React, {useState} from 'react';
import classes from './Home.css';
import zd1 from './zd1.webp'
import zd2 from './zd2.jpeg'
import zd3 from './zd3.webp'


const images = [
    {
        id: 1,
        src: zd1,
        alt: 'Obrazek 1',
        description: 'Opis obrazka 1',
    },
    {
        id: 2,
        src: zd2,
        alt: 'Obrazek 2',
        description: 'Opis obrazka 2',
    },
    {
        id: 3,
        src: zd3,
        alt: 'Obrazek 3',
        description: 'Opis obrazka 3',
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
            <div className="container">
                <button className="left-arrow" onClick={previousImage}>{'<'}</button>
                <div className="image-container">
                    <img className="image-home" src={images[currentImageIndex].src} alt={images[currentImageIndex].alt} />
                    <div className="image-description"> {images[currentImageIndex].description}</div>
                </div>
                <button className="right-arrow" onClick={nextImage}>{'>'}</button>
            </div>
        );
    };



export default Home;