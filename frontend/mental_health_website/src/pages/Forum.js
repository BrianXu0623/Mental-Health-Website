import React, { Component } from 'react';
import "./Forum.css"
class Forum extends Component {
    constructor(props) {
        super(props);
        this.state = {
            posts: this.generateFakePosts(10),
        };
    }
    generateFakePosts = (numPosts) => {
        const fakePosts = [];

        for (let i = 1; i <= numPosts; i++) {
            fakePosts.push({
                id: i,
                title: `Post ${i}`,
                comments: this.generateFakeComments(Math.floor(Math.random() * 10)),
                timestamp: new Date(Date.now() - Math.floor(Math.random() * 10000000)),
            });
        }

        return fakePosts;
    };

    generateFakeComments = (numComments) => {
        const fakeComments = [];

        for (let i = 1; i <= numComments; i++) {
            fakeComments.push({
                id: i,
                text: `Comment ${i} for this post.`,
            });
        }

        return fakeComments;
    };

    calculateMinutesAgo = (timestamp) => {
        const now = new Date();
        const postTime = new Date(timestamp);
        const timeDifference = Math.floor((now - postTime) / (1000 * 60));
        return timeDifference;
    }

    render() {
        return (

            <div  className="post-container">
                <h1>Forum Posts</h1>
                <ul className="post-item-ul">
                    {this.state.posts.map((post) => (
                        <li key={post.id}>
                            <div className="post-item">
                                <a>{post.title}</a>
                                <p>{post.comments.length} comments</p>
                                <p>Posted {this.calculateMinutesAgo(post.timestamp)} minutes ago</p>
                            </div>
                        </li>
                    ))}
                </ul>
            </div>
        );
    }
}

export default Forum;
