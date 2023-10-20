import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import "./NewPost.css";
import TagsInput from "./components/TagsInput";
import "./components/TagsInput.css";
import Hero from "./Hero";

export default function NewPost() {
    const [title, setTitle] = useState('');
    const [text, setText] = useState('');
    const handleSubmit = (e) => {
        e.preventDefault();
        // Handle form submission here
    };

    function ButtonLink({ to, children }) {
        return <Link to={to}><button>{children}</button></Link>;
    }

    return (
        <>
            <Hero />
            <div className="newPostTags">
                <h2></h2>
                <div className="create-post-container">
                    <h1 className="newPostPageTitle">Create Post</h1>
                    <TagsInput />
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
                            placeholder="Text(Optional)"
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
