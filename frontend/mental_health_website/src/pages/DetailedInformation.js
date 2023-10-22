import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import './DetailedInformation.css';


export default function DetailedInformation(){

    const { id } = useParams();
    const [data, setData] = useState([]);

    useEffect(() => {
        fetch(`http://localhost:8080/information/${id}`)
        .then(response => response.json())
        .then((data) => {
          setData(data);
        })
        .catch(error => console.error('Error:', error));
      }, []);

    return (
        <div className="detailed-information-container">
            <div className="detailed-information">
                <h1>{data.title}</h1>
                <h3>{data.author}</h3>
                <p>
                    {data.content}
                </p>
            </div>
        </div>
    );
}