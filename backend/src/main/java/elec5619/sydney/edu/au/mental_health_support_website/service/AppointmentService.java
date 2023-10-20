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

    public Appointment makeAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    public boolean deleteAppointment(Long appointmentId) {
        Appointment apm = appointmentRepository.findById(appointmentId).orElse(null);
        if (apm == null) {
            return false;
        }
        appointmentRepository.deleteById(appointmentId);
        return true;
    }

    public boolean editAppointment(Appointment edittedAppointment) {
        Appointment apm = appointmentRepository.findById(edittedAppointment.getId()).orElse(null);
        appointmentRepository.save(edittedAppointment);
        return apm != null;
    }

    public boolean editStatusAppointment(String status, Appointment updatedAppointment) {
        Appointment apm = appointmentRepository.findById(updatedAppointment.getId()).orElse(null);
        apm.setStatus(status);
        appointmentRepository.save(updatedAppointment);
        return apm != null;
    }

    public Appointment getAppointment(Long appointmentId) {
        return appointmentRepository.findById(appointmentId).orElse(null);
    }

    public List<Appointment> getAppointments(List<Long> appointmentIds) {
        return appointmentRepository.findAllById(appointmentIds);
    }

    public List<Appointment> getAppointmentByUserId(Long userId) {
        return appointmentRepository.findByUserId(userId);
    }

    public List<Appointment> getAppointmentByProfessionalUserId(Long professionalUserId) {
        return appointmentRepository.findByProfessionalUserId(professionalUserId);
    }
}
