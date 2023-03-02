import React, {useState} from 'react';
import classes from './Home.css';
import zd1 from './zd1.webp'
import zd2 from './zd2.jpeg'
import zd3 from './zd3.webp'
import zd4 from './708831.jpg'


const images = [
    {
        id: 1,
        src: zd4,
        alt: 'Obrazek 1',
        description: 'To my gamexchanger najlepsze laski huhuhu testuje opis jest fajnie superowo, ale jestesmy czadowe',
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
        <div className="container h-75">
            <div className="row" align="center">
                <div className="col-md-2">
                    <button className="left-arrow" onClick={previousImage}>{'<'}</button>
                </div>
                <div className="col-md-5">
                    <img className="image-home" src={images[currentImageIndex].src}
                         alt={images[currentImageIndex].alt}/>
                </div>
                <div className="col-md-3" id="description">
                    {images[currentImageIndex].description}
                </div>
                <div className="col-md-2">
                    <button className="right-arrow" onClick={nextImage}>{'>'}</button>
                </div>
            </div>
        </div>
    )
        ;
};


export default Home;