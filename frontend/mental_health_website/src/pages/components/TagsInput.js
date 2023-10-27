import React, { useState } from 'react';

function TagsInput({ tags, setTags }) {
    function handleKeyDown(e) {
        if (e.key !== 'Enter') return;
        const value = e.target.value;
        if (!value.trim()) return;
        setTags([...tags, value]);
        e.target.value = '';


        console.log('Updated tags:', tags);
    }

    function removeTag(index) {
        setTags(tags.filter((el, i) => i !== index));


        console.log('Updated tags after removal:', tags);
    }

    return (
        <div className="tags-input-container">
            {tags.map((tag, index) => (
                <div className="tag-item" key={index}>
                    <span className="text">{tag}</span>
                    <span className="close" onClick={() => removeTag(index)}>&times;</span>
                </div>
            ))}
            <input onKeyDown={handleKeyDown} type="text" className="tags-input" placeholder="Type something" />
        </div>
    );
}

export default TagsInput;
