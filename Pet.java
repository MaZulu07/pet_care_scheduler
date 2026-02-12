import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Pet{

    private String petID;
    private String name;
    private String breed;
    private int age;
    private String ownerName;
    private String contactNumber;
    private LocalDate registrationDate;
    private List<Appointment> appointments;

    
    public Pet(String petID, String name, String breed, int age,
               String ownerName, String contactNumber, LocalDate registrationDate){
        this.petID = petID;
        this.name = name;
        this.breed = breed;
        this.age = age;
        this.ownerName = ownerName;
        this.contactNumber = contactNumber;
        this.registrationDate = registrationDate;
        this.appointments = new ArrayList<>();
    }

    
    public String getPetID(){ 
        return petID; 
    }

    public String getName(){ 
        return name; 
    }

    public String getBreed(){ 
        return breed; 
    }

    public int getAge(){ 
        return age; 
    }

    public String getOwnerName(){ 
        return ownerName; 
    }

    public String getContactNumber(){ 
        return contactNumber; 
    }

    public LocalDate getRegistrationDate(){ 
        return registrationDate; 
    }

    public List<Appointment> getAppointments(){ 
        return appointments; 
    }

    public void setName(String name){ 
        this.name = name; 
    }

    public void setBreed(String breed){ 
        this.breed = breed; 
    }

    public void setAge(int age){ 
        this.age = age; 
    }

    public void setOwnerName(String ownerName){ 
        this.ownerName = ownerName; 
    }

    public void setContactNumber(String contactNumber){ 
        this.contactNumber = contactNumber; 
    }

    public void setRegistrationDate(LocalDate registrationDate){ 
        this.registrationDate = registrationDate; 
    }

    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
    }

    public void displayPetDetails() {
        System.out.println("Pet ID: " + petID);
        System.out.println("Name: " + name);
        System.out.println("Breed: " + breed);
        System.out.println("Age: " + age);
        System.out.println("Owner: " + ownerName);
        System.out.println("Contact: " + contactNumber);
        System.out.println("Registration Date: " + registrationDate);
        System.out.println("Appointments Count: " + appointments.size());
        System.out.println("----------------------------");
    }
}
