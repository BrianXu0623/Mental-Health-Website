package elec5619.sydney.edu.au.mental_health_support_website.controller.param;

import elec5619.sydney.edu.au.mental_health_support_website.db.entities.Appointment;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AppointmentInfo {
    private Appointment appointment;
    private String professionalName;
    private String clinic;
    private String availability;
}
