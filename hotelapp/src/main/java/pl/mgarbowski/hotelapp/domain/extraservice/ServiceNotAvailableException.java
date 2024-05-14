package pl.mgarbowski.hotelapp.domain.extraservice;

public class ServiceNotAvailableException extends Exception {
    public ServiceNotAvailableException(String serviceName) {
        super(String.format("%s is not available for this booking", serviceName));
    }
}
