import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import Hero from './Hero.js';
import ThreadCard from './Cards/ThreadCard.js';
import './Forum.css';

const Forum = () => {
    const [threads, setThreads] = useState([]);
    const [isLoading, setIsLoading] = useState(true);
    const [searchQuery, setSearchQuery] = useState('');

    const handleKeyPress = (event) => {
        if (event.key === 'Enter') {
          search();
        }
      };

    const search = () => {
        fetch(`http://localhost:8080/api/threads/search/tag/${searchQuery}`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
            }
        })
            .then((response) => response.json())
            .then((data) => {
                setThreads(data);
                console.log(data);
                console.log(searchQuery);
                setIsLoading(false);
            })
            .catch((error) => {
                console.error('There was an error!', error);
                setIsLoading(false);
            });
    }

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
                setIsLoading(false);
            })
            .catch((error) => {
                console.error('There was an error!', error);
                setIsLoading(false);
            });
    }, []);

    return (
        <div>
            <Hero />
            <div className='search-bar-container'>
              <form className='search-bar' onSubmit={search}>
              <input
                    type="text"
                    placeholder="Search..."
                    value={searchQuery}
                    onChange={(e) => setSearchQuery(e.target.value)}
                    onKeyPress={handleKeyPress}
                />
                <button onClick={search}>Search</button>
                
              </form>
            </div>
            {isLoading ? (
                <div>Loading...</div>
            ) : (
                <>
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
                    <Link to="/forum/newthread/">
                        <button className="new-post-button">+</button>
                    </Link>
                </>
            )}
        </div>
    );
};

export default Forum;
