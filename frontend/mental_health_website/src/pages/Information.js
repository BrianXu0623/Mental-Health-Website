import React, { useState, useEffect } from 'react';
import "./Information.css";
import Hero from "./Hero.js";
import InformationCard from './Cards/InformationCard';

const Information = () => {

  const [data, setData] = useState([]);

    useEffect(() => {
      fetch('http://localhost:8080/information')
      .then(response => response.json())
      .then((data) => {
        setData(data);
      })
      .catch(error => console.error('Error:', error));
    }, []);
    

    return (
      <>
        <Hero />
        <div className='card-container'>
          {data.map((item, index) => (
            <div key={item.id} className={`${index % 2 === 0 ? 'card-list-left' : 'card-list-right'}`}>
              <InformationCard id={item.id} title={item.title} content={item.content} author={item.author} />
            </div>))}
        </div>
      </>
      
      
    );
  };
  
  export default Information;