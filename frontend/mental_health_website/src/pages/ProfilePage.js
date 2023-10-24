import React, { useEffect, useState } from 'react';
import './ProfilePage.css';
import { useParams } from 'react-router-dom';

const ProfilePage = (props) => {

    const { id } = useParams();
    const [data, setData] = useState([]);

    useEffect(() => {
        fetch(`http://localhost:8080/api/users/searchUser/${id}`, {
            method: 'GET',
          })
            .then(response => response.json())
            .then(data => {
                setData(data);
                console.log(data);
            })
            .catch(error => console.error('Error:', error));
    }, []);
      

    return (
        <div className='profile-container'>
            <div className="profile-header">
                <img 
                        src={data.avatar}
                        alt="User Avatar" 
                        className="profile-picture"
                    />
                <div className='profile-title'>
                    
                    <p className='profile-name'>{data.username}</p>
                    <p className='profile-institude'>{data.clinic}</p>
                    <p className='profile-available'>{data.availableHours}</p>
                    <a href={`/make-appointment/${id}`}>Make an appointment now</a>
                </div>
                <div className='profile-rating'>
                    <p className='current-rating'>Current Rating</p>
                    <p className='rate-me-now'>Rate me now</p>
                </div>
            </div>
            <div className='profile-body'>
                <h3>Experience</h3>
                <p>{data.experience}</p>
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
