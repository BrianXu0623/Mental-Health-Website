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
@CrossOrigin(origins = "http://localhost:3000")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @Resource
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    /**
     * Post method for making an appointment
     * @param appointment the appointment object that encapsulates all required information
     * @return the appointment object
     */
    @PostMapping("/make")
    public Appointment makeAppointment(
            @RequestBody Appointment appointment
    ) {
        appointmentService.makeAppointment(appointment);
        appointmentService.editStatusAppointment("in progress", appointment);

        return appointment;
    }

    /**
     * Put method for cancelling appointment
     * @param appointmentId the id of the appointment
     * @param token token of user requested to cancel the appointment
     * @return TRUE if the operation was successful otherwise FALSE
     */
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

    /**
     * PUT method for completing the appointment
     * @param appointmentId the id of the appointment to be completed
     * @param token the token of user requested
     * @return TRUE if the operation was successful otherwise FALSE
     */
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

    /**
     * Get method for getting the appointment information
     * @param appointmentId the id of the appointment requested
     * @return the appointment object associated with the id
     */
    @GetMapping("/get/{appointmentID}")
    public Appointment getAppointment(
            @RequestBody Long appointmentId
    ) {
        return appointmentService.getAppointment(appointmentId);
    }

    /**
     * GET a list of appointments given a list of appointments' id
     * @param appointmentIds a list of appointment ids
     * @return a list of appointment object associated a list of ids
     */
    @GetMapping("/get/ids")
    public List<Appointment> getAppointments(
            @RequestBody List<Long> appointmentIds
    ) {
        return appointmentService.getAppointments(appointmentIds);
    }


    /**
     * Put method for modifying the appointment objects
     * @param appointmentId the id of the appointment to be edited
     * @param token the user token requested
     * @param apm the newly updated appointment to be updated
     * @return TRUE if the operation was successful otherwise FALSE
     */
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

    /**
     * Delete mapping for removing appointment
     * @param appointmentId the id of the appointment to be removed
     * @param token the user token requested
     * @return TRUE if the operation was successful otherwise FALSE
     */
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

    /**
     * method to check if the user is eligible to complete an appointment - changing it to complete
     * @param userId the id of the user requested
     * @param appointmentId the id of the appointment to be verified
     * @return TRUE if the user is eligible otherwise FALSE
     */
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

    /**
     * method to check if the user is eligible to cancel an appointment - changing it to cancel
     * @param userId the id of the user requested
     * @param appointmentId the id of the appointment to be verified
     * @return TRUE if the user is eligible otherwise FALSE
     */
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
