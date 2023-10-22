import React from 'react';
import { Link } from 'react-router-dom';
import './Cards.css';
import './InformationCard.css';

export default function InformationCard({id, title, content, author}) {
  console.log('ID:', id); 
    console.log('Title:', title); 
    console.log('Content:', content); 
    console.log('Author:', author); 
  return (
    <div className='information-card'>
      <li className='information-card-item'>
        <Link className='information-card-link' to={`/information/${id}`}>
            <div className='information-card-left'>
                <div className='information-card-title'>
                    <h3>{title}</h3>
                </div>
                <div className='information-card-info'>
                    <h5 className='information-card-text'>{content}</h5>
                </div>
            </div>
            <div className='information-card-right'>
                <h4>
                    {author}
                </h4>
            </div>
            
        </Link>
      </li>
    </div>
  );
}