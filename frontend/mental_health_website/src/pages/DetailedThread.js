import React, { useState, useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import './DetailedThread.css';

function DetailedThread() {

    const { id } = useParams();
    const [data, setData] = useState([]);

    useEffect(() => {
        fetch(`http://localhost:8080/api/threads/get/id/${id}`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            }
        })
            .then(response => response.json())
            .then(data => {
                setData(data);
                console.log(data);
            })
            .catch(error => console.error('Error:', error));
    }, [id]);

    return (
        <div className="detailed-thread">
            <div className="thread-content">
                <h1>{data?.thread?.title}</h1>
                <p>{data?.thread?.content}</p>
            </div>

            <div className="actions">
                <Link to={`/addcomment/${data?.thread?.id}`} className="comment-link">
                    + Add a comment
                </Link>
            </div>

            <div className="comments">
                <div className="comments-heading">Comments:</div>
                {data?.comments?.map(comment => (
                    <div key={comment.id} className="comment-content">
                        <span className="comment-author">{comment.commentAuthor}</span>
                        <span> - {comment.comment}</span>
                    </div>
                ))}
            </div>
        </div>
    );
}

export default DetailedThread;
