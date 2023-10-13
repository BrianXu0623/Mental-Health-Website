import React from 'react';
import './DetailedInformation.css';


export default function DetailedInformation(props){
    return (
        <div className="detailed-information-container">
            <div className="detailed-information">
                <h1>This is a title{props.title}</h1>
                <h3>This is an author{props.author}</h3>
                <p>
                    This are some contents{props.content}
                </p>
            </div>
        </div>
    );
}