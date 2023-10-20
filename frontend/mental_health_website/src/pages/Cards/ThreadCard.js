import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import './ThreadCard.css';

const InformationCard = ({title, content, tags}) => {

  return (
    <div className='thread-card'>
      <div className='thread-header'>
        <div className='thread-title'>{title}</div>
        <div className='thread-tags'>{tags}</div>
      </div>

      <div className='thread-body'>{content}</div>
      
    </div>
  );
}

export default InformationCard;