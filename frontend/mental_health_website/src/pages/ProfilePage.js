import React from 'react';
import './ProfilePage.css';

const ProfilePage = (props) => {
    return (
        <div className='profile-container'>
            <div className="profile-header">
                <img 
                        src={props.icon}
                        alt="User Avatar" 
                        className="profile-picture"
                    />
                <div className='profile-title'>
                    
                    <p className='profile-name'>Dr. Jack</p>
                    <p className='profile-institude'>Brown Central Clinic</p>
                    <p className='profile-available'>Available Hours: {props.available}</p>
                    <a href='/make-appointment'>Make an appointment now</a>
                </div>
                <div className='profile-rating'>
                    <p className='current-rating'>Current Rating</p>
                    <p className='rate-me-now'>Rate me now</p>
                </div>
            </div>
            <div className='profile-body'>
                <h3>Experience</h3>
                <p>this is some example bios</p>
            </div>
            <div className='profile-comments'>
                <h3>Recent User Comments</h3>
                <h4>Example User</h4>
                <p className='comment-content'>User comment contents blah blah</p>
            </div>
        </div>
        
    );
}

export default ProfilePage;
