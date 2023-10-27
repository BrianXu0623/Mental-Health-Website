import React, { useEffect, useState } from 'react';
import './ProfilePage.css';
import { useParams } from 'react-router-dom';

const ProfilePage = (props) => {

    const { id } = useParams();
    const [rating, setRating] = useState(0);
    const [currentRating, setCurrentRating] = useState(0);
    const [targetUser, setTargetUser] = useState('');
    const [data, setData] = useState({
        avatar: '',
        username: '',
        clinic: '',
        availableHours: '',
        averageRating: 0, 
      });

      const handleRatingChange = (event) => {
        setRating(event.target.value);
      };
    
      const handleSubmit = async (event) => {
        event.preventDefault();
        const response = await fetch('https://your-api-endpoint.com/rateProfessional', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'token': localStorage.getItem('token')
                },
                body: JSON.stringify({
                    targetUser,
                    rating
                })
            });

            const data = await response.json();

            if (response.ok) {
                console.log('Success:', data);
            } else {
                console.error('Error:', data);
            }
        };
      

    useEffect(() => {
        console.log('useEffect is running');
        fetch(`http://localhost:8080/api/users/searchProfessional/${id}`, {
            method: 'GET',
          })
            .then(response => response.json())
            .then(data => {
                setData(data.user);
                setCurrentRating(data.averageRating);
                setTargetUser(data.user.username);
                console.log("got data");
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
                    <a href={`/appointment/make/${data.id}`}>Make an appointment now</a>
                </div>
                <div className='profile-rating'>
                    <p className='current-rating'>Current Rating: {currentRating}</p>
                    <form onSubmit={handleSubmit}>
                        <label>
                            Rate Me (0 - 100):
                            <input
                            type="number"
                            min="0"
                            max="100"
                            value={rating}
                            onChange={handleRatingChange}
                            />
                        </label>
                        <button type="submit">Submit Rating</button>
                        </form>
                </div>
            </div>
            <div className='profile-body'>
                <h3>Experience</h3>
                <p>{data.experience}</p>
            </div>
            <div className='profile-comments'>
                {data?.professionalCommentList?.map((item) => (
                <div >
                    <h3>Recent User Comments</h3>
                    <h4>Example User</h4>
                    <p className='comment-content'>User comment contents blah blah</p>
                </div>))}
                
            </div>
        </div>
        
    );
}

export default ProfilePage;
