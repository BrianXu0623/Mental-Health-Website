import { Link } from 'react-router-dom';
import React, { useState, useEffect } from 'react';
import Hero from './Hero.js';
import ThreadCard from './Cards/ThreadCard.js';
import './Forum.css';

const Forum = () => {
  const [threads, setThreads] = useState([]);
  const [isButtonVisible, setIsButtonVisible] = useState(true);

  useEffect(() => {
    fetch('http://localhost:8080/api/threads/get/all', {
      method: 'GET',
      headers: {
        'Content-Type': 'application/json',
      },
    })
        .then((response) => response.json())
        .then((data) => {
          setThreads(data);
          setIsButtonVisible(true);
        })
        .catch((error) => {
          console.error('There was an error!', error);
          setIsButtonVisible(true);
        });
  }, []);

  return (
      <div>
        <Hero />
        {threads.map((thread, index) => (
            <Link to={`/forum/${thread.thread.id}`} key={index}>
              <ThreadCard
                  id={thread.thread.id}
                  key={index}
                  title={thread.thread.title}
                  content={thread.thread.content}
                  tags={thread.tagNames}
                  no_comments={thread.noComments}
              />
            </Link>
        ))}

        {isButtonVisible && (
            <Link to="/new-post">
              <button className="new-post-button">New Post</button>
            </Link>
        )}
      </div>
  );
};

export default Forum;
