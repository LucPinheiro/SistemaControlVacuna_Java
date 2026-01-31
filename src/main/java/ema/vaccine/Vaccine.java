package ema.vaccine;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Vaccine POJO with validation rules
 */
public class Vaccine extends ResearchVaccine {
    private String manufacturer;
    private String type;
    private LocalDate developmentStartDate;
    private int efficacyPercentage;
    private String description;
    
    public Vaccine(String name, String manufacturer, String type) {
        super(name);
        setManufacturer(manufacturer);
        setType(type);
        this.developmentStartDate = LocalDate.now();
        this.efficacyPercentage = 0;
        this.description = "";
    }
    
    // Getters and Setters with validation
    
    public String getManufacturer() {
        return manufacturer;
    }
    
    public void setManufacturer(String manufacturer) {
        if (manufacturer == null || manufacturer.trim().isEmpty()) {
            throw new IllegalArgumentException("Manufacturer cannot be empty");
        }
        this.manufacturer = manufacturer.trim();
    }
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        if (type == null || type.trim().isEmpty()) {
            throw new IllegalArgumentException("Type cannot be empty");
        }
        // Validate vaccine type
        String typeUpper = type.trim().toUpperCase();
        if (!typeUpper.equals("MRNA") && !typeUpper.equals("VIRAL VECTOR") && 
            !typeUpper.equals("PROTEIN SUBUNIT") && !typeUpper.equals("INACTIVATED") &&
            !typeUpper.equals("LIVE ATTENUATED")) {
            throw new IllegalArgumentException("Invalid vaccine type. Must be one of: mRNA, Viral Vector, Protein Subunit, Inactivated, Live Attenuated");
        }
        this.type = type.trim();
    }
    
    public LocalDate getDevelopmentStartDate() {
        return developmentStartDate;
    }
    
    public void setDevelopmentStartDate(LocalDate developmentStartDate) {
        if (developmentStartDate == null) {
            throw new IllegalArgumentException("Development start date cannot be null");
        }
        if (developmentStartDate.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Development start date cannot be in the future");
        }
        this.developmentStartDate = developmentStartDate;
    }
    
    public int getEfficacyPercentage() {
        return efficacyPercentage;
    }
    
    public void setEfficacyPercentage(int efficacyPercentage) {
        if (efficacyPercentage < 0 || efficacyPercentage > 100) {
            throw new IllegalArgumentException("Efficacy percentage must be between 0 and 100");
        }
        this.efficacyPercentage = efficacyPercentage;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description != null ? description : "";
    }
    
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return String.format(
            "Vaccine: %s\n" +
            "  Manufacturer: %s\n" +
            "  Type: %s\n" +
            "  Development Start: %s\n" +
            "  Efficacy: %d%%\n" +
            "  Research Phase: %d/4 (%s)\n" +
            "  Authorization Status: %s\n" +
            "  Description: %s",
            name, manufacturer, type, 
            developmentStartDate.format(formatter),
            efficacyPercentage,
            currentPhase, getPhaseDescription(),
            authorizationStatus,
            description.isEmpty() ? "N/A" : description
        );
    }
    
    /**
     * Get a brief summary of the vaccine
     * @return brief summary string
     */
    public String toSummary() {
        return String.format("%-25s | %-20s | %-15s | Phase %d/4 | %s", 
            name, manufacturer, type, currentPhase, authorizationStatus);
    }
}
