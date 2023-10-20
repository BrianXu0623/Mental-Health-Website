import React from 'react';
import { Link } from 'react-router-dom';
import './Cards.css';
import './InformationCard.css';

export default function InformationCard(props) {
  return (
    <div className='information-card'>
      <li className='information-card-item'>
        <Link className='information-card-link' to={props.path}>
            <div className='information-card-left'>
                <div className='information-card-title'>
                    <h3>{props.title}</h3>
                </div>
                <div className='information-card-info'>
                    <h5 className='information-card-text'>{props.text}</h5>
                </div>
            </div>
            <div className='information-card-right'>
                <h4>
                    {props.author}
                </h4>
            </div>
            
        </Link>
      </li>
    </div>
  );
}