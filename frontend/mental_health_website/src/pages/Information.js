import React from 'react';
import "./Information.css";
import Hero from "./Hero.js";
import InformationCard from './Cards/InformationCard';

const Information = () => {

  const [data, setData] = useState([]);

    fetch('http://localhost:8080/information')
        .then(response => response.json())
        .then((data) => {
          setData(data);
        })
        .catch(error => console.error('Error:', error));


    return (
      <>
        <Hero />
        <div className='card-container'>
          {data.map((item, index) => (
            <div key={index} className={`${index % 2 === 0 ? 'card-list-left' : 'card-list-right'}`}>
              <InformationCard id={data.}
            </div>))};
        </div>
      </>
      
      
    );
  };
  
  export default Information;