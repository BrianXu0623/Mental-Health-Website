import React, { useState, useEffect } from 'react';
import Hero from './Hero.js';

const Forum = () => {
  const [threads, setThreads] = useState([]);

  useEffect(() => {

    fetch('http://localhost:8080/api/threads/get/all', {
      method: 'GET',
      headers: {
        'Content-Type': 'application/json'
      }
    })
    .then(response => response.json())
    .then((data) => {
      setThreads(data);
    })
    .catch(error => console.error('There was an error!', error));
  });

  return (
    
    <div>
      <Hero />
      {threads.map((thread, index) => (
        <div key={index}>
          <h2>{thread.thread.title}</h2>
          <p>{thread.thread.content}</p>
        </div>
      ))}

    </div>
  );
};

export default Forum;
