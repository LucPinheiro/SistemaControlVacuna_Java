package ema.vaccine;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Storage class to manage vaccine collections
 */
public class VaccineStorage {
    private List<Vaccine> vaccines;
    
    public VaccineStorage() {
        this.vaccines = new ArrayList<>();
    }
    
    /**
     * Add a vaccine to storage
     * @param vaccine vaccine to add
     * @return true if added successfully, false if vaccine with same name already exists
     */
    public boolean addVaccine(Vaccine vaccine) {
        if (vaccine == null) {
            throw new IllegalArgumentException("Vaccine cannot be null");
        }
        
        // Check if vaccine with same name already exists
        if (findVaccineByName(vaccine.getName()).isPresent()) {
            return false;
        }
        
        return vaccines.add(vaccine);
    }
    
    /**
     * Remove a vaccine from storage by name
     * @param name name of vaccine to remove
     * @return true if removed successfully, false if not found
     */
    public boolean removeVaccine(String name) {
        Optional<Vaccine> vaccine = findVaccineByName(name);
        if (vaccine.isPresent()) {
            return vaccines.remove(vaccine.get());
        }
        return false;
    }
    
    /**
     * Find a vaccine by name
     * @param name name of vaccine to find
     * @return Optional containing vaccine if found, empty otherwise
     */
    public Optional<Vaccine> findVaccineByName(String name) {
        return vaccines.stream()
            .filter(v -> v.getName().equalsIgnoreCase(name))
            .findFirst();
    }
    
    /**
     * Get all vaccines
     * @return list of all vaccines
     */
    public List<Vaccine> getAllVaccines() {
        return new ArrayList<>(vaccines);
    }
    
    /**
     * Get vaccines by authorization status
     * @param status authorization status to filter by
     * @return list of vaccines with matching status
     */
    public List<Vaccine> getVaccinesByStatus(String status) {
        return vaccines.stream()
            .filter(v -> v.getAuthorizationStatus().equalsIgnoreCase(status))
            .collect(Collectors.toList());
    }
    
    /**
     * Get vaccines by research phase
     * @param phase research phase to filter by
     * @return list of vaccines in specified phase
     */
    public List<Vaccine> getVaccinesByPhase(int phase) {
        return vaccines.stream()
            .filter(v -> v.getCurrentPhase() == phase)
            .collect(Collectors.toList());
    }
    
    /**
     * Get count of vaccines
     * @return number of vaccines in storage
     */
    public int getCount() {
        return vaccines.size();
    }
    
    /**
     * Clear all vaccines from storage
     */
    public void clear() {
        vaccines.clear();
    }
}
