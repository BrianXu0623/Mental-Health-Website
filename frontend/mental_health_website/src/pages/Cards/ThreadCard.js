import React from 'react';
import { Link } from 'react-router-dom';
import './ThreadCard.css';

export default function InformationCard(props) {
  return (
    <div className='thread-card'>
      <div className='thread-header'>
        <div className='thread-title'></div>
        <div className='thread-tags'></div>
      </div>
    </div>
  );
}