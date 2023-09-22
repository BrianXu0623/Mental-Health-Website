import React from 'react';
import "./Information.css";
import Hero from "./Hero.js";
import InformationCard from './Cards/InformationCard';

const Information = () => {
    return (
      <>
        <Hero />
        <div className='card-container'>
        <ul className='card-list-left'>
          <InformationCard 
            path='/information/0'
            title='This is card 1'
            text='this is the first information card'
            author='Stack Overflow' />

        </ul>
        
        <ul className='card-list-right'>
          <div className="information-card">
            <h2>Title</h2>
            <p>This is card content</p>
          </div>
        </ul>

        <ul className='card-list-left'>
          <div className="information-card">
            <h2>Title</h2>
            <p>This is card content</p>
          </div>
        </ul>

        <ul className='card-list-right'>
          <div className="information-card">
            <h2>Title</h2>
            <p>This is card content</p>
          </div>
        </ul>
      </div>
      </>
      
      
    );
  };
  
  export default Information;