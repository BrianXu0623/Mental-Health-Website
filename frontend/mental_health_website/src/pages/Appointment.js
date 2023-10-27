import React, { useEffect, useState } from 'react';
import Hero from './Hero.js';
import AppointmentCard from './Cards/AppointmentCard.js';
import './Appointment.css';


const Appointment = () => {

    const [data, setData] = useState([]);
    
    useEffect(() => {
      fetch('http://localhost:8080/api/users/getAllProfessionals')
          .then(response => response.json())
          .then(data => {
              setData(data);
          })
          .catch(error => console.error('Error:', error));
    }, []);

    return (
      <div className='appointment-card-container'>
            {data.map(data => (
                <AppointmentCard key={data.id} 
                icon={data.avatar} 
                name={data.username} 
                institude={data.clinic} 
                available={data.availableHours}
                profilelink={`http://localhost:3000/profile/${data.username}`}
                appointmentlink={`http://localhost:3000/appointment/make/${data.id}`} />
            ))}
        </div>
    )
  };
  
  export default Appointment;