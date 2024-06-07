package pl.mgarbowski.hotelapp.domain.extraservice;

/**
 * Exception thrown when a service is not available.
 */
public class ServiceNotAvailableException extends Exception {
    /**
     * Constructs a new ServiceNotAvailableException with the specified service name.
     *
     * @param serviceName the name of the unavailable service
     */
    public ServiceNotAvailableException(String serviceName) {
        super(String.format("%s is not available for this booking", serviceName));
    }
}
