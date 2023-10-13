import React from 'react';
import { Link } from 'react-router-dom';
import './DetailedThread.css';

function DetailedThread() {

    const favorite_thread = () => {
        console.log("favorited");
    }

    return (
        <div className="detailed-thread">

            <div className="thread-content">
                <h1>Thread Title</h1>
                <p>
                    This is thread content
                </p>
            </div>

            <div className="buttons">
                <Link to='/addcomment'>+ Add a comment</Link>
                <button onClick={() => favorite_thread}>Favorite</button>
                
            </div>

            <div className="comments">

                <p>Comment 1</p>
                <p>Comment 2</p>

            </div>

        </div>
    );
}

export default DetailedThread;
