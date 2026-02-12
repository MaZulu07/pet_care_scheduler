import java.util.*;
import java.io.*;
import java.time.*;
import java.time.format.DateTimeParseException;

public class PetCareScheduler{

    private static Scanner scanner = new Scanner(System.in);
    private static Map<String, Pet> petMap = new HashMap<>(); 

    private static final String PET_FILE = "pets.txt";

    public static void main(String[] args){

        loadPetsFromFile();

        boolean running = true;
        while (running) {
            try {
                System.out.println("\n===== Pet Clinic System =====");
                System.out.println("1. Register Pet");
                System.out.println("2. Schedule Appointment");
                System.out.println("3. Display All Pets");
                System.out.println("4. Generate Report");
                System.out.println("5. Save Data");
                System.out.println("6. Exit");
                System.out.print("Enter choice: ");
    
                int choice = Integer.parseInt(scanner.nextLine());

    
                switch (choice) {
                    case 1:registerPet();         
                        break;
                    case 2:scheduleAppointment();  
                        break;
                    case 3:displayRecords();
                        break;
                    case 4:generateReport();
                        break;
                    case 5:savePetsToFile();
                        running = false;
                        System.out.println("Data Saved!");
                        break;
    
                    default:
                        System.out.println("Invalid choice. Please select a valid option.");
                }
    
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

    }

    private static void registerPet() {
        try {
            System.out.print("Enter Pet ID: ");
            String id = scanner.nextLine();

            if (petMap.containsKey(id)) {
                System.out.println("Pet ID already exists.");
                return;
            }

            System.out.print("Enter Name: ");
            String name = scanner.nextLine();

            System.out.print("Enter Breed: ");
            String breed = scanner.nextLine();

            System.out.print("Enter Age: ");
            int age = Integer.parseInt(scanner.nextLine());

            System.out.print("Enter Owner Name: ");
            String ownerName = scanner.nextLine();

            System.out.print("Enter Contact Info: ");
            String contactNumber = scanner.nextLine();

            Pet pet = new Pet(id, name, breed, age, ownerName, contactNumber, LocalDate.now());

            petMap.put(id, pet);
            System.out.println("Pet registered successfully.");

        } catch (Exception e) {
            System.out.println("Error registering pet.");
        }
    }

    private static void scheduleAppointment() {
        try {
            System.out.print("Enter Pet ID: ");
            String petId = scanner.nextLine();
    
            Pet pet = petMap.get(petId);
            if (pet == null) {
                System.out.println("Error: Pet ID not found.");
                return;
            }
    
            System.out.print("Enter Appointment Type (Vet, Vaccination, Grooming): ");
            String type = scanner.nextLine().trim().toLowerCase();
            if (!(type.equals("vet") || type.equals("vaccination") || type.equals("grooming"))) {
                System.out.println("Error: Invalid appointment type.");
                return;
            }

            System.out.print("Enter Date (YYYY-MM-DD): ");
            LocalDate date = LocalDate.parse(scanner.nextLine());

            System.out.print("Enter Time (HH:MM): ");
            LocalTime time = LocalTime.parse(scanner.nextLine());
            LocalDateTime appointmentDateTime = LocalDateTime.of(date, time);
            if (!appointmentDateTime.isAfter(LocalDateTime.now())) {
                System.out.println("Error: Appointment must be set for a future date and time.");
                return;
            }
    
            System.out.print("Enter Notes (optional): ");
            String notes = scanner.nextLine();
    
            Appointment appointment = new Appointment(type, appointmentDateTime, notes);
            pet.addAppointment(appointment);
            System.out.println("Appointment scheduled successfully.");
    
        } catch (DateTimeParseException e) {
            System.out.println("Error: Invalid date or time format.");
        } catch (Exception e) {
            System.out.println("Error scheduling appointment: " + e.getMessage());
        }
    }
    
    private static void displayRecords() {

        System.out.println("\n===== Display Records =====");
        System.out.println("1. All Registered Pets");
        System.out.println("2. Appointments for a Specific Pet");
        System.out.println("3. Upcoming Appointments (All Pets)");
        System.out.println("4. Past Appointment History");
        System.out.print("Enter your choice: ");
    
        try {
            int choice = Integer.parseInt(scanner.nextLine());
            LocalDateTime now = LocalDateTime.now();
    
            switch (choice) {
                case 1:
                    if (petMap.isEmpty()) {
                        System.out.println("No pets registered.");
                        return;
                    }
                    for (Pet pet : petMap.values()) {
                        pet.displayPetDetails();
                    }
                    break;
  
                case 2:
                    System.out.print("Enter Pet ID: ");
                    String petId = scanner.nextLine();
                    Pet pet = petMap.get(petId);
                    if (pet == null) {
                        System.out.println("Pet not found.");
                        return;
                    }
                    if (pet.getAppointments().isEmpty()) {
                        System.out.println("No appointments for this pet.");
                        return;
                    }
                    for (Appointment appt : pet.getAppointments()) {
                        appt.displayAppointment();
                    }
                    break;
                case 3:
                    boolean upcomingFound = false;
                    for (Pet p : petMap.values()) {
                        for (Appointment appt : p.getAppointments()) {
                            if (appt.getDateTime().isAfter(now)) {
                                System.out.println("\nPet: " + p.getName() + " (ID: " + p.getPetID() + ")");
                                appt.displayAppointment();
                                upcomingFound = true;
                            }
                        }
                    }
                    if (!upcomingFound) {
                        System.out.println("No upcoming appointments.");
                    }
                    break;
                case 4:
                    boolean pastFound = false;
                    for (Pet p : petMap.values()) {
                        boolean printedPet = false;
                        for (Appointment appt : p.getAppointments()) {
                            if (appt.getDateTime().isBefore(now)) {
                                if (!printedPet) {
                                    System.out.println("\nPet: " + p.getName() + " (ID: " + p.getPetID() + ")");
                                    printedPet = true;
                                }
                                appt.displayAppointment();
                                pastFound = true;
                            }
                        }
                    }
                    if (!pastFound) {
                        System.out.println("No past appointments.");
                    }
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
    
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
        } catch (Exception e) {
            System.out.println("Error displaying records: " + e.getMessage());
        }
    }
    
    private static void generateReport() {

        System.out.println("\n===== Pet Care Report =====");
    
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime nextWeek = now.plusDays(7);
        LocalDateTime sixMonthsAgo = now.minusMonths(6);
    
        System.out.println("\n--- Pets with Appointments in the Next 7 Days ---");
        boolean upcomingFound = false;
    
        for (Pet pet : petMap.values()) {
            for (Appointment appt : pet.getAppointments()) {
    
                LocalDateTime apptTime = appt.getDateTime();
    
                if (apptTime.isAfter(now) && apptTime.isBefore(nextWeek)) {
                    System.out.println("Pet: " + pet.getName() + " (ID: " + pet.getPetID() + ")");
                    appt.displayAppointment();
                    upcomingFound = true;
                }
            }
        }
    
        if (!upcomingFound) {
            System.out.println("No upcoming appointments within the next week.");
        }
    
    
        System.out.println("\n--- Pets Overdue for a Vet Visit (No visit in last 6 months) ---");
        boolean overdueFound = false;
    
        for (Pet pet : petMap.values()) {
    
            boolean hasRecentVetVisit = false;
    
            for (Appointment appt : pet.getAppointments()) {
    
                if (appt.getAppointmentType().equalsIgnoreCase("vet")) {
    
                    if (appt.getDateTime().isAfter(sixMonthsAgo) &&
                        appt.getDateTime().isBefore(now)) {
                        hasRecentVetVisit = true;
                        break;
                    }
                }
            }
    
            if (!hasRecentVetVisit) {
                System.out.println("Pet: " + pet.getName() + " (ID: " + pet.getPetID() + ")");
                overdueFound = true;
            }
        }
    
        if (!overdueFound) {
            System.out.println("No pets overdue for a vet visit.");
        }
    }
    
    private static void savePetsToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(PET_FILE))) {

            for (Pet pet : petMap.values()) {
                writer.println(pet.getPetID() + "," +
                        pet.getName() + "," +
                        pet.getBreed() + "," +
                        pet.getAge() + "," +
                        pet.getOwnerName() + "," +
                        pet.getContactNumber() + "," +
                        pet.getRegistrationDate());
            }

            System.out.println("Data saved.");

        } catch (IOException e) {
            System.out.println("Error saving file.");
        }
    }

    private static void loadPetsFromFile() {
        File file = new File("pets.txt");
        if (!file.exists()) return;
    
        try (Scanner fileScanner = new Scanner(file)) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(",");
                
                Pet pet = new Pet(
                    parts[0],
                    parts[1],
                    parts[2],
                    Integer.parseInt(parts[3]),
                    parts[4],
                    parts[5],
                    LocalDate.parse(parts[6])
                );
                petMap.put(pet.getPetID(), pet);  
            }
            System.out.println("Pets loaded from file.");
        } catch (Exception e) {
            System.out.println("Error loading pets: " + e.getMessage());
        }
    }
}