package elec5619.sydney.edu.au.mental_health_support_website.service;

import elec5619.sydney.edu.au.mental_health_support_website.db.entities.AppThread;
import elec5619.sydney.edu.au.mental_health_support_website.db.entities.Appointment;
import elec5619.sydney.edu.au.mental_health_support_website.db.repository.AppThreadRepository;
import elec5619.sydney.edu.au.mental_health_support_website.db.repository.AppointmentRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {

    @Resource
    private AppointmentRepository appointmentRepository;

    public Appointment createAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    public boolean cancelAppointment(Long appointmentId) {
        Appointment apm = appointmentRepository.findById(appointmentId).orElse(null);
        if (apm == null) {
            return false;
        }
        appointmentRepository.deleteById(appointmentId);
        return true;
    }

    public boolean editAppointment(Appointment edittedAppointment) {
        Appointment thr = appointmentRepository.findById(edittedAppointment.getId()).orElse(null);
        appointmentRepository.save(edittedAppointment);
        return thr != null;
    }

    public Appointment getAppointment(Long appointmentId) {
        return appointmentRepository.findById(appointmentId).orElse(null);
    }

    public List<Appointment> getAppointments(List<Long> appointmentIds) {
        return appointmentRepository.findAllById(appointmentIds);
    }
}
