import React from 'react';
import Hero from './Hero.js';
import AppointmentCard from './Cards/AppointmentCard.js';
import './Appointment.css';


const Appointment = () => {
    
    return (
      <>
        <Hero />
        <div className='appointment-card-container'>
          <AppointmentCard 
              name='doctor 1'
              institude='first institude'
              available='all day long baby'
              profilelink='/profile/0' 
              appointmentlink='/bookappointment/0'/>

        </div>

        <div className='appointment-card-container'>
          <AppointmentCard 
              name='doctor 2'
              institude='second institude'
              available='not today baby'
              profilelink='/profile/1' 
              appointmentlink='/bookappointment/1'/>

        </div>
      </>
    )
  };
  
  export default Appointment;