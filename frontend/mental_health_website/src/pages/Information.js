import React from 'react'
import "./Information.css"

const Information = () => {
    return (
      <div className='card-container'>
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
      
    );
  };
  
  export default Information;