package elec5619.sydney.edu.au.mental_health_support_website.controller;

import elec5619.sydney.edu.au.mental_health_support_website.db.entities.Appointment;
import elec5619.sydney.edu.au.mental_health_support_website.db.entities.Users;
import elec5619.sydney.edu.au.mental_health_support_website.db.repository.UserRepository;
import elec5619.sydney.edu.au.mental_health_support_website.service.AppointmentService;
import elec5619.sydney.edu.au.mental_health_support_website.service.UserService;
import elec5619.sydney.edu.au.mental_health_support_website.util.TokenUtil;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/appointments/")

public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @Resource
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @PostMapping("/make")
    public Appointment makeAppointment(
            @RequestBody Appointment appointment
    ) {
        appointmentService.makeAppointment(appointment);
        appointmentService.editStatusAppointment("in progress", appointment);

        return appointment;
    }

    @PutMapping("/cancel/{appointmentID}")
    public boolean cancelAppointment(
            @RequestBody Long appointmentId,
            @RequestHeader("token") String token
    ) {
        String userName = TokenUtil.getUsernameFromToken(token);
        Users user = userService.getUserByUsername(userName);
        if (user != null && isUserEligibleToCancelAppointment(user.getId(), appointmentId) ) {
            appointmentService.editStatusAppointment("cancelled", appointmentService.getAppointment(appointmentId));
            return true;
        }
        return false;
    }

    @PutMapping("/complete/{appointmentID}")
    public boolean completeAppointment(
            @RequestBody Long appointmentId,
            @RequestHeader("token") String token
    ) {
        String userName = TokenUtil.getUsernameFromToken(token);
        Users user = userService.getUserByUsername(userName);
        if (user != null && isUserEligibleToCompleteAppointment(user.getId(), appointmentId) ) {
            appointmentService.editStatusAppointment("completed", appointmentService.getAppointment(appointmentId));
            return true;
        }
        return false;
    }

    @GetMapping("/get/{appointmentID}")
    public Appointment getAppointment(
            @RequestBody Long appointmentId
    ) {
        return appointmentService.getAppointment(appointmentId);
    }

    @GetMapping("/get/ids")
    public List<Appointment> getAppointments(
            @RequestBody List<Long> appointmentIds
    ) {
        return appointmentService.getAppointments(appointmentIds);
    }

    @PutMapping("/edit/{appointmentId}")
    public boolean editAppointment(
            @PathVariable Long appointmentId,
            @RequestHeader("token") String token,
            @RequestBody Appointment apm
    ) {
        String userName = TokenUtil.getUsernameFromToken(token);
        Users user = userService.getUserByUsername(userName);
        if (user != null && isUserEligibleToCancelAppointment(user.getId(), appointmentId) ) {
            appointmentService.editAppointment(apm);
            return true;
        }
        return false;
    }

    @DeleteMapping("/delete/{appointmentId}")
    public boolean removeAppointment(
            @PathVariable Long appointmentId,
            @RequestHeader("token") String token
    ) {
        String userName = TokenUtil.getUsernameFromToken(token);
        Users user = userService.getUserByUsername(userName);
        if (user != null && user.getUserType().equals("admin")) {
            appointmentService.deleteAppointment(appointmentId);
            return true;
        }
        return false;
    }

    private boolean isUserEligibleToCompleteAppointment(Long userId, Long appointmentId) {
        Users user = userRepository.findById(userId).orElse(null);
        Appointment apm = appointmentService.getAppointment(appointmentId);
        if (user == null || apm == null) {
            return false;
        } else if (user.getUserType().equals("admin")) {
            return true;
        } else if (!(apm.getProfessionUserId().equals(userId))) {
            return false;
        }
        return true;
    }

    private boolean isUserEligibleToCancelAppointment(Long userId, Long appointmentId) {
        Users user = userRepository.findById(userId).orElse(null);
        Appointment apm = appointmentService.getAppointment(appointmentId);
        if (user == null || apm == null) {
            return false;
        } else if (user.getUserType().equals("admin")) {
            return true;
        } else if (!(apm.getProfessionUserId().equals(userId)||apm.getUserId().equals(userId))) {
            return false;
        }
        return true;
    }
}
