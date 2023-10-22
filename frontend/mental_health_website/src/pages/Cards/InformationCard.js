import React from 'react';
import { Link } from 'react-router-dom';
import './Cards.css';
import './InformationCard.css';

export default function InformationCard({id, title, content, author}) {

  return (
    <div className='information-card'>
      <li className='information-card-item'>
        <Link className='information-card-link' to={`/information/${id}`}>
            <div className='information-card-left'>
                <div className='information-card-title'>
                    {title}
                </div>
                <div className='information-card-info'>
                    {content}
                </div>
            </div>
            <div className='information-card-right'>
                    {author}
            </div>
            
        </Link>
      </li>
    </div>
  );
}