import React, { useState, useEffect } from 'react';
import Hero from './Hero.js';
import ThreadCard from './Cards/ThreadCard.js';

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
          <ThreadCard key={index} title={thread.thread.title} content={thread.thread.content} tags={thread.tagNames} />
        </div>
      ))}

    </div>
  );
};

export default Forum;
