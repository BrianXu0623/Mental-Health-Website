import React from 'react';
import studentBanner from '../resource/student-banner.png'
import "./Hero.css";

export default function Hero(){

    return (
        <div className='student-banner-container'>
            <img className='student-banner' src={studentBanner} alt='Student-Banner'>

            </img>
        </div>
      
    );
}