import React from 'react';
import { Link } from 'react-router-dom';
import './Cards.css';

export default function ForumCard(props) {
    return (
        <div className='forum-card'>
            <li className='forum-card-item'>
                <Link className='forum-card-link' to={props.path}>
                    <div className='forum-card-left'>
                        <div className='forum-card-title'>
                            <h3>{props.title}</h3>
                        </div>
                        <div className='forum-card-info'>
                            <h5 className='forum-card-text'>{props.text}</h5>
                        </div>
                    </div>
                    <div className='forum-card-right'>
                        <h4>{props.author}</h4>
                    </div>
                </Link>
            </li>
        </div>
    );
}