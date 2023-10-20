import React, { useState, useEffect } from 'react';

const Forum = () => {
  const [threads, setThreads] = useState([]);
  const [currentPage, setCurrentPage] = useState(0);
  const [hasMore, setHasMore] = useState(true);

  const fetchSize = 11; 

  useEffect(() => {
    const currentThreadIds = Array.from({length: fetchSize}, (_, i) => i + 1 + currentPage * 10); 

    fetch('http://localhost:8080/api/threads/get/ids', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(currentThreadIds)
    })
    .then(response => response.json())
    .then(data => {
      if (data.length === fetchSize) {
        setHasMore(true);
        setThreads(prevThreads => [...prevThreads, ...data.slice(0, -1)]); // Removing the 11th item before setting state
      } else {
        setHasMore(false);
        setThreads(prevThreads => [...prevThreads, ...data]);
      }
      setCurrentPage(prevPage => prevPage + 1);
    })
    .catch(error => console.error('There was an error!', error));
  }, [currentPage, hasMore]);

  return (
    <div>
      {threads.map((thread, index) => (
        <div key={index}>
          <h2>{thread.title}</h2>
          <p>{thread.content}</p>
        </div>
      ))}

      {hasMore && 
        <button onClick={() => setCurrentPage(prevPage => prevPage)}>
          Load More
        </button>
      }
    </div>
  );
};

export default Forum;
