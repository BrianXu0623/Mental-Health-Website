import React, { useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import './AddComment.css'; // 确保样式文件存在

function AddComment() {
    const { id } = useParams();
    const [comment, setComment] = useState('');
    const [token, setToken] = useState('');
    const navigate = useNavigate();

    useEffect(() => {
        const userToken = localStorage.getItem('userToken');
        if (userToken) {
            setToken(userToken);
        }
    }, []);

    const handleCommentChange = (e) => {
        setComment(e.target.value);
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        const userToken = localStorage.getItem('token');

        const ThreadComment = {
            userToken: userToken,
            threadId: id,
            comment: comment,
            timestamp: new Date().toISOString(),
        };

        try {
            const response = await fetch('http://localhost:8080/api/threads/comment/create', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': `Bearer ${userToken}`,
                    'token': `${userToken}`
                },
                body: JSON.stringify(ThreadComment),
            });

            if (!response.ok) {
                throw new Error('Error adding comment');
            }

            const data = await response.json();
            console.log('Comment added:', data);
            navigate(`/forum/${id}`);
        } catch (error) {
            console.error('Error adding comment:', error);
        }
    };

    return (
        <div className="add-comment-container">
            <h1>Add a Comment</h1>
            <form onSubmit={handleSubmit} className="comment-form">
                <div className="form-group">
                    <textarea
                        value={comment}
                        onChange={handleCommentChange}
                        placeholder="Enter your comment"
                        required
                        className="comment-textarea"
                    />
                </div>
                <div className="button-container">
                    <button type="submit" className="btn btn-primary">
                        Add Comment
                    </button>
                </div>
            </form>
        </div>
    );
}

export default AddComment;
