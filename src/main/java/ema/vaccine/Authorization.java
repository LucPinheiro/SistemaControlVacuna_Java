package ema.vaccine;

/**
 * Interface for authorization functionality
 */
public interface Authorization {
    /**
     * Authorize a vaccine
     */
    void authorize();
    
    /**
     * Reject a vaccine
     */
    void reject();
    
    /**
     * Check if vaccine is authorized
     * @return true if authorized, false otherwise
     */
    boolean isAuthorized();
    
    /**
     * Get authorization status
     * @return authorization status as string
     */
    String getAuthorizationStatus();
}
