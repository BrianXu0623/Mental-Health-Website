
import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import "./NewPost.css";
import TagsInput from "./components/TagsInput";
import Hero from "./Hero";
import { useNavigate } from 'react-router-dom';

export default function NewPost() {
    const [title, setTitle] = useState('');
    const [text, setText] = useState('');
    const [tags, setTags] = useState([]);
    const navigate = useNavigate();

    const handleSubmit = (e) => {
        e.preventDefault();
        const userToken = localStorage.getItem('token');

        const postData = {
            thread: {
                title: title,
                content: text,
                timestamp: new Date().toISOString(),
            },
            tagNames: tags,
            userToken: userToken,
        };
        console.log(userToken);

        fetch('http://localhost:8080/api/threads/create', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${userToken}`,
                'token': `${userToken}`
            },
            body: JSON.stringify(postData),
        })
            .then(response => response.json())
            .then(data => {
                console.log('Post created successfully:', data);
                navigate(`/forum/${data.thread.id}`);
            })
            .catch(error => {
                console.error('Failed to create post', error);
            });
    };

    return (
        <>
            <Hero />
            <div className="newPostTags">
                <div className="create-post-container">
                    <h1 className="newPostPageTitle">Create Post</h1>
                    <TagsInput
                        tags={tags}
                        setTags={setTags}
                    />
                </div>
                <form className="newPostForm" onSubmit={handleSubmit}>
                    <div className="form-group">
                        <textarea
                            id="title"
                            value={title}
                            onChange={(e) => setTitle(e.target.value)}
                            placeholder="Title"
                        />
                    </div>
                    <div className="form-group">
                        <textarea
                            id="text"
                            value={text}
                            onChange={(e) => setText(e.target.value)}
                            placeholder="Text (Optional)"
                        />
                    </div>
                    <div className="button-container">
                        <button type="submit" className="btn btn-primary">
                            Save Draft
                        </button>
                        <button type="submit" className="btn btn-primary">
                            Submit
                        </button>
                    </div>
                </form>
            </div>
        </>
    );
}