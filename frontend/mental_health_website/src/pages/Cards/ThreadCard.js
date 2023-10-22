import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import './ThreadCard.css';

const InformationCard = ({id, title, content, tags, no_comments}) => {

  return (
    <div className='thread-card'>
      <div className='thread-header'>
        <div className='thread-title'><h2>{title}</h2></div>
        <div className='thread-tags'>
          {tags.map((tag, index) => (
            <div key={index} className="tag">
              <mark>{tag}</mark>
            </div>
          ))}
        </div>
      </div>

      <div className='thread-body'>{content}</div>
      <div className='thread-foot'>
        <div className='no_comments'>{no_comments} comments</div>
      </div>
      
    </div>
  );
}

export default InformationCard;