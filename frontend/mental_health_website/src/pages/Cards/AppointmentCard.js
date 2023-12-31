import React from 'react'
import { Link } from 'react-router-dom';
import './Cards.css';
import './AppointmentCard.css';


export default function InformationCard(props) {
    return (
      <div className='appointment-card'>
        <div className='icon'>
            <img src={`data:image/jpeg;base64,${props.icon}`} alt='icon' />
        </div>
        <div className='information'>
            <h2> {props.name} </h2>
            <p> {props.institude} </p>
            <p> Available hours: { props.available} </p>
        </div>
        <div className='options'>
            <a href={props.profilelink} className='view-profile'>View Profile</a>
            <a href={props.appointmentlink} className='make-an-appointment'>Make An Appointment</a>
        </div>
      </div>
    );
  }