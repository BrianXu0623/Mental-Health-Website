import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import Hero from './Hero.js';
import ThreadCard from './Cards/ThreadCard.js';
import './Forum.css';

const Forum = () => {
    const [threads, setThreads] = useState([]);
    const [isLoading, setIsLoading] = useState(true);
    const [searchValue, setSearchValue] = useState('');

    const handleInputChange = (event) => {
        setSearchValue(event.target.value);
        console.log("Searching for:", event.target.value);
    };

    const handleKeyPress = (event) => {
        if (event.key === 'Enter') {
          search();
        }
      };

    const search = async () => {
        await fetch(`http://localhost:8080/api/threads/search/tag/`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ tagName: searchValue }),
        })
            .then((response) => response.json())
            .then((data) => {
                setThreads(data);
                console.log(data);
                console.log(searchValue);
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
                <div>
                    <h1>Search Bar in React</h1>
                    <input
                        type="text"
                        placeholder="Search..."
                        value={searchValue}
                        onChange={handleInputChange}
                    />
                </div>
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
