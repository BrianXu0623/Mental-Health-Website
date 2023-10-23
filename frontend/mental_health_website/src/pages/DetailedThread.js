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
        fetch(`http://localhost:8080/api/threads/get/id/${id}`)
        .then(response => response.json())
        .then((data) => {
          console.log(data);
          setData(data);
        })
        .catch(error => console.error('Error:', error));
      }, []);

    return (
        <div className="detailed-thread">

            <div className="thread-content">
                <h1>{data.thread.title}</h1>
                <p>
                    {data.thread.content}
                </p>
            </div>

            <div className="buttons">
                <Link to='/addcomment'>+ Add a comment</Link>
                <button onClick={() => favorite_thread}>Favorite</button>
                
            </div>

            <div className="comments">

                {(data.comments).map((item, index) => (
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
