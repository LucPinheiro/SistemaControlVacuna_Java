package ema.vaccine;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

/**
 * Main application for COVID-19 vaccine management system
 */
public class VaccineManagementApp {
    private VaccineStorage storage;
    private Scanner scanner;
    
    public VaccineManagementApp() {
        this.storage = new VaccineStorage();
        this.scanner = new Scanner(System.in);
    }
    
    public static void main(String[] args) {
        VaccineManagementApp app = new VaccineManagementApp();
        app.run();
    }
    
    public void run() {
        System.out.println("╔════════════════════════════════════════════════════════════════╗");
        System.out.println("║   European Medicines Agency (EMA)                              ║");
        System.out.println("║   COVID-19 Vaccine Management System                           ║");
        System.out.println("╚════════════════════════════════════════════════════════════════╝");
        System.out.println();
        
        boolean running = true;
        while (running) {
            displayMenu();
            String choice = scanner.nextLine().trim();
            
            try {
                switch (choice) {
                    case "1":
                        listAllVaccines();
                        break;
                    case "2":
                        addVaccine();
                        break;
                    case "3":
                        deleteVaccine();
                        break;
                    case "4":
                        authorizeVaccine();
                        break;
                    case "5":
                        rejectVaccine();
                        break;
                    case "6":
                        advanceResearchPhase();
                        break;
                    case "7":
                        viewVaccineDetails();
                        break;
                    case "8":
                        listByStatus();
                        break;
                    case "9":
                        listByPhase();
                        break;
                    case "0":
                        running = false;
                        System.out.println("\nThank you for using EMA Vaccine Management System. Goodbye!");
                        break;
                    default:
                        System.out.println("\n❌ Invalid option. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("\n❌ Error: " + e.getMessage());
            }
            
            if (running && !choice.equals("1") && !choice.equals("8") && !choice.equals("9")) {
                System.out.println("\nPress Enter to continue...");
                scanner.nextLine();
            }
        }
        
        scanner.close();
    }
    
    private void displayMenu() {
        System.out.println("\n" + "=".repeat(65));
        System.out.println("                        MAIN MENU");
        System.out.println("=".repeat(65));
        System.out.println("1. List all vaccines");
        System.out.println("2. Add new vaccine");
        System.out.println("3. Delete vaccine");
        System.out.println("4. Authorize vaccine");
        System.out.println("5. Reject vaccine");
        System.out.println("6. Advance research phase");
        System.out.println("7. View vaccine details");
        System.out.println("8. List vaccines by authorization status");
        System.out.println("9. List vaccines by research phase");
        System.out.println("0. Exit");
        System.out.println("=".repeat(65));
        System.out.print("Select an option: ");
    }
    
    private void listAllVaccines() {
        System.out.println("\n" + "=".repeat(100));
        System.out.println("                                  ALL VACCINES");
        System.out.println("=".repeat(100));
        
        List<Vaccine> vaccines = storage.getAllVaccines();
        if (vaccines.isEmpty()) {
            System.out.println("No vaccines registered in the system.");
        } else {
            System.out.println(String.format("%-25s | %-20s | %-15s | %-10s | %s", 
                "Name", "Manufacturer", "Type", "Phase", "Status"));
            System.out.println("-".repeat(100));
            for (Vaccine vaccine : vaccines) {
                System.out.println(vaccine.toSummary());
            }
            System.out.println("-".repeat(100));
            System.out.println("Total vaccines: " + vaccines.size());
        }
        System.out.println();
    }
    
    private void addVaccine() {
        System.out.println("\n" + "=".repeat(65));
        System.out.println("                     ADD NEW VACCINE");
        System.out.println("=".repeat(65));
        
        System.out.print("Vaccine name: ");
        String name = scanner.nextLine().trim();
        
        if (name.isEmpty()) {
            System.out.println("❌ Vaccine name cannot be empty.");
            return;
        }
        
        System.out.print("Manufacturer: ");
        String manufacturer = scanner.nextLine().trim();
        
        System.out.println("\nAvailable vaccine types:");
        System.out.println("  - mRNA");
        System.out.println("  - Viral Vector");
        System.out.println("  - Protein Subunit");
        System.out.println("  - Inactivated");
        System.out.println("  - Live Attenuated");
        System.out.print("Type: ");
        String type = scanner.nextLine().trim();
        
        try {
            Vaccine vaccine = new Vaccine(name, manufacturer, type);
            
            System.out.print("Efficacy percentage (0-100): ");
            String efficacyStr = scanner.nextLine().trim();
            if (!efficacyStr.isEmpty()) {
                vaccine.setEfficacyPercentage(Integer.parseInt(efficacyStr));
            }
            
            System.out.print("Development start date (dd/MM/yyyy, or press Enter for today): ");
            String dateStr = scanner.nextLine().trim();
            if (!dateStr.isEmpty()) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                vaccine.setDevelopmentStartDate(LocalDate.parse(dateStr, formatter));
            }
            
            System.out.print("Description (optional): ");
            String description = scanner.nextLine().trim();
            vaccine.setDescription(description);
            
            if (storage.addVaccine(vaccine)) {
                System.out.println("\n✓ Vaccine '" + name + "' added successfully!");
            } else {
                System.out.println("\n❌ A vaccine with the name '" + name + "' already exists.");
            }
        } catch (NumberFormatException e) {
            System.out.println("\n❌ Invalid efficacy percentage. Please enter a number between 0 and 100.");
        } catch (DateTimeParseException e) {
            System.out.println("\n❌ Invalid date format. Please use dd/MM/yyyy.");
        } catch (IllegalArgumentException e) {
            System.out.println("\n❌ Error: " + e.getMessage());
        }
    }
    
    private void deleteVaccine() {
        System.out.println("\n" + "=".repeat(65));
        System.out.println("                     DELETE VACCINE");
        System.out.println("=".repeat(65));
        
        System.out.print("Enter vaccine name to delete: ");
        String name = scanner.nextLine().trim();
        
        if (storage.removeVaccine(name)) {
            System.out.println("\n✓ Vaccine '" + name + "' deleted successfully!");
        } else {
            System.out.println("\n❌ Vaccine '" + name + "' not found.");
        }
    }
    
    private void authorizeVaccine() {
        System.out.println("\n" + "=".repeat(65));
        System.out.println("                   AUTHORIZE VACCINE");
        System.out.println("=".repeat(65));
        
        System.out.print("Enter vaccine name to authorize: ");
        String name = scanner.nextLine().trim();
        
        Optional<Vaccine> vaccineOpt = storage.findVaccineByName(name);
        if (vaccineOpt.isPresent()) {
            Vaccine vaccine = vaccineOpt.get();
            try {
                vaccine.authorize();
                System.out.println("\n✓ Vaccine '" + name + "' has been AUTHORIZED!");
            } catch (IllegalStateException e) {
                System.out.println("\n❌ " + e.getMessage());
            }
        } else {
            System.out.println("\n❌ Vaccine '" + name + "' not found.");
        }
    }
    
    private void rejectVaccine() {
        System.out.println("\n" + "=".repeat(65));
        System.out.println("                    REJECT VACCINE");
        System.out.println("=".repeat(65));
        
        System.out.print("Enter vaccine name to reject: ");
        String name = scanner.nextLine().trim();
        
        Optional<Vaccine> vaccineOpt = storage.findVaccineByName(name);
        if (vaccineOpt.isPresent()) {
            Vaccine vaccine = vaccineOpt.get();
            vaccine.reject();
            System.out.println("\n✓ Vaccine '" + name + "' has been REJECTED.");
        } else {
            System.out.println("\n❌ Vaccine '" + name + "' not found.");
        }
    }
    
    private void advanceResearchPhase() {
        System.out.println("\n" + "=".repeat(65));
        System.out.println("                ADVANCE RESEARCH PHASE");
        System.out.println("=".repeat(65));
        
        System.out.print("Enter vaccine name: ");
        String name = scanner.nextLine().trim();
        
        Optional<Vaccine> vaccineOpt = storage.findVaccineByName(name);
        if (vaccineOpt.isPresent()) {
            Vaccine vaccine = vaccineOpt.get();
            if (vaccine.advancePhase()) {
                System.out.println("\n✓ Vaccine '" + name + "' advanced to phase " + vaccine.getCurrentPhase());
                System.out.println("  " + vaccine.getPhaseDescription());
                
                if (vaccine.hasCompletedAllPhases()) {
                    System.out.println("\n⚠ This vaccine has completed all research phases and is ready for authorization review.");
                }
            } else {
                System.out.println("\n⚠ Vaccine '" + name + "' is already at the maximum phase (4).");
            }
        } else {
            System.out.println("\n❌ Vaccine '" + name + "' not found.");
        }
    }
    
    private void viewVaccineDetails() {
        System.out.println("\n" + "=".repeat(65));
        System.out.println("                   VACCINE DETAILS");
        System.out.println("=".repeat(65));
        
        System.out.print("Enter vaccine name: ");
        String name = scanner.nextLine().trim();
        
        Optional<Vaccine> vaccineOpt = storage.findVaccineByName(name);
        if (vaccineOpt.isPresent()) {
            System.out.println("\n" + vaccineOpt.get().toString());
        } else {
            System.out.println("\n❌ Vaccine '" + name + "' not found.");
        }
    }
    
    private void listByStatus() {
        System.out.println("\n" + "=".repeat(65));
        System.out.println("              LIST BY AUTHORIZATION STATUS");
        System.out.println("=".repeat(65));
        System.out.println("Available statuses: PENDING, AUTHORIZED, REJECTED");
        System.out.print("Enter status: ");
        String status = scanner.nextLine().trim().toUpperCase();
        
        List<Vaccine> vaccines = storage.getVaccinesByStatus(status);
        System.out.println("\n" + "=".repeat(100));
        System.out.println("Vaccines with status: " + status);
        System.out.println("=".repeat(100));
        
        if (vaccines.isEmpty()) {
            System.out.println("No vaccines found with status '" + status + "'.");
        } else {
            System.out.println(String.format("%-25s | %-20s | %-15s | %-10s | %s", 
                "Name", "Manufacturer", "Type", "Phase", "Status"));
            System.out.println("-".repeat(100));
            for (Vaccine vaccine : vaccines) {
                System.out.println(vaccine.toSummary());
            }
            System.out.println("-".repeat(100));
            System.out.println("Total: " + vaccines.size());
        }
        System.out.println();
    }
    
    private void listByPhase() {
        System.out.println("\n" + "=".repeat(65));
        System.out.println("                LIST BY RESEARCH PHASE");
        System.out.println("=".repeat(65));
        System.out.print("Enter phase number (1-4): ");
        String phaseStr = scanner.nextLine().trim();
        
        try {
            int phase = Integer.parseInt(phaseStr);
            if (phase < 1 || phase > 4) {
                System.out.println("\n❌ Phase must be between 1 and 4.");
                return;
            }
            
            List<Vaccine> vaccines = storage.getVaccinesByPhase(phase);
            System.out.println("\n" + "=".repeat(100));
            System.out.println("Vaccines in phase " + phase);
            System.out.println("=".repeat(100));
            
            if (vaccines.isEmpty()) {
                System.out.println("No vaccines found in phase " + phase + ".");
            } else {
                System.out.println(String.format("%-25s | %-20s | %-15s | %-10s | %s", 
                    "Name", "Manufacturer", "Type", "Phase", "Status"));
                System.out.println("-".repeat(100));
                for (Vaccine vaccine : vaccines) {
                    System.out.println(vaccine.toSummary());
                }
                System.out.println("-".repeat(100));
                System.out.println("Total: " + vaccines.size());
            }
        } catch (NumberFormatException e) {
            System.out.println("\n❌ Invalid phase number. Please enter a number between 1 and 4.");
        }
        System.out.println();
    }
}
