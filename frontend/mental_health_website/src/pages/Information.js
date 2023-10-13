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
            author='First Author' />

        </ul>
        
        <ul className='card-list-right'>
        <InformationCard 
            path='/information/1'
            title='This is card 2'
            text='this is the second information card'
            author='Second Author' />
        </ul>

        <ul className='card-list-left'>
        <InformationCard 
            path='/information/2'
            title='This is card 3'
            text='this is the third information card'
            author='Third Author' />
        </ul>

        <ul className='card-list-right'>
          <InformationCard 
              path='/information/3'
              title='This is card 4'
              text='this is the fourth information card'
              author='Fourth Author' />
        </ul>
      </div>
      </>
      
      
    );
  };
  
  export default Information;