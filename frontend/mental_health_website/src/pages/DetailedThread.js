import React, { useState, useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import './DetailedThread.css';

function DetailedThread() {

    const favorite_thread = () => {
        console.log("favorited");
    }

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

            

            <div className="buttons">
                <Link to={`/addcomment/${data?.thread?.id}`}>+ Add a comment</Link>
                <button onClick={favorite_thread}>Favorite</button> 
            </div>

            <div className="comments">
            <div>Comments:</div>
                {data?.comments?.map(comment => ( // Adjusted the comment rendering
                    <div key={comment.id}> 
                        <div>Username: {comment.commentAuthor}</div>
                        <div>Comment: {comment.comment}</div>
                    </div>
                ))}
            </div>
        </div>
    );
}

export default DetailedThread;
