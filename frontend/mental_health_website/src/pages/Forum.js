import React, { useState } from 'react';
import './Forum.css';
import { Link } from 'react-router-dom';
import ForumCard from './Cards/ForumCard';
import './components/Button.css';
import Hero from "./Hero";

function Forum() {
    const [forumPosts, setForumPosts] = useState([
        {
            id: 1,
            title: 'Post 1 - Introduction',
            text: 'Welcome to our forum! This is the introduction post.',
            author: 'Admin',
        },
        {
            id: 2,
            title: 'Post 2 - General Discussion',
            text: 'Feel free to discuss anything related to mental health here.',
            author: 'User123',
        },
        {
            id: 3,
            title: 'Post 3 - Coping Strategies',
            text: 'Share your tips and strategies for coping with stress and anxiety.',
            author: 'MentalHealthExpert',
        },
        {
            id: 4,
            title: 'Post 4 - New Topic',
            text: 'This is a new topic for discussion.',
            author: 'User456',
        },
        {
            id: 5,
            title: 'Post 5 - Question',
            text: 'I have a question about mental health.',
            author: 'CuriousMind',
        },
        {
            id: 6,
            title: 'Post 6 - Personal Experience',
            text: 'Sharing my personal journey with mental health.',
            author: 'Survivor123',
        },
    ]);

    return (
        <div className="forum-container">
            <Hero />
            <h1>Forum Posts</h1>
            <div className="forum-posts">
                {forumPosts.map((post) => (
                    <ForumCard
                        key={post.id}
                        title={post.title}
                        text={post.text}
                        author={post.author}
                        path={`/post/${post.id}`}
                    />
                ))}
            </div>
            <div className="bottom-right-button">
                <Link to="/newpost">
                    <div className="circle-button">+</div> {/* Circular button */}
                </Link>
            </div>
        </div>
    );
}

export default Forum;
