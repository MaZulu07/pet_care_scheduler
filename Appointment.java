import java.time.LocalDateTime;

public class Appointment{

    private String appointmentType;
    private LocalDateTime dateTime;
    private String notes;

    public Appointment(String appointmentType, LocalDateTime dateTime, String notes){
        this.appointmentType = appointmentType;
        this.dateTime = dateTime;
        this.notes = notes;
    }

    public Appointment(String appointmentType, LocalDateTime dateTime){
        this.appointmentType = appointmentType;
        this.dateTime = dateTime;
        this.notes = "";

    }

    public void setAppointmentType(String appointmentType){
        this.appointmentType = appointmentType;
    }

    public void setDateTime(LocalDateTime dateTime){
        this.dateTime = dateTime;
    }

    public void setNotes(String notes){
        this.notes = notes;
    }

    public String getAppointmentType(){
        return appointmentType;
    }

    public LocalDateTime getDateTime(){
        return dateTime;
    }

    public String getNotes(){
        return notes;
    }

    public void displayAppointment(){
        System.out.println("Type: " + appointmentType);
        System.out.println("Date & Time: " + dateTime);
        
        if (notes != null && !notes.isEmpty()) {
            System.out.println("Notes: " + notes);
        }

        System.out.println("-----------------------");
    }
}   
