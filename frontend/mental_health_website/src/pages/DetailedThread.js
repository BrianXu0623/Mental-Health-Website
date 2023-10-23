import React, { useState } from 'react';
import { Link, useParams } from 'react-router-dom';
import './DetailedThread.css';

function DetailedThread() {

    const favorite_thread = () => {
        console.log("favorited");
    }

    const { id } = useParams();
    const [thread, setThread] = useState([]);

    fetch(`http://localhost:8080/api/threads/get/id/${id}`, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
        })
        .then(response => response.json())
        .then((data) => {setThread(data);})
        .catch(error => console.error('There was an error!', error));

    console.log(thread.thread.title);

    return (
        <div className="detailed-thread">

            <div className="thread-content">
                <h1>{thread.thread.title}</h1>
                <p>
                    {thread.thread.content}
                </p>
            </div>

            <div className="buttons">
                <Link to='/addcomment'>+ Add a comment</Link>
                <button onClick={() => favorite_thread}>Favorite</button>
                
            </div>

            <div className="comments">

                {(thread.comments).map((item, index) => (
                    <div>
                        <div>{item.title}</div>
                        <div>{item.content}</div>
                    </div>
                ))}

            </div>

        </div>
    );
}

export default DetailedThread;
