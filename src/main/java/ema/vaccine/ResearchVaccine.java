package ema.vaccine;

/**
 * Abstract class handling research phases and authorization logic
 */
public abstract class ResearchVaccine implements Authorization {
    protected String name;
    protected int currentPhase;
    protected String authorizationStatus;
    protected static final int MAX_PHASES = 4;
    
    public ResearchVaccine(String name) {
        this.name = name;
        this.currentPhase = 1;
        this.authorizationStatus = "PENDING";
    }
    
    /**
     * Advance to next research phase
     * @return true if advanced successfully, false if already at max phase
     */
    public boolean advancePhase() {
        if (currentPhase < MAX_PHASES) {
            currentPhase++;
            return true;
        }
        return false;
    }
    
    /**
     * Get current research phase
     * @return current phase number
     */
    public int getCurrentPhase() {
        return currentPhase;
    }
    
    /**
     * Check if vaccine has completed all research phases
     * @return true if all phases completed
     */
    public boolean hasCompletedAllPhases() {
        return currentPhase >= MAX_PHASES;
    }
    
    @Override
    public void authorize() {
        if (hasCompletedAllPhases()) {
            authorizationStatus = "AUTHORIZED";
        } else {
            throw new IllegalStateException("Cannot authorize vaccine that hasn't completed all research phases (current phase: " + currentPhase + ")");
        }
    }
    
    @Override
    public void reject() {
        authorizationStatus = "REJECTED";
    }
    
    @Override
    public boolean isAuthorized() {
        return "AUTHORIZED".equals(authorizationStatus);
    }
    
    @Override
    public String getAuthorizationStatus() {
        return authorizationStatus;
    }
    
    public String getName() {
        return name;
    }
    
    /**
     * Get phase description
     * @return description of current phase
     */
    public String getPhaseDescription() {
        switch (currentPhase) {
            case 1:
                return "Phase 1: Safety and dosage";
            case 2:
                return "Phase 2: Efficacy and side effects";
            case 3:
                return "Phase 3: Large-scale testing";
            case 4:
                return "Phase 4: Post-marketing surveillance";
            default:
                return "Unknown phase";
        }
    }
}
