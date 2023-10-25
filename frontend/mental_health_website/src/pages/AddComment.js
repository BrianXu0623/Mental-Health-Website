import React, { useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';

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

    const handleSubmit = (e) => {
        e.preventDefault();
        const userToken = localStorage.getItem('token');



        const ThreadComment = {
            userToken: userToken,
            threadId: id,
            comment: comment,
            timestamp: new Date().toISOString(),
        };

        fetch('http://localhost:8080/api/threads/comment/create', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(ThreadComment),
        })
            .then((response) => response.json())
            .then((data) => {
                console.log(userToken);

                console.log('Comment added:', data);
                navigate(`/forum/${id}`);
            })
            .catch((error) => {
                console.error('Error adding comment:', error);
            });
    };



    return (
        <div>
            <h1>Add a Comment</h1>
            <form onSubmit={handleSubmit}>
                <div className="form-group">
                    <textarea
                        value={comment}
                        onChange={handleCommentChange}
                        placeholder="Enter your comment"
                        required
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
