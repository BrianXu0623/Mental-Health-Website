package elec5619.sydney.edu.au.mental_health_support_website.controller;

import elec5619.sydney.edu.au.mental_health_support_website.controller.param.AppointmentInfo;
import elec5619.sydney.edu.au.mental_health_support_website.db.entities.Appointment;
import elec5619.sydney.edu.au.mental_health_support_website.db.entities.Users;
import elec5619.sydney.edu.au.mental_health_support_website.db.repository.UserRepository;
import elec5619.sydney.edu.au.mental_health_support_website.service.AppointmentService;
import elec5619.sydney.edu.au.mental_health_support_website.service.UserService;
import elec5619.sydney.edu.au.mental_health_support_website.util.TokenUtil;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
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

    @GetMapping("/test-create")
    public Appointment testCreate() {
        Appointment obj  = Appointment.builder()
                .appointmentTopic("Social Anxiety Disorder")
                .date(LocalDate.now())
                .time(LocalTime.now())
                .status("In Progress")
                .professionalUserId(1L)
                .userId(2L)
                .build();

        return appointmentService.makeAppointment(
            obj
        );
    }
    /**
     * Post method for making an appointment
     * @param appointment the appointment object that encapsulates all required information
     * @return the appointment object
     */
    @PostMapping("/make")
    public AppointmentInfo makeAppointment(
            @RequestBody Appointment appointment
    ) {
        appointment.setStatus("in progress");
        Appointment obj = appointmentService.makeAppointment(appointment);
        Users professional = userService.getUserByUserId(appointment.getProfessionalUserId());

        return AppointmentInfo.builder()
                .appointment(obj)
                .professionalName(professional.getUsername())
                .availability(professional.getAvailableHours())
                .clinic(professional.getClinic()).build();
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
    public AppointmentInfo getAppointment(
            @RequestBody Long appointmentId
    ) {
        Appointment obj = appointmentService.getAppointment(appointmentId);
        Users professional = userService.getUserByUserId(obj.getProfessionalUserId());

        return AppointmentInfo.builder()
                .appointment(obj)
                .professionalName(professional.getUsername())
                .availability(professional.getAvailableHours())
                .clinic(professional.getClinic()).build();
    }

    /**
     * GET a list of appointments given a list of appointments' id
     * @param appointmentIds a list of appointment ids
     * @return a list of appointment object associated a list of ids
     */
    @GetMapping("/get/all")
    public List<AppointmentInfo> getAppointments(
            @RequestBody List<Long> appointmentIds
    ) {
        List<AppointmentInfo> objs = new ArrayList<>();
        for (Long id : appointmentIds) {

            Appointment appointment = appointmentService.getAppointment(id);
            Users professional = userService.getUserByUserId(appointment.getProfessionalUserId());
            objs.add(
                   AppointmentInfo.builder()
                           .appointment(appointment)
                           .professionalName(professional.getUsername())
                           .availability(professional.getAvailableHours())
                           .clinic(professional.getClinic()).build()
           );
        }
        return objs;
    }

    /**
     * Get method for getting a list of appointments associated with a user id
     * @param token the token of the user requested
     * @return a list of appointment info if found, otherwise an empty list
     */
    @GetMapping("/get/byUser/")
    public List<AppointmentInfo> getAppointmentsByUserToken(
            @RequestHeader("token") String token
    ) {
        String username = TokenUtil.getUsernameFromToken(token);
        Long userId = userService.getUserByUsername(username).getId();
        List<AppointmentInfo> objs = new ArrayList<>();
        for (Appointment appointment : appointmentService.getAppointmentByUserId(userId)) {
            Users professional = userService.getUserByUserId(appointment.getProfessionalUserId());
            objs.add(
                   AppointmentInfo.builder()
                           .appointment(appointment)
                           .professionalName(professional.getUsername())
                           .availability(professional.getAvailableHours())
                           .clinic(professional.getClinic())
                           .build()
            );
        }
        return objs;
    }

    /**
     * Get method for getting a list of appointments associated with a professional user id
     * @param token the token of the user requested
     * @return a list of appointment info if found, otherwise an empty list
     */
    @GetMapping("/get/byProfessionalUser/")
    public List<AppointmentInfo> getAppointmentsByProfessionalUserId(
            @RequestHeader("token") String token
    ) {
        String username = TokenUtil.getUsernameFromToken(token);
        Long userId = userService.getUserByUsername(username).getId();
        List<AppointmentInfo> objs = new ArrayList<>();
        for (Appointment appointment : appointmentService.getAppointmentByProfessionalUserId(userId)) {
            Users professional = userService.getUserByUserId(appointment.getProfessionalUserId());
            objs.add(
                    AppointmentInfo.builder()
                            .appointment(appointment)
                            .professionalName(professional.getUsername())
                            .availability(professional.getAvailableHours())
                            .clinic(professional.getClinic())
                            .build()
            );
        }
        return objs;
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
        } else if (!(apm.getProfessionalUserId().equals(userId))) {
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
        } else if (!(apm.getProfessionalUserId().equals(userId)||apm.getUserId().equals(userId))) {
            return false;
        }
        return true;
    }
}
