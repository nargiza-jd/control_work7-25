package kg.attractor.control_work725.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String prefix) {
        super(prefix + " not found");
    }
}
